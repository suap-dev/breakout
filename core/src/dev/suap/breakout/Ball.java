package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int radius;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    private boolean inCollision = false;

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
        renderer.setColor(color);
        renderer.circle(x, y, radius);
    }

    public void handleCollision(Paddle paddle) {
        if (!inCollision && collidesWith(paddle)) {
            inCollision = true;
            ySpeed = Math.abs(ySpeed);
        } else if (!collidesWith(paddle)) {
            inCollision = false;
        }
    }

    private boolean collidesWith(Paddle paddle) {
        return paddle.getLeft() <= this.getRight()
                &&
                paddle.getRight() >= this.getLeft()
                &&
                paddle.getTop() >= this.getBottom()
                &&
                paddle.getBottom() <= this.getTop();
    }

    int getLeft() {
        return x - radius;
    }

    int getRight() {
        return x + radius;
    }

    int getTop() {
        return y + radius;
    }

    int getBottom() {
        return y - radius;
    }
}
