package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.HologramsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hologramremovelineCommand implements CommandExecutor {

    HologramsManager hologramsManager = new HologramsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologram.command.removeline")) {
            if (cmd.getName().equalsIgnoreCase("hologramremoveline")) {
                if (args.length < 2) {
                    player.sendMessage(HologramSystem.getPrefix() + "§cBenutze §6/hologramremoveline <Name> <Line(1,2,3,4)>");
                }

                if (args.length == 2) {
                    if (args[1].contains("Line1")) {
                        hologramsManager.removeHologramLine1(args[0]);
                    } else if (args[1].contains("Line2")) {
                        hologramsManager.removeHologramLine2(args[0]);
                    } else if (args[1].contains("Line3")) {
                        hologramsManager.removeHologramLine3(args[0]);
                    } else if (args[1].contains("Line4")) {
                        hologramsManager.removeHologramLine4(args[0]);
                    }
                    player.sendMessage(HologramSystem.getPrefix() + "§2Du hast die §e" + args[1] + " §2von §e" + args[0] + " §2entfernt");
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
