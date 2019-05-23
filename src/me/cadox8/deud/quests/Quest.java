package me.cadox8.deud.quests;

import lombok.Getter;

public abstract class Quest {

    @Getter protected final int id;
    @Getter protected final String name;
    @Getter protected final String description;
    @Getter protected final QuestType type;

    public Quest(int id, String name, String description, QuestType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    protected void check() {

    }

    public enum QuestType {
        GO, KILL, SELL, FIND, OPEN;
    }
}
