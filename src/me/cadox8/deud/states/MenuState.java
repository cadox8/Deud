package me.cadox8.deud.states;

import lombok.NonNull;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.ui.UIImage;
import me.cadox8.deud.ui.UIImageButton;
import me.cadox8.deud.ui.UIManager;
import me.cadox8.deud.ui.UIText;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Updater;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(@NonNull GameAPI gameAPI) {
        super(gameAPI);

        uiManager = new UIManager(gameAPI);
        gameAPI.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImage(0, 0, gameAPI.getWidth(), gameAPI.getHeight(), GUI.background)); // Must be the first

        uiManager.addObject(new UIImageButton(150, 650, 200, 100, GUI.play, () -> {
            gameAPI.getMouseManager().setUIManager(null);
            setState(gameAPI.getGame().gameState);

            if (gameAPI.getGame().getPlayerData() == null) gameAPI.getPlayer().setNick("Arya");

            gameAPI.getGame().getDisplay().getFrame().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
            Log.log("Player nick: " + gameAPI.getPlayer().getNick());
        }));

        uiManager.addObject(new UIImageButton(900, 650, 200, 100, GUI.exit, () -> System.exit(0)));

        uiManager.addObject(new UIImage(gameAPI.getWidth() - 97, 0, 97, 151, GUI.logo));


        if (!Updater.timeToUpdate()) return;
        uiManager.addObject(new UIText(5, 15, Color.RED, "New version available: " + Updater.getWebVersion() + " ⇩", () -> {
            try {
                gameAPI.getMouseManager().setUIManager(null);
                Desktop.getDesktop().browse(new URI("https://cadox8.github.io/Deud/index.html"));
                gameAPI.getMouseManager().setUIManager(uiManager);
            } catch (URISyntaxException | IOException e){
                Log.log(Log.LogType.DANGER, "Link doesn't exist");
                e.printStackTrace();
            }
        }));
    }



    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        g.setColor(Color.WHITE);
        g.drawString("Version: " + Launcher.VERSION, 5, 795);
        g.drawString("© Deud 2016-2019 - The Game is property of Cadox8", 955, 795);
    }
}
