package me.cadox8.deud.states;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.NysvaManager;
import me.cadox8.deud.ux.editor.Editor;

import java.awt.*;

public class EditorState extends State {

    private final NysvaManager nysvaManager;

    public EditorState(@NonNull GameAPI gameAPI) {
        super(gameAPI);
        final Editor editor = new Editor(gameAPI);
        this.nysvaManager = editor.getNysvaManager();

        gameAPI.getMouseManager().setNysvaUI(nysvaManager);
    }

    @Override
    public void tick() {
        nysvaManager.tick();
    }

    @Override
    public void render(Graphics g) {
        nysvaManager.render(g);
    }
}
