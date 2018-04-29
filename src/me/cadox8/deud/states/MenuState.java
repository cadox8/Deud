package me.cadox8.deud.states;

import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.ui.*;
import me.cadox8.deud.utils.DeudColor;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Updater;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(API API) {
        super(API);

        uiManager = new UIManager(API);
        API.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImage(0, 0, API.getWidth(), API.getHeight(), GUI.background));

        uiManager.addObject(new UIImageButton(150, 450, 200, 100, GUI.play, new ClickListener() {
            @Override
            public void onClick() {
                API.getMouseManager().setUIManager(null);
                setState(API.getGame().gameState);
            }
        }));
        uiManager.addObject(new UIImageButton(450, 450, 200, 100, GUI.exit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));

        if (!Updater.timeToUpdate()) return;
        uiManager.addObject(new UIText(5, 15, DeudColor.RED, "New version available: " + Updater.getWebVersion() + " ⇩", new ClickListener() {
            @Override
            public void onClick() {
                try {
                    API.getMouseManager().setUIManager(null);
                    Desktop.getDesktop().browse(new URI("https://cadox8.github.io/Deud/index.html"));
                    API.getMouseManager().setUIManager(uiManager);
                } catch (URISyntaxException | IOException e){
                    Log.log(Log.LogType.DANGER, "Link doesn't exist");
                }
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
        g.setColor(DeudColor.WHITE.toColor());
        g.drawString("Version: " + Launcher.VERSION, 5, 595);
        g.drawString("© Deud 2016-2018 - The Game is property of Cadox8", 505, 595);
    }
}
