package me.cadox8.deud.states;

import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.ui.*;
import me.cadox8.deud.utils.DeudColor;

import java.awt.*;

public class OptionsState extends State {

    private UIManager uiManager;

    public OptionsState(API API) {
        super(API);

        uiManager = new UIManager(API);
        API.getMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIWall(0, 0, API.getWidth(), API.getHeight(), Color.GRAY));

        uiManager.addObject(new UIImageButton(5, 5, 250, 70, GUI.buttons, () -> System.out.println("Test")));
        uiManager.addObject(new UIText(5, 5, DeudColor.BLACK, "Test", () -> {}));

        uiManager.addObject(new UIImageButton(260, 5, 250, 70, GUI.buttons, () -> System.exit(0)));
        uiManager.addObject(new UIText(260, 5, DeudColor.BLACK, "Exit", () -> {}));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
