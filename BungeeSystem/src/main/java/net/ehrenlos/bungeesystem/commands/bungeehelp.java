package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class bungeehelp extends Command {

    public bungeehelp(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        final ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            if (player.hasPermission("bungeesystem.command.bungeehelp")) {
                player.sendMessage(BungeeSystem.getPrefix() + "§e/broadcast §8- §7Mache einen Rundruf");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/coins §8- §7Sehe deine Coins");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/joinme §8- §7Sende eine Nachricht zum nachjoinen");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/jump §8- §7Springe zu einem Spieler");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/onlinetime §8- §7Sehe deine Spielzeit");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/onlinetimetop10 §8- §7Sehe die Top 10 Spielzeit");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/ping §8- §7Sehe deinen Ping");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/serverinfo §8- §7Zeigt dir Infos über den Server");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/staff §8- §7Zeigt alle Teammitglieder");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/tc §8- §7Schreibe im Teamchat");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/bungeehelp §8- §7Zeigt dir diese Hilfe hier");
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§e/coins §8- §7Sehe deine Coins");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/joinme §8- §7Sende eine Nachricht zum nachjoinen");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/jump §8- §7Springe zu einem Spieler");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/onlinetime §8- §7Sehe deine Spielzeit");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/onlinetimetop10 §8- §7Sehe die Top 10 Spielzeit");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/ping §8- §7Sehe deinen Ping");
                player.sendMessage(BungeeSystem.getPrefix() + "§e/bungeehelp §8- §7Zeigt dir diese Hilfe hier");
            }
        }
    }
}
