package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class homesystemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("homesystem")) {
            if (args.length == 0) {
                player.sendMessage(" ");
                player.sendMessage("§8§m-----------§7 [§6HomeSystem§7] §8§m-------------");
                player.sendMessage(Community.getPrefix() + "§e/home <Spieler> <Home> §8- §eTeleportiere dich zu einem Home");
                player.sendMessage(Community.getPrefix() + "§e/homes <Spieler> §8- §eLasse dir alle Homes anzeigen");
                player.sendMessage(Community.getPrefix() + "§e/sethome <Name> §8- §eSetze einen Home");
                player.sendMessage(Community.getPrefix() + "§e/delhome <Home> §8- §eLösche einen Home");
                player.sendMessage(Community.getPrefix() + "§e/invsee <Spieler> §8- §eÖffne das Inventar eines Spielers");
                player.sendMessage(Community.getPrefix() + "§e/setstatus <Home> <true (open) | false (private)> §8- §eSetzte den Status deines Homes");
                player.sendMessage("§8§m-----------§7 [§6HomeSystem§7] §8§m-------------");
                player.sendMessage(" ");
            }
        }
        return false;
    }
}
