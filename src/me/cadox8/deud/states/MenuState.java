package me.cadox8.deud.states;

import lombok.NonNull;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.audio.Sound;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.components.images.UIImage;
import me.cadox8.deud.nysvaui.components.images.UIImageButton;
import me.cadox8.deud.nysvaui.components.text.UITextButton;
import me.cadox8.deud.nysvaui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Updater;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private final NysvaManager nysvaManager;

    public MenuState(@NonNull GameAPI gameAPI) {
        super(gameAPI);

        nysvaManager = new NysvaManager();

        final UIImage background = new UIImage(gameAPI, GUI.background);
        final UIImageButton start = new UIImageButton(gameAPI, GUI.play[0], () -> {
            gameAPI.getMouseManager().setNysvaUI(null);
            setState(gameAPI.getGame().getGameState());

            if (gameAPI.getGame().getPlayerData() == null) gameAPI.getPlayer().setNick("Arya");

            Sound.TOWN_MUSIC.playLoop();

            //gameAPI.getGame().getDisplay().getFrame().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
        });
        final UIImageButton exit = new UIImageButton(gameAPI, GUI.exit[0], () -> System.exit(0));

        final UIImageButton logo = new UIImageButton(gameAPI, GUI.logo, () -> {
            gameAPI.getMouseManager().setNysvaUI(null);
            setState(gameAPI.getGame().getEditorState());
        });

        final UITextButton update = new UITextButton(gameAPI, "New version available: " + Updater.getWebVersion() + " ⇩", () -> {
            try {
                Desktop.getDesktop().browse(new URI("https://cadox8.github.io/Deud/index.html"));
            } catch (URISyntaxException | IOException e) {
                Log.danger("Link doesn't exist");
                e.printStackTrace();
            }
        });

        background.setUiDimension(new UIDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));
        start.setUiDimension(new UIDimension(150, 650, 200, 100));
        exit.setUiDimension(new UIDimension(900, 650, 200, 100));
        logo.setUiDimension(new UIDimension(gameAPI.getWidth() - 97, 0, 97, 151));

        background.setResize(false);
        logo.setResize(false);

        if (Updater.timeToUpdate()) nysvaManager.addObject(update);

        nysvaManager.addObject(background);
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
        g.setColor(Color.WHITE);
        g.drawString("Version: " + Launcher.VERSION, 5, 795);
        g.drawString("© Deud 2016-2019 - The Game is property of Cadox8", 955, 795);
    }
}
