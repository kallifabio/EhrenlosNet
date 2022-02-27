package net.ehrenlos.jumpandrun.gamestates;

public abstract class GameStates {

    public static final int LOBBY_STATE = 0,
            INGAME_STATE = 1,
            END_STATE = 2;

    public abstract void start();

    public abstract void stop();
}
