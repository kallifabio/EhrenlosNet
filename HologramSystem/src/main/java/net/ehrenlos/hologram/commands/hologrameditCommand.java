package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.HologramsManager;
import net.ehrenlos.hologram.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hologrameditCommand implements CommandExecutor {

    HologramsManager hologramsManager = new HologramsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologram.command.edit")) {
            if (cmd.getName().equalsIgnoreCase("hologramedit")) {
                if (args.length < 3) {
                    player.sendMessage(HologramSystem.getPrefix() + "§cBenutze §6/hologramedit <Name> <Line(1,2,3,4)> <NewLine>");
                }

                if (args.length == 3) {
                    hologramsManager.editHologramLine1(args[0], args[1]);
                    player.sendMessage(HologramSystem.getPrefix() + "§2Du hast das Hologram §e" + args[0] + " §2geändert");
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
