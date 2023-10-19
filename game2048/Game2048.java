package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import javax.imageio.ImageTranscoder;

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
        matrix();
    }

    private void drawScene() {
        for (int x = 0; x < SIDE; x++){
            for (int y = 0; y < SIDE; y++){
            setCellColoredNumber(x, y, gameField[y][x]);
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

    private void setCellColoredNumber(int x, int y, int value) {
        if (value != 0) {
            setCellValueEx(x, y, getColorByValue(value), Integer.toString(value));
        }
        else {
            setCellValueEx(x, y, Color.NONE , "");
        }
    }

    private Color getColorByValue (int value) {
        switch (value) {
            case 2:
                return Color.PINK;
            case 4:
                return Color.VIOLET;
            case 8:
                return Color.BLUE;
            case 16:
                return Color.BLUEVIOLET;
            case 32:
                return Color.GREENYELLOW;
            case 64:
                return Color.GREEN;
            case 128:
                return Color.ORANGE;
            case 256:
                return Color.LIGHTPINK;
            case 512:
                return Color.ORANGERED;
            case 1024:
                return Color.DEEPPINK;
            case 2048:
                return Color.AZURE;
            default:
                return Color.NONE;
        }
    }

    public void matrix() {
        System.out.println("Matrix:");
        for (int x = 0; x < SIDE; x++){
            System.out.print("{ ");
            for (int y = 0; y < SIDE; y++){
                System.out.print(gameField[x][y] + " ");
            }
            System.out.println("}");
        }
    }
}
