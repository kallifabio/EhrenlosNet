package net.ehrenlos.lobbysystem.listeners;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.HashMap;

public class EnderpearlListener implements Listener {

    private final HashMap<Player, EnderPearl> enderPearls = new HashMap<>();
    Player player;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        player = event.getPlayer();

        if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getType() == Material.ENDER_PEARL) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);

                player.getInventory().setItem(2, new ItemBuilder(Material.LEGACY_FIREWORK_CHARGE).setDisplayName("§7§lEnderperle").build());
                player.updateInventory();

                EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(player);

                enderPearls.put(player, enderPearl);

                Bukkit.getScheduler().runTaskLater(LobbySystem.getInstance(), () -> {
                    player.getInventory().setItem(2, new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§aEnderperle").build());
                    player.updateInventory();
                }, 20 * 5);
            }
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (event.getExited() instanceof Player) {
            if (enderPearls.containsKey(event.getExited())) {
                enderPearls.get(event.getExited()).remove();
            }
        }
    }
}
