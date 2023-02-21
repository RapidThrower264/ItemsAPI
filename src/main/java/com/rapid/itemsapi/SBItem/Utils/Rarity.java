package com.rapid.itemsapi.SBItem.Utils;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON("Common", ChatColor.WHITE),
    UNCOMMON("Uncommon", ChatColor.GREEN),
    RARE("Rare", ChatColor.BLUE),
    EPIC("Epic", ChatColor.DARK_PURPLE),
    LEGENDARY("Legendary", ChatColor.YELLOW),
    MYTHIC("Mythic", ChatColor.LIGHT_PURPLE),
    SPECIAL("Special", ChatColor.RED),
    EARLY_DEVELOPMENT("Early Development", ChatColor.DARK_RED);

    private final String name;
    private final ChatColor color;

    Rarity(String fancyName, ChatColor color) {
        this.name = fancyName;
        this.color = color;
    }

    public ChatColor getColor() { return this.color; }
    public String getFullColor(boolean isCapital) {
        if (isCapital) return this.color + this.toString();
        else return this.color + this.name;
    }
    String getName() { return this.name; }
}