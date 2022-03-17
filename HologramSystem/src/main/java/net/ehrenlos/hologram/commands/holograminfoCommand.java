package net.ehrenlos.hologram.commands;

import net.ehrenlos.hologram.HologramSystem;
import net.ehrenlos.hologram.manager.ConfigManager;
import net.ehrenlos.hologram.manager.HologramsManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class holograminfoCommand implements CommandExecutor {

    HologramsManager hologramsManager = new HologramsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HologramSystem.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("hologram.command.info")) {
            if (cmd.getName().equalsIgnoreCase("holograminfo")) {
                if (args.length < 1) {
                    player.sendMessage(HologramSystem.getPrefix() + "§cBenutze §6/holograminfo <Name>");
                }

                if (args.length == 1) {
                    String name = args[0];
                    World world = Bukkit.getWorld(ConfigManager.getHologramData().getString(name + ".Position.World"));
                    double x = ConfigManager.getHologramData().getDouble(name + ".Position.X");
                    String formattedX = new DecimalFormat("#.##").format(x);
                    double y = ConfigManager.getHologramData().getDouble(name + ".Position.Y");
                    String formattedY = new DecimalFormat("#.##").format(y);
                    double z = ConfigManager.getHologramData().getDouble(name + ".Position.Z");
                    String formattedZ = new DecimalFormat("#.##").format(z);
                    String line1 = hologramsManager.getHologramLine1(name);
                    String line2 = hologramsManager.getHologramLine2(name);
                    String line3 = hologramsManager.getHologramLine3(name);
                    String line4 = hologramsManager.getHologramLine4(name);
                    player.sendMessage("Hologramname: " + name + "\nWeltname: " + world.getName() + "\nX: " + formattedX + "\nY: " + formattedY + "\nZ: " + formattedZ + "\nLine1: " + line1 + "\nLine2: " + line2 + "\nLine3: " + line3 + "\nLine4: " + line4);
                }
            }
        } else {
            player.sendMessage(HologramSystem.getPrefix() + HologramSystem.getNoPerms());
        }
        return false;
    }
}
