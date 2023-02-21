package com.rapid.itemsapi.SBItem.Ability;

import org.bukkit.World;

public abstract class Ability {
    protected int currentTicks;
    protected World world;

    public Ability(World world) {
        this.world = world;
    }

    public abstract void abilityTick();
    public abstract boolean isFinished();
    public abstract void destroy();
}
