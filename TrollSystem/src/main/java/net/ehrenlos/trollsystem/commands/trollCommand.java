package net.ehrenlos.trollsystem.commands;

import net.ehrenlos.trollsystem.TrollSystem;
import net.ehrenlos.trollsystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        player.getInventory().setItem(8, new ItemBuilder(Material.CHEST).setDisplayName("§eTrollMenü §8| §d" + target.getName()).build());
                    }
                }
            } else {
                player.sendMessage(TrollSystem.getPrefix() + TrollSystem.getNoPermission());
            }
        }
        return false;
    }
}
