package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class joinmeCommand extends Command {

    public ArrayList<ProxiedPlayer> cooldown;

    public joinmeCommand(String name) {
        super(name);
        this.cooldown = new ArrayList<>();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        final ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("bungeesystem.joinme") || player.hasPermission("bungeesystem.*")) {
            if (args.length == 0) {
                if (!this.cooldown.contains(player)) {
                    TextComponent message = new TextComponent("§a[Klicke hier zum verbinden]");
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + player.getServer().getInfo().getName()));
                    String msg = "§7Der Spieler %prefix% §8| §e%player% §7spielt auf §e%server%§7!";
                    msg = msg.replaceAll("%server%", player.getServer().getInfo().getName());
                    msg = msg.replaceAll("%player%", player.getName());
                    if (player.hasPermission("lobbysystem.rang.admin") || player.hasPermission("*")) {
                        msg = msg.replaceAll("%prefix%", "§4Admin");
                    }

                    ProxyServer.getInstance().broadcast("");
                    ProxyServer.getInstance().broadcast(msg);
                    ProxyServer.getInstance().broadcast(message);
                    ProxyServer.getInstance().broadcast("");
                    if (player.hasPermission("bungeesystem.joinme.nodelay") || player.hasPermission("bungeesystem.*"))
                        return;
                    this.cooldown.add(player);
                    ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
                        if (player != null)
                            this.cooldown.remove(player);
                    }, 60, TimeUnit.SECONDS);
                } else {
                    player.sendMessage("§7Bitte §ewarte §7einen Moment!");
                }
            } else {
                player.sendMessage("§cBenutze: §e%command%".replaceAll("%command%", "/joinme"));
            }
        } else {
            player.sendMessage(BungeeSystem.getNoPerms());
        }
    }
}
