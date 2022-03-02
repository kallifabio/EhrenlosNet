package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LobbySystem.getPrefix() + "Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("lobbysystem.command.gamemode")) {
            if (cmd.getName().equalsIgnoreCase("gm")) {
                if (args.length == 0) {
                    player.sendMessage(LobbySystem.getPrefix() + "§cBitte benutze §6/gm <0, 1, 2, 3> <Name>");
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDu bist bereits im Survival Modus");
                        } else {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(LobbySystem.getPrefix() + "§2Du befindest dich nun im Survival Modus");
                        }
                    } else if (args[0].equalsIgnoreCase("1")) {
                        if (player.getGameMode() == GameMode.CREATIVE) {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDu bist bereits im Creative Modus");
                        } else {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(LobbySystem.getPrefix() + "§2Du befindest dich nun im Creative Modus");
                        }
                    } else if (args[0].equalsIgnoreCase("2")) {
                        if (player.getGameMode() == GameMode.ADVENTURE) {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDu bist bereits im Adventure Modus");
                        } else {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(LobbySystem.getPrefix() + "§2Du befindest dich nun im Adventure Modus");
                        }
                    } else if (args[0].equalsIgnoreCase("3")) {
                        if (player.getGameMode() == GameMode.SPECTATOR) {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDu bist bereits im Spectator Modus");
                        } else {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(LobbySystem.getPrefix() + "§2Du befindest dich nun im Spectator Modus");
                        }
                    } else {
                        player.sendMessage(LobbySystem.getPrefix() + "§cBitte benutze §6/gm <0, 1, 2, 3> <Name>");
                    }
                }
                if (args.length == 2) {
                    String name = args[1];
                    Player target = Bukkit.getServer().getPlayer(name);
                    if (args[0].equalsIgnoreCase("0")) {
                        if (target != null) {
                            if (target.getGameMode() == GameMode.SURVIVAL) {
                                player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cbefindet sich bereits im Survival Modus");
                            } else {
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage(LobbySystem.getPrefix() + "§2Du wurdest von §7" + player.getName() + " §2in den Survival Modus gesetzt");
                                player.sendMessage(LobbySystem.getPrefix() + "§2Du hast §7" + target.getName() + " §2in den Survival Modus gesetzt");
                            }
                        } else {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler ist nicht online");
                        }
                    } else if (args[0].equalsIgnoreCase("1")) {
                        if (target != null) {
                            if (target.getGameMode() == GameMode.CREATIVE) {
                                player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cbefindet sich bereits im Creative Modus");
                            } else {
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage(LobbySystem.getPrefix() + "§2Du wurdest von §7" + player.getName() + " §2in den Creative Modus gesetzt");
                                player.sendMessage(LobbySystem.getPrefix() + "§2Du hast §7" + target.getName() + " §2in den Creative Modus gesetzt");
                            }
                        } else {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler ist nicht online");
                        }
                    } else if (args[0].equalsIgnoreCase("2")) {
                        if (target != null) {
                            if (target.getGameMode() == GameMode.ADVENTURE) {
                                player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cbefindet sich bereits im Adventure Modus");
                            } else {
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage(LobbySystem.getPrefix() + "§2Du wurdest von §7" + player.getName() + " §2in den Adventure Modus gesetzt");
                                player.sendMessage(LobbySystem.getPrefix() + "§2Du hast §7" + target.getName() + " §2in den Adventure Modus gesetzt");
                            }
                        } else {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler ist nicht online");
                        }
                    } else if (args[0].equalsIgnoreCase("3")) {
                        if (target != null) {
                            if (target.getGameMode() == GameMode.SPECTATOR) {
                                player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cbefindet sich bereits im Spectator Modus");
                            } else {
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage(LobbySystem.getPrefix() + "§2Du wurdest von §7" + player.getName() + " §2in den Spectator Modus gesetzt");
                                player.sendMessage(LobbySystem.getPrefix() + "§2Du hast §7" + target.getName() + " §2in den Spectator Modus gesetzt");
                            }
                        } else {
                            player.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler ist nicht online");
                        }
                    } else {
                        player.sendMessage(LobbySystem.getPrefix() + "§cBitte benutze §6/gm <0, 1, 2, 3> <Name>");
                    }
                }
            }
        } else {
            player.sendMessage(LobbySystem.getPrefix() + LobbySystem.getNoPerms());
        }
        return false;
    }
}
