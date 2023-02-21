package com.rapid.itemsapi.SBItem;

import com.rapid.itemsapi.SBItem.Utils.Rarity;
import org.bukkit.Material;

public class SBSword extends SBItem {
    public SBSword(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public String getType() {
        return super.getType() + " SWORD";
    }
}
