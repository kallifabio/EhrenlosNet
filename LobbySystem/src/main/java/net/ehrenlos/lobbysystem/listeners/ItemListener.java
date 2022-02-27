package net.ehrenlos.lobbysystem.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemListener implements Listener {

    Player player;

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        player = (Player) event.getWhoClicked();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }
}
