package net.ehrenlos.jumpandrun.commands;

import net.ehrenlos.jumpandrun.JumpAndRun;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class jumpandrunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(JumpAndRun.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("jumpandrun.command.jumpandrun")) {
            if (cmd.getName().equalsIgnoreCase("jumpandrun")) {
                if (args.length == 0) {

                }
            }
        }
        return false;
    }
}
