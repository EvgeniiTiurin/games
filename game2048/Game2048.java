package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][]gameField = new int[SIDE][SIDE];
@Override
    public void initialize() {
        setScreenSize(4, 4);
        createGame();
    }
    private void createGame(){

    }
}
