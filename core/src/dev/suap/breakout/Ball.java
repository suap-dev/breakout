package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int radius;
    int xSpeed;
    int ySpeed;

    public Ball(int x, int y, int radius, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;

        if (x - radius < 0 || x + radius > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y - radius < 0 || y + radius > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer renderer) {
        renderer.circle(x, y, radius);
    }
}
