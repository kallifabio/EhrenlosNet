package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.ServerInfoManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class serverinfoCommand extends Command {

    public serverinfoCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("bungeesystem.command.serverinfo")) {
            if (args.length == 0) {
                player.sendMessage(BungeeSystem.getPrefix() + "§8§m-------§c ServerInfo §8§m-------");
                player.sendMessage(BungeeSystem.getPrefix() + "§7Insgesamt in der Serverliste hinzugefügt §8» §e%total%".replaceAll("%total%", String.valueOf(ServerInfoManager.getTotalPings().size())));
                player.sendMessage(BungeeSystem.getPrefix() + "§7Registrierte Spieler §8» §e%total%".replaceAll("%total%", String.valueOf(ServerInfoManager.getRegistredUsers().size())));
                player.sendMessage(BungeeSystem.getPrefix() + "§7Maximaler Spielerrekord §8» §e%total%".replaceAll("%total%", String.valueOf(ServerInfoManager.getUserRecord())));
                player.sendMessage(BungeeSystem.getPrefix() + "§7Durchschnittliche Spieler (letzte Stunde) §8» §e%total%".replaceAll("%total%", String.valueOf(ServerInfoManager.getTotalPlayers() / ServerInfoManager.getUpdatedTimes())));
                player.sendMessage(BungeeSystem.getPrefix() + "§8§m-------§c ServerInfo §8§m-------");
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/serverinfo"));
            }
        } else {
            player.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
