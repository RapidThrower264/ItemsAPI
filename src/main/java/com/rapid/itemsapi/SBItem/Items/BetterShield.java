package com.rapid.itemsapi.SBItem.Items;

import com.rapid.itemsapi.Player.SBPlayer;
import com.rapid.itemsapi.Player.SBPlayerManager;
import com.rapid.itemsapi.SBItem.Ability.Ability;
import com.rapid.itemsapi.SBItem.Ability.SBItemAbility;
import com.rapid.itemsapi.SBItem.Sword.SBSword;
import com.rapid.itemsapi.Utils.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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
            ability = new BetterShieldAbility(sbPlayer);
            sbPlayer.registerAbility(ABILITY_NAME, ability);
            sbPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Activated your Better Shield.");
        } else {
            ability.endAbility();
            sbPlayer.removeAbility(ABILITY_NAME);
            sbPlayer.getPlayer().sendMessage(ChatColor.RED + "Deactivated your Better Shield.");
        }
    }
}

class BetterShieldAbility extends Ability {
    private final Player targetPlayer;
    private final ArmorStand armorStand;
    public BetterShieldAbility(SBPlayer player) {
        super();

        this.targetPlayer = player.getPlayer();

        Location spawnLocation = this.targetPlayer.getLocation();

        armorStand = (ArmorStand) player.getPlayer().getWorld().spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
        armorStand.setHelmet(new ItemStack(Material.GOLD_HELMET));
        armorStand.setGravity(false);
        armorStand.setMarker(true);
    }

    @Override
    public void abilityTick() {
        Location playerLocation = this.targetPlayer.getLocation();
        Vector playerLookAngle = playerLocation.getDirection().multiply(3);

        playerLocation.add(playerLookAngle);
        armorStand.teleport(playerLocation);
    }

    @Override
    public void destroy() {
        armorStand.remove();
    }
}
