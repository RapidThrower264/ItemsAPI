package com.rapid.itemsapi.SBItem;

import com.rapid.itemsapi.Main;
import com.rapid.itemsapi.Utils.Rarity;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SBItem implements Cloneable {
    protected static Main pluginReference;
    private final String id, name;
    private final Rarity rarity;
    private final ItemStack item;
    private final NBTTagCompound nbt;

    static {
        pluginReference = JavaPlugin.getPlugin(Main.class);
    }

    public SBItem(String id, String name, String[] lore, Material material, Rarity rarity) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;

        item = new ItemStack(CraftMagicNumbers.getItem(Material.matchMaterial(material.name())), 1);
        // creating a new tag compound and adding the item's data in it
        NBTTagCompound tag = new NBTTagCompound();
        this.nbt = tag;

        // creating a tag which stores the item's lore and display name
        NBTTagCompound displayAttributes = new NBTTagCompound();
        displayAttributes.setString("Name", this.rarity.getColor() + this.name);

        NBTTagCompound extraAttributes = new NBTTagCompound();
        extraAttributes.setString("id", id);

        // setting the lore of the item
        NBTTagList nbtTagList = new NBTTagList();
        for (String string : lore) {
            nbtTagList.add(new NBTTagString(string));
        }
        nbtTagList.add(new NBTTagString(this.getType()));
        displayAttributes.set("Lore", nbtTagList);

        // setting the tag so that it hides the damage dealt
        tag.setInt("HideFlags", 254);
        tag.set("display", displayAttributes);
        tag.set("ExtraAttributes", extraAttributes);

        // setting the tag to the item
        item.setTag(tag);
    }

    protected SBItem(String id, String name, Rarity rarity, ItemStack itemStack) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;

        this.item = itemStack;
        this.nbt = this.item.getTag();
    }

    public SBItem createFromItem(ItemStack itemStack) {
        return new SBItem(this.id, this.name, this.rarity, itemStack);
    }


    /**
     * Gets the ItemStack for this item
     * @return an ItemStack for the item
     */
    public org.bukkit.inventory.ItemStack getItem() {
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

    /**
     * Gets the rarity of this SBItem
     * @return the item's rarity
     */
    public Rarity getRarity() {
        return this.rarity;
    }

    public String getType() {
        return "" + this.rarity.getFullColor(true) + ChatColor.BOLD + this.rarity;
    }

    protected void getLore(ArrayList<String> currentLore) {}
}
