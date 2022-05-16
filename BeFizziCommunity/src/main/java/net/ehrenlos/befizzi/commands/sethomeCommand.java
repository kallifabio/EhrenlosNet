package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import net.ehrenlos.befizzi.manager.ConfigManager;
import net.ehrenlos.befizzi.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class sethomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("sethome")) {
            if (player.hasPermission("community.command.sethome")) {
                if (args.length == 0) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/sethome <Name>");
                }

                if (args.length == 1) {
                    ConfigurationSection section = ConfigManager.getLocationData().getConfigurationSection(player.getName());
                    if (section != null) {
                        if (section.getKeys(false).size() == 5) {
                            player.sendMessage(Community.getPrefix() + "§cDein Limit von §75 Homes §cwurde erreicht");
                        } else {
                            LocationManager.setLocation(player.getName(), args[0], player.getLocation());
                            player.sendMessage(Community.getPrefix() + "§2Du hast einen Home §7" + args[0] + " §2erstellt");
                        }
                    } else {
                        LocationManager.setLocation(player.getName(), args[0], player.getLocation());
                        player.sendMessage(Community.getPrefix() + "§2Du hast einen Home §7" + args[0] + " §2erstellt");
                    }
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
