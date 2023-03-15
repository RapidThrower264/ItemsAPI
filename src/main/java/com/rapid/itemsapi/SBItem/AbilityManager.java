package com.rapid.itemsapi.SBItem;

import com.rapid.itemsapi.Main;
import com.rapid.itemsapi.SBItem.Ability.Ability;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Iterator;

public class AbilityManager {
    private static BukkitTask task;
    private static final ArrayList<Ability> activeAbilities = new ArrayList<>();

    public static void initialiseAbilityManager() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                Iterator<Ability> abilityIterator = activeAbilities.iterator();
                while (abilityIterator.hasNext()) {
                    Ability ability = abilityIterator.next();
                    ability.abilityTick();

                    // removing the ability if it is finished
                    if (ability.isFinished()) {
                        ability.destroy();
                        abilityIterator.remove();
                    }
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 0, 1L);
    }

    public static void addAbility(Ability ability) {
        activeAbilities.add(ability);
    }

    public static void stopRunnable() {
        if (task != null)
            task.cancel();

        for (Ability ability : activeAbilities) {
            ability.destroy();
        }
        activeAbilities.clear();
    }
}
