package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.HologramsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hologramremoveCommand implements CommandExecutor {

    HologramsManager hologramsManager = new HologramsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologram.command.remove")) {
            if (cmd.getName().equalsIgnoreCase("hologramremove")) {
                if (args.length < 1) {
                    player.sendMessage(HologramSystem.getPrefix() + "§cBenutze §6/hologramremove <Name>");
                }

                if (args.length == 1) {
                    hologramsManager.removeHologram(args[0]);
                    player.sendMessage(HologramSystem.getPrefix() + "§2Du hast das Hologram §e" + args[0] + " §2entfernt");
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
