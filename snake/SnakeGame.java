package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        isGameStopped = false;
        score = 0;
        setScore(score);
        turnDelay = 300;
        setTurnTimer(turnDelay);
        Snake snake = new Snake(WIDTH/2, HEIGHT/2);
        this.snake = snake;
        createNewApple();
        drawScene();
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.ANTIQUEWHITE,"");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
    @Override
    public void onTurn(int x) {
        snake.move(apple);
        if (!apple.isAlive){
            score+=5;
            setScore(score);
            turnDelay-=10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if (snake.isAlive == false) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                if (isGameStopped) {
                   createGame();
                }
                break;
            default:
                break;
        }
    }
    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        while (snake.checkCollision(apple)) {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }
    }
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "Game Over", Color.BLACK, 75);
    }
    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "You win", Color.AZURE, 75);
    }
}
