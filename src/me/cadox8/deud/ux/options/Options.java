package me.cadox8.deud.ux.options;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.components.text.UISelectedTextButton;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;
import me.cadox8.deud.saves.FileUtils;

import java.awt.*;

public class Options {

    private GameAPI gameAPI;

    private NysvaManager nysvaManager;

    @Getter @Setter private boolean enabled = false;

    public Options(GameAPI gameAPI, Player player) {
        nysvaManager = new NysvaManager();
        this.gameAPI = gameAPI;

        final UIBlock base = new UIBlock(gameAPI, NysvaColor.DARK_GRAY.transparent(80));
        base.setUiDimension(new UIDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));

        final UISelectedTextButton resume = new UISelectedTextButton(gameAPI, "Resume Game", () -> setEnabled(false));

        final UISelectedTextButton exit = new UISelectedTextButton(gameAPI, "Exit Game", () -> {
            FileUtils.save(player);
            System.exit(0);
        });

        exit.resizeFont(32);
        resume.resizeFont(32);

        resume.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 35, 220, 35));
        exit.setUiDimension(new UIDimension(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 35, 150, 35));

        resume.getBaseBlock().setRoundRadius(15);
        exit.getBaseBlock().setRoundRadius(15);

        nysvaManager.addObject(base);
        nysvaManager.addObject(resume);
        nysvaManager.addObject(exit);

        gameAPI.getMouseManager().setNysvaUI(nysvaManager);
    }

    public void tick() {
        if (gameAPI.getMouseManager().getNysvaUI() == null) gameAPI.getMouseManager().setNysvaUI(nysvaManager);
        nysvaManager.tick();
    }

    public void render(Graphics g) {
        nysvaManager.render(g);
    }
}
