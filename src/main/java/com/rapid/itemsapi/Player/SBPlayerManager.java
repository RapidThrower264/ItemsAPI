package com.rapid.itemsapi.Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
public class SBPlayerManager {
    private static final HashMap<UUID, SBPlayer> playerMap = new HashMap<>();

    public static void initialisePlayerManager() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerMap.put(player.getUniqueId(), new SBPlayer(player));
        }
    }

    public static SBPlayer getPlayer(Player selectedPlayer) {
        return playerMap.computeIfAbsent(selectedPlayer.getUniqueId(), newPlayer -> new SBPlayer(selectedPlayer));
    }

    public static void addPlayer(Player selectedPlayer) {
        playerMap.computeIfAbsent(selectedPlayer.getUniqueId(), newPlayer -> new SBPlayer(selectedPlayer));
    }

    public static void removePlayer(UUID uuid) {
        playerMap.remove(uuid);
    }
}
