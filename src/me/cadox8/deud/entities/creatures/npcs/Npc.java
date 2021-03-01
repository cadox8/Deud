package me.cadox8.deud.entities.creatures.npcs;

import lombok.Getter;
import lombok.NonNull;
import me.cadox8.deud.ai.entities.FriendsEntityAI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.inventory.creature.CreatureInventory;
import me.cadox8.deud.quests.Quest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Npc extends Creature {

    @Getter private final String displayName;
    @Getter private final List<String> text;

    private Quest quest;

    public Npc(@NonNull GameAPI gameAPI, float x, float y, String displayName, BufferedImage[]... textures) {
        super("fb32b568-aa91-46d9-99a9-fe1b90fb8599", "NPC", EntityData.EntityType.NPC, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        this.displayName = displayName;
        this.text = new ArrayList<>();

        inventory = new CreatureInventory(gameAPI);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 28;
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

        entityAi = new FriendsEntityAI(this, 50);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
    }

    @Override
    public void die() {
        dropItem(inventory.getItems().get(0));
        inventory.getItems().remove(0);
        inventory.getItems().forEach(i -> dropItem(i, new Random().nextFloat()));
    }

    public void addTexts(String... texts) {
        this.text.addAll(Arrays.asList(texts));
    }

    public void addQuest(Quest quest) {
        this.quest = quest;
    }
    public boolean hasQuest() {
        return this.quest != null;
    }
    public Quest getQuest() {
        return quest;
    }

    public void triggerQuest() {

    }
}
