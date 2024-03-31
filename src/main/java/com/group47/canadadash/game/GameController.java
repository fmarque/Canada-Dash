package com.group47.canadadash.game;

import com.group47.canadadash.GameState;
import com.group47.canadadash.processing.Level;

public class GameController {

    private GameState internalState;


    public GameController(Level level)
    {
        internalState = new GameState();
    }

    public void playerDamage()
    {
        internalState.loseLife();
    }

    public void IncreasePoints()
    {
        internalState.increasePoints(1);
    }

    public int getPoints()
    {
        return internalState.getTotalPoints();
    }

    public int getCurrentLives()
    {
        return internalState.getLives();
    }





}
