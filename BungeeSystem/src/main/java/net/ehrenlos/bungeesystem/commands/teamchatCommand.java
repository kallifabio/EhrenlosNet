package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class teamchatCommand extends Command {

    public teamchatCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        final ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("bungeesystem.teamchat")) {
            if (args.length == 0) {
                player.sendMessage("§cTeamChat §8| §6Verwende§7: /tc <Nachricht>");
            } else {
                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";
                }

                for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                    if (all.hasPermission("bungeesystem.teamchat"))
                        all.sendMessage("§cTeamChat §8| §6" + player.getName() + " §7: §e" + message);
                }
            }
        } else {
            player.sendMessage("§7[§6System§7] Unbekannter Befehl");
        }
    }

}
