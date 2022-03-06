package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.manager.MuteManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String message = event.getMessage();
        if (MuteManager.isMuted(player.getUniqueId().toString()))
            if (!message.startsWith("/")) {
                long current = System.currentTimeMillis();
                long end = MuteManager.getEnd(player.getUniqueId().toString()).longValue();
                if (current < end || end == -1L) {
                    event.setCancelled(true);
                    player.sendMessage("");
                    player.sendMessage("§6§lEhrenlosNet");
                    player.sendMessage("§cDu wurdest auf dem Netzwerk §egemutet!");
                    player.sendMessage("§7Dauer §8» §e%time%".replaceAll("%time%", MuteManager.getRemainingTime(player.getUniqueId().toString())));
                    player.sendMessage("§7Grund §8» §e%reason%".replaceAll("%reason%", MuteManager.getReason(player.getUniqueId().toString())));
                    player.sendMessage("§7Gemutet von §8» §e%staff%".replaceAll("%staff%", MuteManager.getTeamMuted(MuteManager.getUUID(player.getName()))));
                    player.sendMessage("");
                } else {
                    MuteManager.unmute(player.getUniqueId().toString(), player.getName());
                }
            } else {
                event.setCancelled(false);
            }
    }
}
