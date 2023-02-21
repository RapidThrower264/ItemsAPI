package com.rapid.itemsapi.SBItem.Items;

import com.rapid.itemsapi.Player.SBPlayer;
import com.rapid.itemsapi.Player.SBPlayerManager;
import com.rapid.itemsapi.SBItem.Ability.Ability;
import com.rapid.itemsapi.SBItem.AbilityManager;
import com.rapid.itemsapi.SBItem.SBItemAbility;
import com.rapid.itemsapi.SBItem.SBSword;
import com.rapid.itemsapi.SBItem.Utils.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BetterShield extends SBSword implements SBItemAbility {
    private static final String ABILITY_NAME = "BETTER_SHIELD";
    public BetterShield(String id, String name, String[] lore, Material material, Rarity rarity) {
        super(id, name, lore, material, rarity);
    }

    @Override
    public void executeAbility(Event event) {
        PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;

        SBPlayer sbPlayer = SBPlayerManager.getPlayer(interactEvent.getPlayer());
        BetterShieldAbility ability = (BetterShieldAbility) sbPlayer.getAbility(ABILITY_NAME);
        if (ability == null) {
            sbPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Activated your Better Shield.");
        }
        AbilityManager.addAbility(new BetterShieldAbility(player));




        //
    }
}

class BetterShieldAbility extends Ability {
    private Player targetPlayer;
    private ArmorStand armorStand;
    public BetterShieldAbility(Player player) {
        super(player.getWorld());

        this.targetPlayer = player;

        Location spawnLocation = player.getLocation();
        spawnLocation.add(1, 0, 0);

        armorStand = (ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
        armorStand.setHelmet(new ItemStack(Material.GOLD_HELMET));
        armorStand.setGravity(false);
    }

    @Override
    public void abilityTick() {
        double x, y, z;
        Location playerLocation = this.targetPlayer.getLocation();
        x = Math.sin(Math.toRadians(playerLocation.getYaw()));
        y = Math.sin(Math.toRadians(playerLocation.getPitch()));
        z = Math.cos(Math.toRadians(playerLocation.getYaw()));

        playerLocation.add(x * -3, y * -3, z * 3);

        armorStand.teleport(playerLocation);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void destroy() {

    }
}
