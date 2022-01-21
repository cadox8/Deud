package es.cadox8.deud.ux.options;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.saves.FileUtils;
import es.cadox8.deud.states.State;
import es.cadox8.deud.ui.UiManager;
import es.cadox8.deud.ui.components.block.UiBlock;
import es.cadox8.deud.ui.components.button.UiSelectedTextButton;
import es.cadox8.deud.ui.helpers.UiColor;
import es.cadox8.deud.ui.helpers.UiDimension;
import lombok.Getter;

import java.awt.*;

public class Options {

    private final GameAPI gameAPI;

    private final UiManager uiManager;

    @Getter private boolean enabled = false;

    public Options(GameAPI gameAPI, Player player) {
        this.uiManager = new UiManager();
        this.gameAPI = gameAPI;


        final UiBlock base = new UiBlock(UiColor.DARK_GRAY.color());
        base.setUiDimension(new UiDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));

        final UiSelectedTextButton resume = new UiSelectedTextButton("Resume Game", () -> this.setEnabled(false));
        resume.setUiDimension(new UiDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight() / 2 - 35, 220, 35));

        final UiSelectedTextButton menu = new UiSelectedTextButton("Main Menu", () -> {
            FileUtils.save(player, gameAPI);
            this.gameAPI.getMouseManager().setUiManager(null);
            State.setState(gameAPI.getGame().getMenuState());
        });
        resume.setUiDimension(new UiDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight() / 2, 220, 35));

        final UiSelectedTextButton exit = new UiSelectedTextButton("Exit Game", () -> {
            FileUtils.save(player, gameAPI);
            System.exit(0);
        });
        resume.setUiDimension(new UiDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight() / 2 + 35, 220, 35));

        uiManager.addComponent(base);
        uiManager.addComponent(resume);
        uiManager.addComponent(menu);
        uiManager.addComponent(exit);

        gameAPI.getMouseManager().setUiManager(uiManager);
    }

    public void tick() {
        if (gameAPI.getMouseManager().getUiManager() == null) gameAPI.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    public void render(Graphics g) {
        uiManager.render(g);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        gameAPI.getMouseManager().setUiManager(uiManager);
    }
}
