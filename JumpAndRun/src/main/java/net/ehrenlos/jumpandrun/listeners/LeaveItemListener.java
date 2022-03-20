package net.ehrenlos.jumpandrun.listeners;

import net.ehrenlos.jumpandrun.JumpAndRun;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LeaveItemListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§cSpiel verlassen") && player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getType() == Material.SLIME_BALL) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF("Lobby-2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendPluginMessage(JumpAndRun.getInstance(), "BungeeCord", b.toByteArray());
                player.sendMessage(JumpAndRun.getPrefix() + "§7Du wirst mit Lobby-2 verbunden");
                event.setCancelled(true);
            }
        }
    }
}
