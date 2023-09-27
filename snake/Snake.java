package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public Snake (int x, int y) {
        for(int i = 0; i <= 2; i++) {
            GameObject part = new GameObject(x, y);
            snakeParts.add(part);
            x++;
        }
    }
    public void draw(Game game) {
        for (GameObject go : this.snakeParts) {
            if (0 == snakeParts.indexOf(go)) {
                game.setCellValue(go.x, go.y, HEAD_SIGN);
            }
            else if (0 < snakeParts.indexOf(go)) {
                game.setCellValue(go.x, go.y, BODY_SIGN);
            }
        }
    }
}
