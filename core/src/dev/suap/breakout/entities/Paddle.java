package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dev.suap.breakout.interfaces.Collidable;

public class Paddle implements Collidable {
    int x;
    int y;
    int width;
    int height;
    boolean inCollision = false;

    public Paddle(int originX, int originY, int width, int height) {
        this.x = originX - width / 2;
        this.y = originY - height / 2;
        this.height = height;
        this.width = width;
    }

    public void update() {
        this.setOriginX(Gdx.input.getX());
        this.setOriginY(Gdx.input.getY()); // usefull for testing
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);
    }

    void setOriginX(int x) {
        this.x = x - width / 2;
    }

    void setOriginY(int y) {
        this.y = Gdx.graphics.getHeight() - y - height / 2;
    }

    public int getLeft() {
        return x;
    }

    public int getRight() {
        return x + width;
    }

    public int getTop() {
        return y + height;
    }

    public int getBottom() {
        return y;
    }

    @Override
    public boolean isInCollision() {
        return inCollision;
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
