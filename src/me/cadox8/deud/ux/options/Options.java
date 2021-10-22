package me.cadox8.deud.ux.options;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.states.State;
import me.cadox8.deud.old_ui.NysvaManager;
import me.cadox8.deud.old_ui.components.base.UIBlock;
import me.cadox8.deud.old_ui.components.text.UISelectedTextButton;
import me.cadox8.deud.old_ui.helpers.NysvaColor;
import me.cadox8.deud.old_ui.helpers.UIDimension;

import java.awt.*;

public class Options {

    private final GameAPI gameAPI;

    private final NysvaManager nysvaManager;

    @Getter private boolean enabled = false;

    public Options(GameAPI gameAPI, Player player) {
        nysvaManager = new NysvaManager();
        this.gameAPI = gameAPI;

        final UIBlock base = new UIBlock(gameAPI, NysvaColor.DARK_GRAY.transparent(80));
        base.setUiDimension(new UIDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));

        final UISelectedTextButton resume = new UISelectedTextButton(gameAPI, "Resume Game", () -> setEnabled(false));

        final UISelectedTextButton menu = new UISelectedTextButton(gameAPI, "Main Menu", () -> {
            FileUtils.save(player, gameAPI);
            gameAPI.getMouseManager().setAarinManager(null);
            State.setState(gameAPI.getGame().getMenuState());
        });

        final UISelectedTextButton exit = new UISelectedTextButton(gameAPI, "Exit Game", () -> {
            FileUtils.save(player, gameAPI);
            System.exit(0);
        });

        exit.resizeFont(32);
        menu.resizeFont(32);
        resume.resizeFont(32);

        resume.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 35, 220, 35));
        menu.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2, 220, 35));
        exit.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 35, 220, 35));

        resume.getBaseBlock().setRoundRadius(15);
        menu.getBaseBlock().setRoundRadius(15);
        exit.getBaseBlock().setRoundRadius(15);

        resume.adjust();
        menu.adjust();
        exit.adjust();

        nysvaManager.addObject(base);
        nysvaManager.addObject(resume);
        nysvaManager.addObject(menu);
        nysvaManager.addObject(exit);

        gameAPI.getMouseManager().setAarinManager(nysvaManager);
    }

    public void tick() {
        if (gameAPI.getMouseManager().getAarinManager() == null) gameAPI.getMouseManager().setAarinManager(nysvaManager);
        nysvaManager.tick();
    }

    public void render(Graphics g) {
        nysvaManager.render(g);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        gameAPI.getMouseManager().setAarinManager(nysvaManager);
    }
}
