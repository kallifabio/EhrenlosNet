package net.ehrenlos.trollsystem.commands;

import net.ehrenlos.trollsystem.TrollSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Random;

public class trollCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(TrollSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("troll")) {
            if (player.hasPermission("trollsystem.command.troll")) {
                if (args.length == 0) {
                    player.sendMessage(TrollSystem.getPrefix() + "§e/troll freeze <Spieler> §8- §7Friere einen Spieler ein");
                    player.sendMessage(TrollSystem.getPrefix() + "§e/troll tntplayer <Spieler> §8- §7Spieler werden von TNT verfolgt");
                    player.sendMessage(TrollSystem.getPrefix() + "§e/troll rotate <Spieler> §8- §7Lasse einen Spieler rotieren");
                    player.sendMessage(TrollSystem.getPrefix() + "§e/troll randomtp <Spieler> §8- §7Lasse einen Spieler durch die Luft teleportieren");
                    player.sendMessage(TrollSystem.getPrefix() + "§e/troll prison <Spieler> §8- §7Der Spieler kommt in ein Gefängnis an deiner Position");
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("hackmsg")) {
                        player.sendMessage(TrollSystem.getPrefix() + "§2Die Hackmessage wurde verschickt");
                        final Object packet = Bukkit.getOnlinePlayers().iterator();
                        while (((Iterator) packet).hasNext()) {
                            final Player all = (Player) ((Iterator) packet).next();
                            if (!all.equals(player)) {
                                final Random rnd = new Random();
                                for (int i = 0; i < 100; ++i) {
                                    all.sendTitle("§c" + String.valueOf(i) + "%", "§aHackvorgang läuft");
                                    try {
                                        Thread.sleep(50L);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                all.sendMessage("§cDer Server wurde gehackt!");
                                all.sendTitle("§cGEHACKT!", "§aIHR HABT KEINE CHANCE");
                            }
                        }
                    }
                }

                if (args.length == 2) {
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("freeze")) {
                        if (!TrollSystem.getInstance().freezePlayer.contains(target)) {
                            TrollSystem.getInstance().freezePlayer.add(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§2Du hast den Spieler §7" + target.getName() + " §2eingefroren");
                        } else {
                            TrollSystem.getInstance().freezePlayer.remove(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§cDu hast den Spieler §7" + target.getName() + " §centfroren");
                        }
                    }

                    if (args[0].equalsIgnoreCase("tntplayer")) {
                        if (!TrollSystem.getInstance().tntPlayer.contains(target)) {
                            TrollSystem.getInstance().tntPlayer.add(target);
                            TrollSystem.getInstance().spawnTNTPlayerSpur(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§2Der Spieler §7" + target.getName() + " §2wird nun von TNT verfolgt");
                        } else {
                            TrollSystem.getInstance().tntPlayer.remove(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cwird nicht mehr von TNT verfolgt");
                        }
                    }

                    if (args[0].equalsIgnoreCase("rotate")) {
                        if (!TrollSystem.getInstance().rotatePlayer.contains(target)) {
                            TrollSystem.getInstance().rotatePlayer.add(target);
                            TrollSystem.getInstance().rotatePlayer(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§2Der Spieler §7" + target.getName() + " §2rotiert sich nun");
                        } else {
                            TrollSystem.getInstance().rotatePlayer.remove(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §crotiert sich nun nicht mehr");
                        }
                    }

                    if (args[0].equalsIgnoreCase("randomtp")) {
                        if (!TrollSystem.getInstance().randomTPPlayer.contains(target)) {
                            TrollSystem.getInstance().randomTPPlayer.add(target);
                            TrollSystem.getInstance().randomTP(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§2Der Spieler §7" + target.getName() + " §2wird durch die Luft teleportiert");
                        } else {
                            TrollSystem.getInstance().randomTPPlayer.remove(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cwird nicht mehr durch die Luft teleportiert");
                        }
                    }

                    if (args[0].equalsIgnoreCase("prison")) {
                        TrollSystem.getInstance().createBox(player, target);
                        player.sendMessage(TrollSystem.getPrefix() + "§2Der Spieler §7" + target.getName() + " §2ist im Gefängnis an deiner Position");
                    }

                    if (args[0].equalsIgnoreCase("potion")) {
                        if (!TrollSystem.getInstance().effectPlayer.contains(target)) {
                            TrollSystem.getInstance().effectPlayer.add(target);
                            TrollSystem.getInstance().createEffect(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§2Der Spieler §7" + target.getName() + " §2wird mit Zufallseffekten versorgt");
                        } else {
                            TrollSystem.getInstance().effectPlayer.remove(target);
                            player.sendMessage(TrollSystem.getPrefix() + "§cDer Spieler §7" + target.getName() + " §cwird nicht mehr mit Zufallseffekten versorgt");
                        }
                    }
                }
            } else {
                player.sendMessage(TrollSystem.getPrefix() + TrollSystem.getNoPermission());
            }
        }
        return false;
    }
}
