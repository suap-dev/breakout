package dev.suap.breakout;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int width;
    int height;

    public Paddle(int centerX, int centerY, int width, int height) {
        this.x = centerX - width/2;
        this.y = centerY - height/2;
        this.height = height;
        this.width = width;
    }

    public void update() {

    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);
    }

}
