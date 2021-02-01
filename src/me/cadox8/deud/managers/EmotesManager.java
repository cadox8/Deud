package me.cadox8.deud.managers;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ux.emotions.Emotion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EmotesManager {

    @Getter @Setter private GameAPI gameAPI;
    @Getter @Setter private static ArrayList<Emotion> emotions;

    public EmotesManager(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        emotions = new ArrayList<>();
    }

    public void tick() {
        final Iterator<Emotion> it = emotions.iterator();
        while (it.hasNext()) {
            final Emotion e = it.next();
            e.tick();
            if (!e.getAnim().isEnd()) {
                it.remove();
                emotions.remove(e);
            }
        }
    }

    public void render(Graphics g) {
        emotions.forEach(e -> e.render(g));
    }

    public void addParticle(Emotion e) {
        emotions.add(e);
    }

    public void removeParticle(Emotion en) {
        en.getAnim().withEnd(true);
    }

    public void killAll() {
        emotions.forEach(p -> p.getAnim().withEnd(true));
    }

}
