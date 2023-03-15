package com.rapid.itemsapi.SBItem.Ability;

public abstract class Ability {
    private boolean abilityFinished;
    protected int currentTicks;

    public Ability() {
        this.abilityFinished = false;
    }

    public abstract void abilityTick();
    public abstract void destroy();

    public boolean isFinished() {
        return this.abilityFinished;
    }

    public void endAbility() {
        this.abilityFinished = true;
    }
}
