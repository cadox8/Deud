package me.cadox8.deud.entities.creatures.npcs;

import lombok.Getter;
import me.cadox8.deud.ai.entities.FriendsAI;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Npc extends Creature {

    @Getter private final String name;
    @Getter private final List<String> text;
    @Getter private final List<Item> items;

    public Npc(API API, float x, float y, String name, BufferedImage[]... textures) {
        super(10, "Npc", API, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        this.name = name;
        this.text = new ArrayList<>();
        this.items = new ArrayList<>();

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 25;
        bounds.height = 19;

        // Animations
        animDown = new Animation((int)(speed * 600), textures[0]);
        animUp = new Animation((int)(speed * 600), textures[1]);
        animLeft = new Animation((int)(speed * 600), textures[2]);
        animRight = new Animation((int)(speed * 600), textures[3]);

        setMaxHealth(10);

        setDamage(DEFAULT_DAMAGE);
        setArmor(DEFAULT_ARMOR);

        setAttackCooldown(300);

        animations[0] = animDown;
        animations[1] = animUp;
        animations[2] = animLeft;
        animations[3] = animRight;

        ai = new FriendsAI(API, this, getSpeed(), 50);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public void die() {
        dropItem(items.get(0));
        items.remove(0);
        items.forEach(i -> dropItem(i, new Random().nextFloat()));
    }

    public Npc addTexts(String... texts) {
        this.text.addAll(Arrays.asList(texts));
        return this;
    }
    public Npc addItems(Item... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }
    public void setAngry(boolean angry) {
        ((FriendsAI)ai).setAngry(angry);
    }
}
