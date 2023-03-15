package com.rapid.itemsapi.SBItem.Items;

import com.rapid.itemsapi.Utils.Rarity;
import com.rapid.itemsapi.SBItem.SBItem;
import com.rapid.itemsapi.SBItem.Ability.SBItemAbility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;

public class PlumbersSponge extends SBItem implements SBItemAbility {
    public PlumbersSponge(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public void executeAbility(Event event) {
        PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;

        if (interactEvent.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        int currentAmount = interactEvent.getItem().getAmount();
        if (currentAmount > 1)
            interactEvent.getItem().setAmount(--currentAmount);
        else
            interactEvent.getPlayer().setItemInHand(null);

        BlockFace blockFace = interactEvent.getBlockFace();
        Location startingLocation = interactEvent.getClickedBlock().getLocation().add(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());

        final LinkedList<Location> targetBlockLocations = new LinkedList<>();
        targetBlockLocations.add(startingLocation);

        new BukkitRunnable() {
            int blocksCleared = 0;
            int currentIndex = 0;

            @Override
            public void run() {
                if (blocksCleared >= 20) {
                    System.out.println("cancelling....");
                    this.cancel();
                    return;
                }

                boolean foundBlock = false;
                Location location = null;

                while (!foundBlock) {
                    location = targetBlockLocations.get(currentIndex);
                    Block block = location.getBlock();
                    System.out.println(block.getType());

                    if (block.getType() == Material.STATIONARY_WATER) {
                        block.setType(Material.COAL_BLOCK);
                        blocksCleared++;
                        foundBlock = true;
                    } else {
                        if (currentIndex + 1 >= targetBlockLocations.size()) {
                            System.out.println("cancelling....");
                            this.cancel();
                            return;
                        }
                        currentIndex++;
                    }
                }

                Location xChange = new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ());
                targetBlockLocations.add(xChange);

                Location zChange = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1);
                targetBlockLocations.add(zChange);

                Bukkit.broadcastMessage("" + blocksCleared);
                currentIndex++;



            }
        }.runTaskTimer(pluginReference, 0, 1L);

        interactEvent.setCancelled(true);

//        BlockFace placedFace = interactEvent.getBlockFace();
//        Location placedBlockLocation = interactEvent.getClickedBlock().getLocation();
//        placedBlockLocation.add(placedFace.getModX(), placedFace.getModY(), placedFace.getModZ());
//
//        Bukkit.getScheduler().runTask(pluginReference, () -> placedBlockLocation.getBlock().setType(Material.AIR));

//        interactEvent.setCancelled(true);

//        interactEvent.setUseItemInHand(Event.Result.DENY);
//        interactEvent.setUseInteractedBlock(Event.Result.DENY);
//
    }

    private Location checkBlock(Location baseLocation, int x, int y, int z) {
        Location newLocation = baseLocation.clone().add(x, y, z);
        return newLocation;

//        newLocation.getBlock().setType(Material.GLASS);
//        return null;
    }
}

//                if (blocksCleared >= 20 || targetBlockLocations.size() == 0) {
//                    System.out.println(blocksCleared);
//                    System.out.println(targetBlockLocations.size());
//
//                    for (int i = 0; i < currentIndex; i++) {
//                        Location statedLocation = targetBlockLocations.get(i);
//                        statedLocation.getBlock().setType(Material.DIAMOND_ORE);
//                    }
//
//                    this.cancel();
//                    return;
//                }
//
//                Location currentLocation = targetBlockLocations.get(currentIndex);
//                Location location = new Location(currentLocation.getWorld(), currentLocation.getX() + 1, currentLocation.getY(), currentLocation.getZ());
//                if (location.getBlock().getType().equals(Material.STONE)) {
//                    targetBlockLocations.add(location);
//                } else {
//                    System.out.println("cannot add for some reason");
//                }
//
//                Location another = new Location(currentLocation.getWorld(), currentLocation.getX(), currentLocation.getY(), currentLocation.getZ() + 1);
//                if (another.getBlock().getType().equals(Material.STONE)) {
//                    targetBlockLocations.add(another);
//                } else {
//                    System.out.println("cannot add for some reason");
//                }
//
//
////                currentLocation.getBlock().setType(Material.DIAMOND_ORE);
//                currentIndex++;
////                targetBlockLocations.remove(0);
//                Bukkit.broadcastMessage("blocks cleared...." + blocksCleared);
//                blocksCleared++;




//                int addedBlocks = 0;
//                // checks all adjacent blocks
//                for (int i = 0; i < 6; i++) {
//                    int x = 0;
//                    int y = 0;
//                    int z = 0;
//
//                    if (i == 0) {
//                        x = 1;
//                    } else if (i == 1) {
//                        x = -1;
//                    } else if (i == 2) {
//                        y = 1;
//                    } else if (i == 3) {
//                        y = -1;
//                    } else if (i == 4) {
//                        z = 1;
//                    } else {
//                        z = -1;
//                    }
//
//                    Location newLocation = checkBlock(currentLocation, x, y, z);
//                    if (newLocation != null) {
//                        targetBlockLocations.add(newLocation);
//                        addedBlocks++;
//                    }
//


//                e++;
//                if (e > 20)
//                    this.cancel();
//                Bukkit.broadcastMessage("HEY GUYS " + e);
