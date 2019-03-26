package me.cadox8.deud.dialog;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Dialog {

    private final GameAPI gameApi;
    private final Player player;
    private final Npc npc;

    private final List<String> text;
    private int page = 0;

    @Getter private boolean end = false;

    public Dialog(GameAPI gameApi, Player p) {
        this(gameApi, p, null);
    }
    public Dialog(GameAPI gameApi, Player p, Npc npc) {
        this.gameApi = gameApi;
        this.player = p;
        this.npc = npc;
        text = npc == null ? new ArrayList<>() : npc.getText();
    }

    public Dialog addText(List<String> newText) {
        final List<String> tempList = new ArrayList<>();
        newText.forEach(t -> tempList.add(t.replaceAll("%player%", player.getNick())));
        text.addAll(tempList);
        return this;
    }


    public void tick() {
        if (!end) {
            player.setFreeze(true);
        } else {
            player.setFreeze(false);
        }

        if (gameApi.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) page++;
        if (gameApi.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            if (page == 0) return;
            page--;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(GUI.dialog, 0, gameApi.getHeight() - 100, gameApi.getWidth(), 100, null);

        int p = 1;
        for (String s : renderText()) {
            s = s.replaceAll("%npc%", npc == null ? "" : npc.getDisplayName()).replaceAll("%player%", player.getNick());
            Text.drawString(g, s, 80, gameApi.getHeight() - 97 + (p * 20), Color.WHITE, 2);
            p++;
        }
    }

    private List<String> renderText() {
        final List<String> temp = new ArrayList<>();
        int index = 0;

        switch (page) {
            case 0:
                index = 0;
                break;
            case 1:
                index = 4;
                break;
            case 2:
                index = 8;
                break;
        }

        try {
            temp.addAll(text.subList(index, text.size() >= (index + 4) ? index + 4 : text.size()));
        } catch (IllegalArgumentException e) { // Problems? Nope
            end = true;
        }
        if (temp.isEmpty()) end = true;
        if (end) return new ArrayList<>();
        return temp;
    }
}
