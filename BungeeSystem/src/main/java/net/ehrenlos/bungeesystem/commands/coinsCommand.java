package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.CoinsSystemManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class coinsCommand extends Command {

    public coinsCommand(String name) {
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
            if (CoinsSystemManager.isRegistered(sender.getName())) {
                sender.sendMessage(BungeeSystem.getPrefix() + "§2Du hast §7" + CoinsSystemManager.getCoins(sender.getName()) + " §2Coins");
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§2Du bist nicht registriert");
            }
        }

        if (args.length == 1) {
            player.sendMessage(BungeeSystem.getPrefix() + "§cBitte benutze §6/coins <set, add, remove, get> <Name> <Coins>");
        }

        String name = args[1];
        ProxiedPlayer target = BungeeCord.getInstance().getPlayer(name);
        if (args.length == 2) {
            if (player.hasPermission("bungeesystem.command.coins.get")) {
                if (args[0].equalsIgnoreCase("get")) {
                    if (target != null && CoinsSystemManager.isRegistered(name)) {
                        player.sendMessage(BungeeSystem.getPrefix() + "§2Der Spieler §e" + target.getName() + " §2hat §7" + CoinsSystemManager.getCoins(target.getName()) + " §2Coins");
                    } else {
                        player.sendMessage(BungeeSystem.getPrefix() + "§cSpieler ist nicht online oder nicht registriert");
                    }
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§4Du hast dazu keine Rechte");
            }
        }

        if (args.length == 3) {
            int amount = Integer.parseInt(args[2]);
            if (player.hasPermission("bungeesystem.command.coins.set")) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (target != null) {
                        CoinsSystemManager.setCoins(name, amount);
                        player.sendMessage(BungeeSystem.getPrefix() + "§2Du hast §e" + target.getName() + " §7" + amount + " §2Coins gesetzt");
                        target.sendMessage(BungeeSystem.getPrefix() + "§e" + player.getName() + " §2hat dir §7" + amount + " §2Coins gesetzt");
                    } else {
                        player.sendMessage(BungeeSystem.getPrefix() + "§cSpieler ist nicht online oder nicht registriert");
                    }
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§4Du hast dazu keine Rechte");
            }
            if (player.hasPermission("bungeesystem.command.coins.add")) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (target != null) {
                        CoinsSystemManager.addCoins(name, amount);
                        player.sendMessage(BungeeSystem.getPrefix() + "§2Du hast §e" + target.getName() + " §7" + amount + " §2Coins hinzugefügt");
                        target.sendMessage(BungeeSystem.getPrefix() + "§e" + player.getName() + " §2hat dir §7" + amount + " §2Coins hinzugefügt");
                    } else {
                        player.sendMessage(BungeeSystem.getPrefix() + "§cSpieler ist nicht online oder nicht registriert");
                    }
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§4Du hast dazu keine Rechte");
            }
            if (player.hasPermission("bungeesystem.command.coins.remove")) {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (target != null) {
                        CoinsSystemManager.removeCoins(name, amount);
                        player.sendMessage(BungeeSystem.getPrefix() + "§cDu hast §e" + target.getName() + " §7" + amount + " §cCoins entfernt");

                        player.sendMessage(BungeeSystem.getPrefix() + "§cDer Spieler §e" + target.getName() + " §chat keine Coins");

                    } else {
                        player.sendMessage(BungeeSystem.getPrefix() + "§cSpieler ist nicht online oder nicht registriert");
                    }
                }
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§4Du hast dazu keine Rechte");
            }
        }
    }
}
