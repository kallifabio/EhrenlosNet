package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.OnlineTimeManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class onlinetimeCommand extends Command {

    public onlinetimeCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            int time = OnlineTimeManager.getTime(player.getUniqueId());
            boolean isHour = (time >= 60);
            if (isHour) {
                time /= 60;
                player.sendMessage(BungeeSystem.getPrefix() + "§7Du hast eine §eOnlinezeit §7von §e%time% Stunde(n)§7!".replaceAll("%time%", String.valueOf(time)));
            } else {
                player.sendMessage(BungeeSystem.getPrefix() +
                        "§7Du hast eine §eOnlinezeit §7von §e%time% Minute(n)§7!".replaceAll("%time%", String.valueOf(time)));
            }
        } else if (args.length == 1) {
            String target = args[0];
            if (target != null) {
                int time = OnlineTimeManager.getTime(player.getUniqueId());
                boolean isHour = (time >= 60);
                if (time > 1) {
                    if (isHour) {
                        time /= 60;
                        player.sendMessage(BungeeSystem.getPrefix() + "§7Der Spieler §e%target% §7hat eine §eOnlinezeit §7von §e%time% Stunde(n)§7!"
                                .replaceAll("%time%", String.valueOf(time)).replaceAll("%target%", args[0]));
                    } else {
                        player.sendMessage(BungeeSystem.getPrefix() + "§7Der Spieler §e%target% §7hat eine §eOnlinezeit §7von §e%time% Minute(n)§7!"
                                .replaceAll("%time%", String.valueOf(time)).replaceAll("%target%", args[0]));
                    }
                } else {
                    player.sendMessage(BungeeSystem.getPrefix() + "§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", args[0]));
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", args[0]));
            }
        } else {
            player.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/onlinetime"));
        }
    }


}
