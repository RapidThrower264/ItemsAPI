package com.rapid.itemsapi.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerMiscListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setExhaustion(0.0f);
        player.setSaturation(20f);
    }

    @EventHandler
    public void onPlayerFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
