package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.MuteManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class unmuteCommand extends Command {

    public unmuteCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.unmute") || sender.hasPermission("bungeesystem.*")) {
            if (args.length == 1) {
                final String target = args[0];
                if (MuteManager.playerExistsMuted(MuteManager.getUUID(target))) {
                    if (!MuteManager.isMuted(MuteManager.getUUID(target), target)) {
                        sender.sendMessage("§cDer Spieler §e%target% §cist nicht gemutet!".replaceAll("%target%", target));
                    } else {
                        MuteManager.unmute(MuteManager.getUUID(target), target);
                        sender.sendMessage("§7Du hast §e%target%§7 entmutet!".replaceAll("%target%", target));
                        for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                            if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*"))) {
                                players.sendMessage("§7Der Spieler §e%player% §7wurde von §e%staff%§7 entmutet!".replaceAll("%player%", target).replaceAll("%staff%", String.valueOf(sender)));
                            }
                        }
                    }
                } else {
                    sender.sendMessage("§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", target));
                }
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/unmute <Player>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
