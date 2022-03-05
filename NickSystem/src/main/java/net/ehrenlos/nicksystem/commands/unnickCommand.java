package net.ehrenlos.nicksystem.commands;

import net.ehrenlos.nicksystem.NickSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

public class unnickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(NickSystem.getPrefix() + "§4Du musst ein Spieler sein");
        }

        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("unnick")) {
            if (player.hasPermission("nicksystem.command.unnick")) {
                if (args.length == 0) {
                    NickAPI.resetNick(player);
                    NickAPI.resetSkin(player);
                    NickAPI.resetUniqueId(player);
                    NickAPI.resetGameProfileName(player);
                    NickAPI.refreshPlayer(player);
                    player.sendMessage(NickSystem.getPrefix() + "§2Du hast erfolgreich deinen Nick zurückgesetzt");
                }
            } else {
                player.sendMessage(NickSystem.getPrefix() + NickSystem.getNoPerms());
            }
        }
        return false;
    }
}
