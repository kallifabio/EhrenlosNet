package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.BanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class unbanCommand extends Command {

    public unbanCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.unban") || sender.hasPermission("bungeesystem.*")) {
            if (args.length == 1) {
                final String target = args[0];
                if (BanManager.playerExistsBanned(BanManager.getUUID(target))) {
                    if (!BanManager.isBanned(BanManager.getUUID(target), target)) {
                        sender.sendMessage("§cDer Spieler §e%target% §cist nicht gebannt!".replaceAll("%target%", target));
                    } else {
                        BanManager.unban(BanManager.getUUID(target), target);
                        sender.sendMessage("§7Du hast §e%target%§7 entbannt!".replaceAll("%target%", target));
                        for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                            if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*"))) {
                                players.sendMessage("§7Der Spieler §e%player% §7wurde von §e%staff%§7 entbannt!".replaceAll("%player%", target).replaceAll("%staff%", String.valueOf(sender)));
                            }
                        }
                    }
                } else {
                    sender.sendMessage("§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", target));
                }
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/unban <Player>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
