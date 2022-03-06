package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class broadcastCommand extends Command {

    public broadcastCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        if (sender.hasPermission("bungeesystem.command.broadcast") || sender.hasPermission("bungeesystem.*")) {
            if (args.length >= 1) {
                StringBuilder messageBuilder = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    messageBuilder.append(args[i]).append(" ");
                String message = ChatColor.translateAlternateColorCodes('&', "§7[§cBroadcast§7] " + messageBuilder.toString().trim());
                ProxyServer.getInstance().broadcast("");
                ProxyServer.getInstance().broadcast(message);
                ProxyServer.getInstance().broadcast("");
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/broadcast <Messsage>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
