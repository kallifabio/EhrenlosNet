package net.ehrenlos.jumpandrun.gamestates;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.countdowns.LobbyCountdown;
import net.ehrenlos.jumpandrun.manager.GameStatesManager;
import org.bukkit.Bukkit;

public class LobbyState extends GameStates {

    public static final int MIN_PLAYERS = 2, MAX_PLAYERS = 5;

    private LobbyCountdown lobbyCountdown;

    public LobbyState(GameStatesManager gameStatesManager) {
        lobbyCountdown = new LobbyCountdown(gameStatesManager);
    }

    @Override
    public void start() {
        lobbyCountdown.startIdle();
        System.out.println("Der LOBBY STATE wurde getstartet");
    }

    @Override
    public void stop() {
        Bukkit.broadcastMessage(JumpAndRun.getPrefix() + "§cAlle Spieler werden teleportiert");
    }


    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
