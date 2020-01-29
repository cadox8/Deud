package me.cadox8.deud.states;

import lombok.NonNull;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.audio.Sound;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.components.images.UIImage;
import me.cadox8.deud.nysvaui.components.images.UIImageButton;
import me.cadox8.deud.nysvaui.components.text.UITextButton;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Updater;
import me.cadox8.deud.ux.editor.Editor;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
