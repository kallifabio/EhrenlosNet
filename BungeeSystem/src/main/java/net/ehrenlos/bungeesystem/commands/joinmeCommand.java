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
        if (player.hasPermission("bungeesystem.command.joinme")) {
            if (args.length == 0) {
                if (!this.cooldown.contains(player)) {
                    TextComponent message = new TextComponent(BungeeSystem.getPrefix() + "§a[Klicke hier zum verbinden]");
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + player.getServer().getInfo().getName()));
                    String msg = BungeeSystem.getPrefix() + "§7Der Spieler %prefix% §8| §e%player% §7spielt auf §e%server%§7!";
                    msg = msg.replaceAll("%server%", player.getServer().getInfo().getName());
                    msg = msg.replaceAll("%player%", player.getName());
                    if (player.hasPermission("lobbysystem.rang.admin")) {
                        msg = msg.replaceAll("%prefix%", "§4Admin");
                    } else if (player.hasPermission("lobbysystem.rang.developer")) {
                        msg = msg.replaceAll("%prefix%", "§bDeveloper");
                    } else if (player.hasPermission("lobbysystem.rang.moderator")) {
                        msg = msg.replaceAll("%prefix%", "§cModerator");
                    } else if (player.hasPermission("lobbysystem.rang.builder")) {
                        msg = msg.replaceAll("%prefix%", "§2Builder");
                    } else if (player.hasPermission("lobbysystem.rang.supporter")) {
                        msg = msg.replaceAll("%prefix%", "§9Supporter");
                    } else if (player.hasPermission("lobbysystem.rang.youtuber")) {
                        msg = msg.replaceAll("%prefix%", "§5YouTuber");
                    } else if (player.hasPermission("lobbysystem.rang.jryoutuber")) {
                        msg = msg.replaceAll("%prefix%", "§dJrYouTuber");
                    }

                    ProxyServer.getInstance().broadcast("");
                    ProxyServer.getInstance().broadcast(msg);
                    ProxyServer.getInstance().broadcast(message);
                    ProxyServer.getInstance().broadcast("");
                    if (player.hasPermission("bungeesystem.command.joinme.nodelay"))
                        return;
                    this.cooldown.add(player);
                    ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
                        if (player != null)
                            this.cooldown.remove(player);
                    }, 60, TimeUnit.SECONDS);
                } else {
                    player.sendMessage(BungeeSystem.getPrefix() + "§7Bitte §ewarte §7einen Moment!");
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/joinme"));
            }
        } else {
            player.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
