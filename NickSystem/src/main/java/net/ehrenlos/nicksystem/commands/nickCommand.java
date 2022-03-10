package net.ehrenlos.nicksystem.commands;

import net.ehrenlos.nicksystem.NickSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

public class nickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(NickSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        String name = args[0];
        if (cmd.getName().equalsIgnoreCase("nick")) {
            if (player.hasPermission("nicksystem.command.nick")) {
                if (args.length == 0) {
                    player.sendMessage(NickSystem.getPrefix() + "§cBitte benutze §6/nick <Name>");
                }

                if (args.length == 1) {
                    NickAPI.nick(player, name);
                    NickAPI.setSkin(player, name);
                    NickAPI.setUniqueId(player, name);
                    NickAPI.setGameProfileName(player, name);
                    NickAPI.refreshPlayer(player);
                    player.sendMessage(NickSystem.getPrefix() + "§2Du hast dich erfolgreich §7" + name + " §2genannt");
                }
            } else {
                player.sendMessage(NickSystem.getPrefix() + NickSystem.getNoPerms());
            }
        }
        return false;
    }
}
