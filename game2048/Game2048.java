package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][]gameField = new int[SIDE][SIDE];
@Override
    public void initialize() {
        setScreenSize(4, 4);
        createGame();
        drawScene();
    }
    private void createGame(){
        createNewNumber();
        createNewNumber();
        showMatrix();
    }

    private void drawScene() {
        for (int x = 0; x < SIDE; x++){
            for (int y = 0; y < SIDE; y++){
            //setCellColor(x, y, Color.RED);
            }
        }
    }

    private void createNewNumber() {
        boolean notNull = true;
        while(notNull) {
            int x = getRandomNumber(SIDE);
            int y = getRandomNumber(SIDE);
            if(gameField[y][x] == 0) {
                if (getRandomNumber(10) == 9) {
                    gameField[y][x] = 4;
                }
                else {
                    gameField[y][x] = 2;
                }
                notNull = false;
            }
        }
    }

    private void showMatrix() {
        for (int x = 0; x < SIDE; x++){
            for (int y = 0; y < SIDE; y++){
                System.out.print(gameField[y][x] + " ");
            }
        }
    }
}
