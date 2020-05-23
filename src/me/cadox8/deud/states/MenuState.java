package me.cadox8.deud.states;

import lombok.NonNull;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.audio.Sound;
import me.cadox8.deud.audio.Sounds;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.ui.NysvaManager;
import me.cadox8.deud.ui.components.images.UIImage;
import me.cadox8.deud.ui.components.images.UIImageButton;
import me.cadox8.deud.ui.components.text.UITextButton;
import me.cadox8.deud.ui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Updater;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private final NysvaManager nysvaManager;

    private final Sound menu = Sounds.MENU.getSound();

    public MenuState(@NonNull GameAPI gameAPI) {
        super(gameAPI);

        menu.playLoop();

        nysvaManager = new NysvaManager();

        final UIImage background = new UIImage(gameAPI, GUI.background);
        final UIImageButton start = new UIImageButton(gameAPI, GUI.play[0], () -> {
            gameAPI.getMouseManager().setNysvaUI(null);
            setState(gameAPI.getGame().getGameState());

            if (gameAPI.getGame().getPlayerData() == null) gameAPI.getPlayer().setNick("Arya");

            menu.stop();
            Sounds.TOWN_MUSIC.playLoop();

            //gameAPI.getGame().getDisplay().getFrame().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
        });
        final UIImageButton exit = new UIImageButton(gameAPI, GUI.exit[0], () -> System.exit(0));

        final UIImageButton logo = new UIImageButton(gameAPI, GUI.logo, () -> {
            gameAPI.getMouseManager().setNysvaUI(null);
            setState(gameAPI.getGame().getEditorState());
        });

        background.setUiDimension(new UIDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));
        start.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 65, 200, 100));
        exit.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 65, 200, 100));
        logo.setUiDimension(new UIDimension(gameAPI.getWidth() - 97, 0, 97, 151));

        background.setResize(false);
        logo.setResize(false);

        nysvaManager.addObject(background);

        if (Updater.checkForUpdate()) {
            final UITextButton update = new UITextButton(gameAPI, "New version available: " + Updater.latestVersion().getVersion() + " ⇩", () -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://cadox8.es/deud"));
                } catch (URISyntaxException | IOException e) {
                    Log.danger("Link doesn't exist");
                    e.printStackTrace();
                }
            });
            update.setUiDimension(new UIDimension(5, 40, 200, 20));
            nysvaManager.addObject(update);
        }

        nysvaManager.addObject(start);
        nysvaManager.addObject(exit);
        nysvaManager.addObject(logo);

        gameAPI.getMouseManager().setNysvaUI(nysvaManager);
    }


    @Override
    public void tick() {
        nysvaManager.tick();
    }

    @Override
    public void render(Graphics g) {
        nysvaManager.render(g);
        Text.drawString(g, "Version: " + Launcher.VERSION, 5, 20, Color.WHITE, 2);
        Text.drawString(g, "© Deud 2016-2019 - This Game is property of Cadox8", gameAPI.getWidth() - 550, gameAPI.getHeight() - 20, Color.WHITE, 2);
    }
}
