package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;

    private Direction direction = Direction.LEFT;

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake (int x, int y) {
        for(int i = 0; i <= 2; i++) {
            GameObject part = new GameObject(x, y);
            snakeParts.add(part);
            x++;
        }
    }
    public void draw(Game game) {
        if (isAlive) {
            for (GameObject go : this.snakeParts) {
                if (0 == snakeParts.indexOf(go)) {
                    game.setCellValueEx(go.x, go.y, Color.NONE, HEAD_SIGN, Color.GREEN, 75);
                }
                else if (0 < snakeParts.indexOf(go)) {
                    game.setCellValueEx(go.x, go.y, Color.NONE, BODY_SIGN, Color.GREEN, 75);
                }
            }
        }
        else {
            for (GameObject go : this.snakeParts) {
                if (0 == snakeParts.indexOf(go)) {
                    game.setCellValueEx(go.x, go.y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                }
                else if (0 < snakeParts.indexOf(go)) {
                    game.setCellValueEx(go.x, go.y, Color.NONE, BODY_SIGN, Color.RED, 75);
                }
            }
        }
    }
    public void move() {
        return;
    }
}
