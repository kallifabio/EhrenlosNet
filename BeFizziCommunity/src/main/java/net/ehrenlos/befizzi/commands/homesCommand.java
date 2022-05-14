package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import net.ehrenlos.befizzi.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class homesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("homes")) {
            if (player.hasPermission("community.command.homes")) {
                if (args.length == 0) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/homes <Spieler>");
                }

                if (args.length == 1) {
                    final Player target = Bukkit.getServer().getPlayer(args[0]);
                    ConfigurationSection section = ConfigManager.getLocationData().getConfigurationSection(target.getName());
                    String homes = null;
                    if (section != null) {
                        homes = String.valueOf(section.getKeys(false));
                    }
                    player.sendMessage(Community.getPrefix() + "§bDas sind die Homes von §e" + target.getName() + " §7" + homes);
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
