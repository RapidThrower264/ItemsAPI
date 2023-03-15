package com.rapid.itemsapi.Listeners;

import com.rapid.itemsapi.SBItem.SBItem;
import com.rapid.itemsapi.SBItem.Ability.SBItemAbility;
import com.rapid.itemsapi.SBItem.SBItemManager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!event.getItem().hasItemMeta()) return;

        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getItem());
        NBTTagCompound compound = nmsItem.getTag().getCompound("ExtraAttributes");
        if (compound == null) return;

        SBItem foundItem = SBItemManager.getItem(compound.getString("id"));
        if (foundItem == null) return;
        if (!(foundItem instanceof SBItemAbility sbItemAbility)) return;

        sbItemAbility.executeAbility(event);
    }

    @EventHandler
    public void armorStandInteractEvent(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }
}
