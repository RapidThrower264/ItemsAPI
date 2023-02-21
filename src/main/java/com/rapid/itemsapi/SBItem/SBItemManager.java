package com.rapid.itemsapi.SBItem;

import com.rapid.itemsapi.SBItem.Items.*;
import com.rapid.itemsapi.SBItem.Utils.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;

public class SBItemManager {
    private static final HashMap<String, SBItem> SB_ITEMS = new HashMap<>();

    public static void initialiseItemManager() {
        SBItem item = new SBItem("test_item", "Test Item", new String[] {ChatColor.WHITE + "This item is really just a test."}, Material.ANVIL, Rarity.COMMON);
        addItem(item);

        AspectOfTheEnd aote = new AspectOfTheEnd("ASPECT_OF_THE_END", "Aspect of the End", new String[] {}, Material.DIAMOND_SWORD, Rarity.RARE);
        addItem(aote);

        MagicalWaterBucket wb = new MagicalWaterBucket("MAGICAL_WATER_BUCKET", "Magical Water Bucket", new String[] {}, Material.WATER_BUCKET, Rarity.COMMON);
        addItem(wb);

        DirtStick stick = new DirtStick("DIRT_STICK", "Dirt Stick", new String[] {}, Material.STICK, Rarity.UNCOMMON);
        addItem(stick);

        PlumbersSponge sponge = new PlumbersSponge("PLUMBERS_SPONGE", "Plumber's Sponge", new String[] {}, Material.SPONGE, Rarity.EARLY_DEVELOPMENT);
        addItem(sponge);

        BetterShield shield = new BetterShield("BETTER_SHIELD", "Better Shield", new String[] {}, Material.GOLD_HOE, Rarity.EARLY_DEVELOPMENT);
        addItem(shield);

        System.out.println("done the end of stuff.");
    }

    public static SBItem getItem(String id) {
        return SB_ITEMS.get(id);
    }

    public static void addItem(SBItem item) {
        SB_ITEMS.put(item.getId(), item);
    }
}
