package com.rapid.itemsapi.SBItem.Items;

import com.rapid.itemsapi.SBItem.Utils.Rarity;
import com.rapid.itemsapi.SBItem.SBItemAbility;
import com.rapid.itemsapi.SBItem.SBSword;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;

public class AspectOfTheEnd extends SBSword implements SBItemAbility {
    private int instantDistance = 8;
    private int etherwarpDistance = 59;
    private boolean canEtherwarp = true;

    public AspectOfTheEnd(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public void executeAbility(Event event) {
        PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;

        Player player = interactEvent.getPlayer();
        if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.isSneaking() && canEtherwarp) this.etherTransmission(player);
            else this.instantTransmission(player);
        }
    }

    private void instantTransmission(Player player) {
        Location playerLocation = player.getLocation();
        BlockIterator blockIterator = new BlockIterator(playerLocation, 1, this.instantDistance);
        blockIterator.next();
        Location targetLocation = player.getLocation();

        while (blockIterator.hasNext()) {
            Block currentBlock = blockIterator.next();
            if (!currentBlock.isEmpty()) {
                player.sendMessage(ChatColor.RED + "There are blocks in the way!");
                break;
            }
            targetLocation = currentBlock.getLocation();
        }

        targetLocation.setYaw(playerLocation.getYaw());
        targetLocation.setPitch(playerLocation.getPitch());
        targetLocation.add(0.5, 0, 0.5);
        player.teleport(targetLocation);
    }

    private void etherTransmission(Player player) {
        Location playerLocation = player.getEyeLocation();
        BlockIterator blockIterator = new BlockIterator(playerLocation, 0, this.etherwarpDistance);

        while (blockIterator.hasNext()) {
            Block currentBlock = blockIterator.next();
            if (!currentBlock.isEmpty()) {
                if (currentBlock.getRelative(0, 1, 0).isEmpty() && currentBlock.getRelative(0, 2, 0).isEmpty()) {
                    Location targetLocation = currentBlock.getLocation().add(0.5, 1, 0.5);
                    targetLocation.setPitch(playerLocation.getPitch());
                    targetLocation.setYaw(playerLocation.getYaw());
                    Bukkit.getScheduler().runTask(pluginReference, () -> player.teleport(targetLocation));
                }
                break;
            }
        }
    }
}
