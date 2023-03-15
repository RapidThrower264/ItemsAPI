package com.rapid.itemsapi.Player;

import com.rapid.itemsapi.SBItem.Ability.Ability;
import com.rapid.itemsapi.SBItem.AbilityManager;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SBPlayer {
    private final Player player;
    private final HashMap<String, Ability> abilityData;

    public SBPlayer(Player player) {
        this.player = player;

        this.abilityData = new HashMap<>();
    }

    public Player getPlayer() {
        return this.player;
    }

    public Ability getAbility(String abilityName) {
        return abilityData.get(abilityName);
    }
    public void removeAbility(String abilityName) {
        this.abilityData.remove(abilityName);
    }

    public void registerAbility(String abilityName, Ability ability) {
        abilityData.put(abilityName, ability);
        AbilityManager.addAbility(ability);
    }
}
