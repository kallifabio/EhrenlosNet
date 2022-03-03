package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.BanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class banCommand extends Command {

    public banCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.ban") || sender.hasPermission("bungeesystem.*")) {
            if (args.length >= 2) {
                final String target = args[0];
                String reason = "";
                for (int i = 1; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                if (BanManager.playerExistsBanned(BanManager.getUUID(target))) {
                    if (ProxyServer.getInstance().getPlayer(target) != null) {
                        final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(target);
                        if (player.hasPermission("bungeesystem.ban") || player.hasPermission("bungeesystem.*")) {
                            sender.sendMessage("§cDu kannst §e%target%§c nicht bannen!".replaceAll("%target%", target));
                            return;
                        }
                    }
                    if (BanManager.isBanned(BanManager.getUUID(target), target)) {
                        sender.sendMessage("§cDer Spieler §e%target% §cist bereits gebannt!".replaceAll("%target%", target));
                        return;
                    }
                    BanManager.ban(sender.getName(), BanManager.getUUID(target), target, reason, -1L);
                    sender.sendMessage("§aDu hast §e%target% §afür §e%reason% §e%time% §avom Netzwerk gebannt!".replaceAll("%target%", target).replaceAll("%reason%", reason).replaceAll("%time%", "§ePERMANENT"));
                    for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                        if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*"))) {
                            players.sendMessage("§7Der Spieler §e%player% §7wurde von §e%staff% §7für §e%reason% §e%time% §7vom Netzwerk gebannt!".replaceAll("%player%", target).replaceAll("%staff%", sender.getName()).replaceAll("%reason%", reason).replaceAll("%time%", "§ePERMANENT"));
                        }
                    }
                } else {
                    sender.sendMessage("§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", target));
                }
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/ban <Player> <Reason>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
