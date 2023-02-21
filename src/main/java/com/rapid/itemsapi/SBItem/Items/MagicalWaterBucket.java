package com.rapid.itemsapi.SBItem.Items;

import com.rapid.itemsapi.SBItem.Utils.Rarity;
import com.rapid.itemsapi.SBItem.SBItem;
import com.rapid.itemsapi.SBItem.SBItemAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MagicalWaterBucket extends SBItem implements SBItemAbility {
    public MagicalWaterBucket(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public void executeAbility(Event event) {
        PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;

        if (interactEvent.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        interactEvent.setUseItemInHand(Event.Result.DENY);

        Location location = interactEvent.getClickedBlock().getLocation();
        BlockFace blockFace = interactEvent.getBlockFace();
        location.add(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());
        location.getBlock().setType(Material.WATER);
    }
}
