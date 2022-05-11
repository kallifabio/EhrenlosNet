package net.ehrenlos.trollsystem.listeners;

import net.ehrenlos.trollsystem.TrollSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String name = "";
        Player target = Bukkit.getPlayer(name);
        if (TrollSystem.getInstance().freezePlayer.contains(event.getPlayer())) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
}
