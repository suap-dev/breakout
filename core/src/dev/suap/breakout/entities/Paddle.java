package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dev.suap.breakout.interfaces.Collidable;

public class Paddle implements Collidable {
    float x;
    float y;
    float width;
    float height;
    boolean inCollision = false;

    public Paddle(float originX, float originY, float width, float height) {
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

    void setOriginX(float x) {
        this.x = x - width / 2;
    }

    void setOriginY(float y) {
        this.y = Gdx.graphics.getHeight() - y - height / 2;
    }

    public float getLeft() {
        return x;
    }

    public float getRight() {
        return x + width;
    }

    public float getTop() {
        return y + height;
    }

    public float getBottom() {
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
