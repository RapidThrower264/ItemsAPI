package com.rapid.itemsapi;

import com.rapid.itemsapi.Commands.ItemCreationCommand;
import com.rapid.itemsapi.Listeners.DamageListener;
import com.rapid.itemsapi.Listeners.PlayerInteractListener;
import com.rapid.itemsapi.Listeners.PlayerMiscListener;
import com.rapid.itemsapi.Player.SBPlayerManager;
import com.rapid.itemsapi.SBItem.AbilityManager;
import com.rapid.itemsapi.SBItem.SBItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Initialising the ItemsAPI Plugin");

        SBItemManager.initialiseItemManager();
        SBPlayerManager.initialisePlayerManager();
        AbilityManager.initialiseAbilityManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new PlayerMiscListener(), this);
        pluginManager.registerEvents(new DamageListener(), this);

        getCommand("giveitem").setExecutor(new ItemCreationCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Shutting the ItemsAPI Plugin down");

        AbilityManager.stopRunnable();
    }
}
