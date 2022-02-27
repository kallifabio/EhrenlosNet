package net.ehrenlos.jumpandrun.countdowns;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.gamestates.GameStates;
import net.ehrenlos.jumpandrun.gamestates.LobbyState;
import net.ehrenlos.jumpandrun.manager.GameStatesManager;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class LobbyCountdown extends Countdowns {

    private static final int COUNTDOWN_TIME = 20, IDLE_TIME = 15;
    private final GameStatesManager gameStatesManager;
    private int idleID;
    private int seconds;
    private boolean isIdling;
    private boolean isRunning;

    public LobbyCountdown(GameStatesManager gameStatesManager) {
        this.gameStatesManager = gameStatesManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStatesManager.getPlugin(), () -> {
            switch (seconds) {
                case 30:
                case 20:
                case 15:
                case 10:
                case 5:
                case 4:
                case 3:
                case 2:
                    Bukkit.broadcastMessage(JumpAndRun.getPrefix() + "§7Das Spiel startet in §a" + seconds + " §7Sekunden");

                    if (seconds == 3) {
                        Voting voting = gameStatesManager.getPlugin().getVoting();
                        Map winningMap;
                        if (voting != null) {
                            winningMap = voting.getWinnerMap();
                        } else {
                            ArrayList<Map> maps = gameStatesManager.getPlugin().getMaps();
                            Collections.shuffle(maps);
                            winningMap = maps.get(0);
                        }
                        Bukkit.broadcastMessage(JumpAndRun.getPrefix() + "§7Sieger des Votings §a" + winningMap.getName());
                    }

                    break;
                case 1:
                    Bukkit.broadcastMessage(JumpAndRun.getPrefix() + "§7Das Spiel startet in §aeiner §7Sekunde");
                    break;
                case 0:
                    gameStatesManager.setGameState(GameStates.INGAME_STATE);
                    break;

                default:
                    break;
            }
            seconds--;
        }, 0, 20);
    }

    @Override
    public void stop() {
        if (isRunning) {
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle() {
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStatesManager.getPlugin(), () -> {
            Bukkit.broadcastMessage(JumpAndRun.getPrefix() + "§7Bis zum Start werden noch §6" +
                    (LobbyState.MIN_PLAYERS - gameStatesManager.getPlugin().getPlayers().size()) +
                    " §7Spieler benötigt");
        }, 0, 20 * IDLE_TIME);
    }

    public void stopIdle() {
        if (isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
