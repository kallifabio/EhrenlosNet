package net.ehrenlos.befizzi.manager;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionbarManager {

    private static void sendActionbar(final Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static void updateActionBar() {
        final SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        final String message = "&8&lEs ist &f&l» &a" + simpleDateFormatTime.format(new Date());

        Bukkit.getOnlinePlayers().forEach(current -> sendActionbar(current, message));
    }
}
