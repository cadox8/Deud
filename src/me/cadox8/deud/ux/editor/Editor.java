package me.cadox8.deud.ux.editor;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.components.field.UIField;
import me.cadox8.deud.nysvaui.components.images.UIImage;
import me.cadox8.deud.nysvaui.components.text.UIText;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.util.Arrays;

public class Editor {

    private final GameAPI gameAPI;
    @Getter private final NysvaManager nysvaManager;

    private final int screenWidth, screenHeight;

    public Editor(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.nysvaManager = new NysvaManager();

        screenWidth = gameAPI.getWidth();
        screenHeight = gameAPI.getHeight();

        load();
    }

    private void load() {
        final UIImage background = new UIImage(gameAPI, GUI.backgroundEditor);
        final UIText playerNameLabel = new UIText(gameAPI, "Nick: ");
        final UIField playerName = new UIField(gameAPI);
        

        background.setUiDimension(new UIDimension(-15, -15, screenWidth + 30, screenHeight + 30));
        playerName.setUiDimension(new UIDimension((screenWidth / 2) - 100, 20, 200, 20));
        playerNameLabel.setUiDimension(new UIDimension((screenWidth / 2) - 155, 20, 100, 20));

        background.setResize(false);
        playerNameLabel.setTextColor(NysvaColor.WHITE);
        playerName.setText("Testing");

        add(background, playerName, playerNameLabel);
    }

    private void add(NysvaUI... components) {
        Arrays.asList(components).forEach(nysvaManager::addObject);
    }
}
