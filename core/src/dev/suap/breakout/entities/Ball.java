package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dev.suap.breakout.interfaces.Collidable;

public class Ball implements Collidable {
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

    public void handleCollision(Collidable other) {
        boolean isCollidingWithOther = isColliding(other);

        if (!this.inCollision && !other.isInCollision() && isCollidingWithOther) {
            this.inCollision = true;
            other.markAsColliding();

            this.ySpeed = -this.ySpeed;
        } else if (this.inCollision && other.isInCollision() && !isCollidingWithOther) {
            this.inCollision = false;
            other.resolveCollision();
        }
    }

    public boolean isColliding(Collidable other) {
        return this.getLeft() < other.getRight()
                &&
                this.getRight() > other.getLeft()
                &&
                this.getBottom() < other.getTop()
                &&
                this.getTop() > other.getBottom();
    }    

    @Override
    public boolean isInCollision() {
        return inCollision;
    }

    public int getLeft() {
        return x - radius;
    }

    public int getRight() {
        return x + radius;
    }

    public int getTop() {
        return y + radius;
    }

    public int getBottom() {
        return y - radius;
    }

    @Override
    public void markAsColliding() {
        this.inCollision = true;        
    }

    @Override
    public void resolveCollision() {
        this.inCollision = false;
    }
}
