package me.cadox8.deud.dialog;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.fonts.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Dialog {

    private final API api;
    private final Player player;

    private final List<String> text;
    private int page = 0;

    @Getter private boolean end = false;

    public Dialog(API api, Player p) {
        this.api = api;
        this.player = p;
        text = new ArrayList<>();
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

        if (api.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) page++;
        if (api.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            if (page == 0) return;
            page--;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, api.getHeight() - 100, api.getWidth(), 100);
        g.fillRect(0, api.getHeight() - 100, api.getWidth(), 100);

        int p = 1;
        for (String s : renderText()) {
            Text.drawString(g, s, 5, api.getHeight() - 97 + (p * 20), Color.WHITE, 2);
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
