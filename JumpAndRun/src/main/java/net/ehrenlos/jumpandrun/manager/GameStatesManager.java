package net.ehrenlos.jumpandrun.manager;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.gamestates.EndState;
import net.ehrenlos.jumpandrun.gamestates.GameStates;
import net.ehrenlos.jumpandrun.gamestates.IngameState;
import net.ehrenlos.jumpandrun.gamestates.LobbyState;

public class GameStatesManager {

    private JumpAndRun plugin;
    private GameStates[] gameStates;
    private GameStates currentGameState;

    public GameStatesManager(JumpAndRun plugin) {
        this.plugin = plugin;
        gameStates = new GameStates[3];

        gameStates[GameStates.LOBBY_STATE] = new LobbyState(this);
        gameStates[GameStates.INGAME_STATE] = new IngameState();
        gameStates[GameStates.END_STATE] = new EndState();
    }

    public void setGameState(int gameStateID) {
        if (currentGameState != null) {
            currentGameState.stop();
        }
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopCurrentGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }

    public GameStates getCurrentGameState() {
        return currentGameState;
    }

    public JumpAndRun getPlugin() {
        return plugin;
    }
}
