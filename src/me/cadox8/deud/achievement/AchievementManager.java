package me.cadox8.deud.achievement;

import java.util.ArrayList;
import java.util.List;

public class AchievementManager {

    private List<Achievement> achievements;

    public AchievementManager() {
        achievements = new ArrayList<>();
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public void deleteAchievement(Achievement achievement) {
        achievements.remove(achievement);
    }

    public Achievement getAchievement(int id) {
        return achievements.stream().filter(a -> a.getId() == id).findAny().orElse(null);
    }
}
