package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class kickCommand extends Command {

    public kickCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.kick") || sender.hasPermission("bungeesystem.*")) {
            if (args.length > 1) {
                final String target = args[0];
                String reason = "";
                for (int i = 1; i < args.length; ++i) {
                    reason = reason + args[i];
                }
                if (ProxyServer.getInstance().getPlayer(target) != null) {
                    final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(target);
                    if (player.hasPermission("bungeesystem.kick") || player.hasPermission("bungeesystem.*")) {
                        sender.sendMessage("§cDu kannst §e%target%§c nicht kicken!".replaceAll("%target%", target));
                        return;
                    }
                    for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                        if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*"))) {
                            players.sendMessage("§7Der Spieler §e%player% §7wurde von §e%target% §7für §e%reason% §7vom Netzwerk gekickt!".replaceAll("%player%", target).replaceAll("%reason%", reason).replaceAll("%target%", sender.getName()));
                        }
                    }
                    this.kickPlayer(target, reason, sender.getName());
                    sender.sendMessage("§aDu hast §e%target% §afür §e%reason% §avom Netzwerk gekickt!".replaceAll("%target%", target).replaceAll("%reason%", reason));
                } else {
                    sender.sendMessage("§cDer Spieler §e%target% §cist nicht online!".replaceAll("%target%", target));
                }
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/kick <Player> <Reason>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }

    public void kickPlayer(final String player, final String reason, final String team) {
        if (ProxyServer.getInstance().getPlayer(player) != null) {
            final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(player);
            target.disconnect("§6§lEhrenlosNet %newline% §cDu wurdest vom Netzwerk §egekickt! %newline% §7Grund §8» §e%reason% %newline% §7Gekickt von §8» §e%staff%".replaceAll("%newline%", "\n").replaceAll("%reason%", reason).replaceAll("%staff%", team));
        }
    }
}
