package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.HologramsManager;
import net.ehrenlos.hologram.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hologramcreateCommand implements CommandExecutor {

    HologramsManager hologramsManager = new HologramsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologram.command.create")) {
            if (cmd.getName().equalsIgnoreCase("hologramcreate")) {
                if (args.length < 3) {
                    player.sendMessage(HologramSystem.getPrefix() + "§cBenutze §6/hologramcreate <Name> <Line1> <Line2>");
                }

                if (args.length == 3) {
                    LocationManager.setLocation(args[0], player.getLocation());
                    hologramsManager.createHologramLine2(args[0], LocationManager.getLocation(args[0]), args[1], args[2]);
                    player.sendMessage(HologramSystem.getPrefix() + "§2Du hast das Hologram §e" + args[0] + " §2erstellt");
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
