package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class pingCommand extends Command {

    public pingCommand(String name) {
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
            player.sendMessage(BungeeSystem.getPrefix() + "§7Du hast ein §ePing §7von §e%ping%ms§7!".replaceAll("%ping%", String.valueOf(player.getPing())));
        } else if (args.length == 1) {
            if (ProxyServer.getInstance().getPlayer(args[0]) != null) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                player.sendMessage(BungeeSystem.getPrefix() + "§7Der Spieler §e%target% §7hat einen §ePing §7von §e%ping%ms§7!".replaceAll("%target%", String.valueOf(target))
                        .replaceAll("%ping%", String.valueOf(target.getPing())));
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§7Der Spieler §e%target% §7ist nicht §eonline§7!".replaceAll("%target%", args[0]));
            }
        } else if (args.length >= 2) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/ping"));
        }
    }
}
