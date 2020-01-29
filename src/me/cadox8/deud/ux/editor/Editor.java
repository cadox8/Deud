package me.cadox8.deud.ux.editor;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.components.field.UIField;
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
        final UIBlock background = new UIBlock(gameAPI, NysvaColor.DARK_GRAY);
        final UIText playerNameLabel = new UIText(gameAPI, "Nick: ");
        final UIField playerName = new UIField(gameAPI);

        background.setUiDimension(new UIDimension(0, 0, screenWidth, screenHeight));
        playerName.setUiDimension(new UIDimension((screenWidth / 2) - 100, 20, 200, 20));
        playerNameLabel.setUiDimension(new UIDimension((screenWidth / 2) - 155, 20, 100, 20));

        playerNameLabel.setTextColor(NysvaColor.BLACK);
        playerName.setText("Testing");

        add(background, playerName, playerNameLabel);
    }

    private void add(NysvaUI... components) {
        Arrays.asList(components).forEach(nysvaManager::addObject);
    }
}
