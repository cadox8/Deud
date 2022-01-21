package es.cadox8.deud.states;

import es.cadox8.deud.Launcher;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.audio.Sound;
import es.cadox8.deud.audio.SoundType;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.graphics.fonts.Text;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.ui.AarinManager;
import es.cadox8.deud.ui.components.button.UIImageButton;
import es.cadox8.deud.ui.components.button.UITextButton;
import es.cadox8.deud.ui.components.image.UIImage;
import es.cadox8.deud.ui.helpers.AarinArea;
import es.cadox8.deud.ui.helpers.AarinColor;
import lombok.NonNull;
import es.cadox8.deud.utils.Log;
import es.cadox8.deud.utils.Updater;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private final AarinManager aarinManager;

    private final Sound menu = SoundType.MENU.getSound();

    public MenuState(@NonNull GameAPI gameAPI) {
        super(gameAPI);

        menu.playLoop();

        aarinManager = new AarinManager();

        final UIImage background = new UIImage(gameAPI, GUI.background);
        final UIImageButton start = new UIImageButton(gameAPI, GUI.play[0], () -> {
            gameAPI.getMouseManager().setAarinManager(null);
            setState(gameAPI.getGame().getGameState());

            if (gameAPI.getGame().getPlayerData() == null) gameAPI.getPlayer().setNick("Arya");

            menu.stop();
            SoundType.TOWN_MUSIC.playLoop();

            //gameAPI.getGame().getDisplay().getFrame().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
        });
        final UIImageButton exit = new UIImageButton(gameAPI, GUI.exit[0], () -> System.exit(0));

        final UIImageButton logo = new UIImageButton(gameAPI, GUI.logo, () -> {});

        background.setArea(new AarinArea().addPoints(new Point(0, 0), new Point(gameAPI.getWidth(), gameAPI.getHeight())));
        start.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 65), new Point(200, 100)));
        exit.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 65), new Point(200, 100)));
        logo.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() - 97, 0), new Point(97, 151)));

        background.setResizable(false);

        aarinManager.addObject(background);

        if (Updater.checkForUpdate()) {
            final UITextButton update = new UITextButton(gameAPI, AarinColor.TRANSPARENT, false, "New version available: " + Updater.latestVersion().getVersion() + " ⇩", () -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://cadox8.es/deud"));
                } catch (URISyntaxException | IOException e) {
                    Log.danger("Link doesn't exist");
                    e.printStackTrace();
                }
            });
            update.setArea(new AarinArea().addPoints(new Point(5, 40), new Point(200, 20)));
            aarinManager.addObject(update);
        }

        aarinManager.addObject(start);
        aarinManager.addObject(exit);
        aarinManager.addObject(logo);

        gameAPI.getMouseManager().setAarinManager(aarinManager);
    }


    @Override
    public void tick() {
        aarinManager.tick();
    }

    @Override
    public void render(Graphics g) {
        aarinManager.render(g);
        Text.drawString(g, "Version: " + Launcher.VERSION, 5, 20, Color.WHITE, Fonts.DEUD_TALL);
        Text.drawString(g, "© Deud 2016-2021 - This Game is property of Cadox8", gameAPI.getWidth() - 550, gameAPI.getHeight() - 20, Color.WHITE, Fonts.DEUD_TALL);
    }
}
