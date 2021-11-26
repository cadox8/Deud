package es.cadox8.deud.ux.options;

import es.cadox8.deud.api.GameAPI;
import lombok.Getter;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.saves.FileUtils;
import es.cadox8.deud.states.State;
import es.cadox8.deud.ui.AarinManager;
import es.cadox8.deud.ui.components.base.UIBlock;
import es.cadox8.deud.ui.components.text.UISelectedText;
import es.cadox8.deud.ui.helpers.AarinArea;
import es.cadox8.deud.ui.helpers.AarinColor;

import java.awt.*;

public class Options {

    private final GameAPI gameAPI;

    private final AarinManager aarinManager;

    @Getter private boolean enabled = false;

    public Options(GameAPI gameAPI, Player player) {
        aarinManager = new AarinManager();
        this.gameAPI = gameAPI;

        final UIBlock base = new UIBlock(gameAPI, AarinColor.DARK_GRAY.transparent(80), true);
        base.setArea(new AarinArea().addPoints(new Point(0, 0), new Point(gameAPI.getWidth(), gameAPI.getHeight())));

        final UISelectedText resume = new UISelectedText(gameAPI, AarinColor.TRANSPARENT, true, "Resume Game", () -> this.setEnabled(false));

        final UISelectedText menu = new UISelectedText(gameAPI, AarinColor.TRANSPARENT, true, "Main Menu", () -> {
            FileUtils.save(player, gameAPI);
            gameAPI.getMouseManager().setAarinManager(null);
            State.setState(gameAPI.getGame().getMenuState());
        });

        final UISelectedText exit = new UISelectedText(gameAPI, AarinColor.TRANSPARENT, true, "Exit Game", () -> {
            FileUtils.save(player, gameAPI);
            System.exit(0);
        });

/*        exit.resizeFont(32);
        menu.resizeFont(32);
        resume.resizeFont(32);*/

        resume.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 - 35), new Point(220, 35)));
        menu.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2), new Point(220, 35)));
        exit.setArea(new AarinArea().addPoints(new Point(gameAPI.getWidth() / 2 - (250 / 2), gameAPI.getHeight()/2 + 35), new Point(220, 35)));

        aarinManager.addObject(base);
        aarinManager.addObject(resume);
        aarinManager.addObject(menu);
        aarinManager.addObject(exit);

        gameAPI.getMouseManager().setAarinManager(aarinManager);
    }

    public void tick() {
        if (gameAPI.getMouseManager().getAarinManager() == null) gameAPI.getMouseManager().setAarinManager(aarinManager);
        aarinManager.tick();
    }

    public void render(Graphics g) {
        aarinManager.render(g);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        gameAPI.getMouseManager().setAarinManager(aarinManager);
    }
}
