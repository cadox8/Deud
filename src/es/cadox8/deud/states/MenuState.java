package es.cadox8.deud.states;

import es.cadox8.deud.Launcher;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.audio.Sound;
import es.cadox8.deud.audio.Sounds;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.graphics.fonts.Text;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.ui.UiManager;
import es.cadox8.deud.ui.components.button.UiImageButton;
import es.cadox8.deud.ui.components.button.UiTextButton;
import es.cadox8.deud.ui.components.image.UiImage;
import es.cadox8.deud.utils.Log;
import es.cadox8.deud.utils.Updater;
import lombok.NonNull;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private final UiManager uiManager;

    private final Sound menu = Sounds.MENU.getSound();

    public MenuState(@NonNull GameAPI gameAPI) {
        super(gameAPI);

        menu.playLoop();

        uiManager = new UiManager();

        final UiImage background = new UiImage(GUI.background);
        final UiImageButton start = new UiImageButton(GUI.play[0], () -> {
            gameAPI.getMouseManager().setUiManager(null);
            setState(gameAPI.getGame().getGameState());

            menu.stop();
            Sounds.TOWN_MUSIC.playLoop();

            //gameAPI.getGame().getDisplay().getFrame().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
        });
        final UiImageButton exit = new UiImageButton(GUI.exit[0], () -> System.exit(0));

        final UiImage logo = new UiImage(GUI.logo);

        background.setUiDimension(0, 0, this.gameAPI.getWidth(), this.gameAPI.getHeight());
        start.setUiDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 65, 200, 100);
        exit.setUiDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 65, 200, 100);
        logo.setUiDimension(this.gameAPI.getWidth() - 97, 0);

        this.uiManager.addComponent(background);

        if (Updater.checkForUpdate()) {
            final UiTextButton update = new UiTextButton( "New version available: " + Updater.latestVersion().getVersion() + " ⇩", () -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://cadox8.es/deud"));
                } catch (URISyntaxException | IOException e) {
                    Log.danger("Link doesn't exist");
                    e.printStackTrace();
                }
            });
            update.setUiDimension(5, 40, 200, 20);
            this.uiManager.addComponent(update);
        }

        this.uiManager.addComponent(start);
        this.uiManager.addComponent(exit);
        this.uiManager.addComponent(logo);

        gameAPI.getMouseManager().setUiManager(this.uiManager);
    }


    @Override
    public void tick() {
        this.uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        this.uiManager.render(g);
        Text.drawString(g, "Version: " + Launcher.VERSION, 5, 20, Color.WHITE, Fonts.DEUD_TALL);
        Text.drawString(g, "© Deud 2016-2021 - This Game is property of Cadox8", gameAPI.getWidth() - 550, gameAPI.getHeight() - 20, Color.WHITE, Fonts.DEUD_TALL);
    }
}
