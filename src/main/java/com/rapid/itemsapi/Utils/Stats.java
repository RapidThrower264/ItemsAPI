package com.rapid.itemsapi.Utils;

import org.bukkit.ChatColor;

public enum Stats {
    STRENGTH("Strength", ChatColor.RED, ChatColor.RED),
    CRITICAL_DAMAGE("Crit Damage", ChatColor.BLUE, ChatColor.RED),
    CRITICAL_CHANCE("Crit Chance", ChatColor.BLUE, ChatColor.RED),
    HEALTH("Health", ChatColor.RED, ChatColor.GREEN);

    private final String fancyName;
    private final ChatColor color, itemStatColor;

    Stats(String fancyName, ChatColor color, ChatColor itemStatColor) {
        this.fancyName = fancyName;
        this.color = color;
        this.itemStatColor = itemStatColor;
    }

    public String getFancyName() {
        return this.fancyName;
    }

    public ChatColor getColor() {
        return this.color;
    }
}
