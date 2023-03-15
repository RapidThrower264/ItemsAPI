package com.rapid.itemsapi.SBItem.Sword;

import com.rapid.itemsapi.SBItem.SBItem;
import com.rapid.itemsapi.Utils.Rarity;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.Material;

import java.util.ArrayList;

public class SBSword extends SBItem {
    public SBSword(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public SBItem createFromItem(ItemStack originalItem) {
        return null;
    }

    public void getLore(ArrayList<String> currentLore) {

    }

    @Override
    public String getType() {
        return super.getType() + " SWORD";
    }
}
