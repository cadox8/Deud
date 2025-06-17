package es.cadox8.deud.managers;

import es.cadox8.deud.api.GameAPI;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.particles.Particle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ParticleManager {

    @Getter @Setter private GameAPI gameAPI;
    @Getter @Setter private static ArrayList<Particle> particles;

    private final Comparator<Particle> renderSorter = (Particle a, Particle b) -> {
        if (a.getPriority() < b.getPriority()) return -1;
        return 0;
    };

    public ParticleManager(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        particles = new ArrayList<>();
    }

    public void tick() {
        final Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            final Particle e = it.next();
            e.tick();
            if (!e.getAnimation().isEnd()) {
                it.remove();
                particles.remove(e);
            }
        }
        particles.sort(renderSorter);
    }

    public void render(Graphics g) {
        particles.forEach(e -> e.render(g));
    }

    public void addParticle(Particle e) {
        particles.add(e);
    }

    public void removeParticle(Particle en) {
        en.getAnimation().withEnd(true);
    }

    public void killAll() {
        particles.forEach(p -> p.getAnimation().withEnd(true));
    }

}
