package com.rapid.itemsapi.Commands;

import com.rapid.itemsapi.SBItem.SBItem;
import com.rapid.itemsapi.SBItem.SBItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCreationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command commandText, String command, String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player to send this");
            return true;
        }

        if (command.equalsIgnoreCase("giveitem")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "You need to supply an item id!");
                return true;
            }

            SBItem item = SBItemManager.getItem(args[0]);
            if (item == null) {
                player.sendMessage(ChatColor.RED + "This item doesn't exist!");
                return true;
            }

            player.sendMessage(ChatColor.GREEN + "Giving you a " + item.getRarity().getColor() + item.getName());
            player.getInventory().addItem(item.getItem());
            return true;
        }

        return true;
    }
}
