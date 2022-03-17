package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.HologramsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class hologramhelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologramsystem.command.help")) {
            if (cmd.getName().equalsIgnoreCase("hologramhelp")) {
                if (args.length == 0) {
                    player.sendMessage("§8§m-----------§7 [§6Hologram-Help§7] §8§m-------------");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/hologramcreate §8- §7Erstelle ein Hologram");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/hologramedit §8- §7Ändere die Line von einem Hologram");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/holograminfo §8- §7Bekomme Infos zu einem Hologram");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/hologramremove §8- §7Lösche das ganze Hologram");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/hologramremoveline §8- §7Lösche eine Line von einem Hologram");
                    player.sendMessage(HologramSystem.getPrefix() + "§e/hologramhelp §8- §7Zeigt dir diese Hilfe hier");
                    player.sendMessage("§8§m-----------§7 [§6Hologram-Help§7] §8§m-------------");
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
