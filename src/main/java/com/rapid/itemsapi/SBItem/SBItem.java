package com.rapid.itemsapi.SBItem;

import com.rapid.itemsapi.Main;
import com.rapid.itemsapi.SBItem.Utils.Rarity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SBItem {
    protected static Main pluginReference;
    private final String id, name;
    private final Rarity rarity;
    private final net.minecraft.server.v1_8_R3.ItemStack item;

    static {
        pluginReference = JavaPlugin.getPlugin(Main.class);
    }

    public SBItem(String id, String name, String[] lore, Material material, Rarity rarity) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;

        item = new net.minecraft.server.v1_8_R3.ItemStack(CraftMagicNumbers.getItem(Material.matchMaterial(material.name())), 1);

        // creating a tag which stores the item's lore and display name
        NBTTagCompound displayAttributes = new NBTTagCompound();
        displayAttributes.setString("Name", this.rarity.getColor() + this.name);
        // setting the lore of the item
        NBTTagList nbtTagList = new NBTTagList();
        for (String string : lore) {
            nbtTagList.add(new NBTTagString(string));
        }
        nbtTagList.add(new NBTTagString(this.getType()));
        displayAttributes.set("Lore", nbtTagList);

        NBTTagCompound extraAttributes = new NBTTagCompound();
        extraAttributes.setString("id", id);

        // creating a new tag compound and adding the item's data in it
        NBTTagCompound tag = new NBTTagCompound();
        // setting the tag so that it hides the damage dealt
        tag.setInt("HideFlags", 254);
        tag.set("display", displayAttributes);
        tag.set("ExtraAttributes", extraAttributes);

        item.setTag(tag);
    }

    /**
     * Gets the ItemStack for this item
     * @return an ItemStack for the item
     */
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(this.item);
    }

    /**
     * Gets the id for this SBItem
     * @return the item's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the name of this SBItem
     * @return the item's name
     */
    public String getName() {
        return this.name;
    }

    public String getType() {
        return "" + this.rarity.getColor() + ChatColor.BOLD + this.rarity;
    }
}
