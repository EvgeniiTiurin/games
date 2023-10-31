package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import javax.imageio.ImageTranscoder;
import java.lang.reflect.Array;
import java.util.Arrays;

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
//        matrix();
//        int[] row0 = {4, 4, 0, 0};
//        mergeRow(row0);
//        int[] row1 = {2, 2, 2, 2};
//        mergeRow(row1);
//        int[] row2 = {4, 2, 2, 0};
//        mergeRow(row2);
//        int[] row3 = {0, 2, 2, 0};
//        mergeRow(row3);
//        int[] row4 = {0, 2, 2, 2};
//        mergeRow(row4);
//        int[] row5 = {4, 0, 4, 0};
//        mergeRow(row5);

        //compressRow(row);
        //mergeRow(row);
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

    private boolean compressRow(int[] row) {
        int length = SIDE;
        int writeIndex = 0;
        boolean moved = false;

        for (int i = 0; i < length; i++) {
            if (row[i] != 0) {
                row[writeIndex] = row[i];
                if (i != writeIndex) {
                    row[i] = 0;
                    moved = true;
                }
                writeIndex++;
            }
        }
        return moved;
    }

    private boolean mergeRow(int[] row) {
        boolean merged = false;
        int length = SIDE;

        //System.out.println("Original: " + Arrays.toString(row) + " " + merged);

        for (int i = 0; i < length - 1; i++) {
            if ((row[i] == row[i+1]) && (row[i] != 0)) {
                row[i] = row[i] * 2;
                row[i + 1] = 0;
                merged = true;
            }
        }
//        System.out.println("Merged: " + Arrays.toString(row) + " " + merged);
//        System.out.println("----------------");
        return merged;
    }
}
