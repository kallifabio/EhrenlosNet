package net.ehrenlos.jumpandrun.commands;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.gamestates.LobbyState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class startCommand implements CommandExecutor {

    private static final int START_SECONDS = 5;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(JumpAndRun.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("jumpandrun.command.start")) {
            if (cmd.getName().equalsIgnoreCase("start")) {
                if (args.length == 0) {
                    if (JumpAndRun.getGameStatesManager().getCurrentGameState() instanceof LobbyState) {
                        LobbyState lobbyState = (LobbyState) JumpAndRun.getGameStatesManager().getCurrentGameState();
                        if (lobbyState.getLobbyCountdown().isIsRunning() && lobbyState.getLobbyCountdown().getSeconds() > START_SECONDS) {
                            lobbyState.getLobbyCountdown().setSeconds(START_SECONDS);
                            player.sendMessage(JumpAndRun.getPrefix() + "§aDer Spieltstart wurde beschleunigt");
                        } else {
                            player.sendMessage(JumpAndRun.getPrefix() + "§cDas Spiel läuft bereits");
                        }
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cDas Spiel läuft bereits");
                    }
                } else {
                    player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/start");
                }
            }
        } else {
            player.sendMessage(JumpAndRun.getPrefix() + JumpAndRun.getNoPerms());
        }
        return false;
    }
}
