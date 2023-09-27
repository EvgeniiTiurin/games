package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    final private static String MINE = "\uD83D\uDCA3";
    final private static String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        fieldConsole();
    }

    private void createGame() {
        score = 0;
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
            }
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.BLUEVIOLET);
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors(){
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (!(gameField[y][x].isMine)) {
                    List<GameObject>  gameObjectList = getNeighbors(gameField[y][x]);
                    for (GameObject gameObject: gameObjectList) {
                        if (gameObject.isMine) {
                            gameField[y][x].countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
        }
        else {
            openTile(x, y);
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void openTile(int x, int y) {
        if (!isGameStopped) {
            if(!gameField[y][x].isOpen && !gameField[y][x].isFlag) {
                GameObject gameObject = gameField[y][x];
                if (gameObject.isMine) {
                    setCellColor(x, y, Color.RED);
                    setCellValue(x, y, MINE);
                    setCellValueEx(x, y, Color.RED, MINE);
                    gameOver();
                }
                else {
                    gameField[y][x].isOpen = true;
                    setCellColor(x, y, Color.GREEN);
                    score+=5;
                    System.out.println(score);
                    setScore(score);
                    if (gameField[y][x].countMineNeighbors > 0) {
                        setCellNumber(x, y, gameField[y][x].countMineNeighbors);
                        countClosedTiles--;
                    }
                    else {
                        setCellValue(x, y, "");
                         countClosedTiles--;
                        List<GameObject> neighbors = getNeighbors(gameField[y][x]);
                        for (GameObject member : neighbors) {
                            if (!member.isOpen) {
                                openTile(member.x, member.y);
                            }
                        }
                        //                getNeighbors(gameField[y][x]).stream()  // работает, но нужно понять
                        //                        .filter(cell -> !cell.isOpen)
                        //                        .collect(Collectors.toList())
                        //                        .forEach(cell -> openTile(cell.x, cell.y));
                    }
                    if (countClosedTiles == countMinesOnField) {
                        win();
                    }
                }
            }
        }
    }

    private void  fieldConsole() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (gameField[y][x].isMine) {
                    System.out.print(ANSI_RED + "M" + ANSI_RESET + " ");
                }
                else {
                    System.out.print(gameField[y][x].countMineNeighbors + " ");
                }
            }
            System.out.println();
        }
    }

    private void markTile(int x, int y) {
        if (!isGameStopped) {
            if (gameField[y][x].isOpen) {
                return;
            }
            if (!gameField[y][x].isFlag && !gameField[y][x].isOpen && countFlags > 0) {
                gameField[y][x].isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            }
            else if (gameField[y][x].isFlag && !gameField[y][x].isOpen) {
                gameField[y][x].isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.BLUEVIOLET);
            }
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "You WIN! Your score is: " + score, Color.BLACK, 30);
    }

    private void gameOver() {
        showMessageDialog(Color.YELLOW, "Game Over. Your score is: " + score, Color.BLACK, 20);
        isGameStopped = true;
    }

    private void restart() {
        isGameStopped = false;
        score = 0;
        setScore(0);
        countClosedTiles = SIDE * SIDE;
        countMinesOnField = 0;
        createGame();
    }
}