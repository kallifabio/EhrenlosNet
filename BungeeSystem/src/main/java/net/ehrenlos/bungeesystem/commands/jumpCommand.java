package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class jumpCommand extends Command {

    public jumpCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("bungeesystem.command.jump")) {
            if (args.length == 1) {
                if (ProxyServer.getInstance().getPlayer(args[0]) != null) {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                    player.connect(target.getServer().getInfo());
                    player.sendMessage(BungeeSystem.getPrefix() + "§7Du bist zu §e%target%§7 gesprungen!".replaceAll("%target%", target.getName()));
                } else {
                    player.sendMessage(BungeeSystem.getPrefix() + "§7Der Spieler §e%target% §7ist nicht §eonline§7!".replaceAll("%target%", args[0]));
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/jump <Spieler>"));
            }
        } else {
            player.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}

