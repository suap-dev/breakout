package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.interfaces.Collidable;

public class Paddle implements Collidable {
    Vector2 bottomLeftCorner;
    float width;
    float height;

    boolean inCollision = false;

    public Paddle(float originX, float originY, float width, float height) {
        this(new Vector2(originX - width / 2, originY - height / 2), width, height);
    }

    public Paddle(Vector2 bottomLeftCorner, float width, float height) {
        this.bottomLeftCorner = bottomLeftCorner;
        this.height = height;
        this.width = width;
    }

    public void update() {
        this.setOriginX(Gdx.input.getX());
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(bottomLeftCorner.x, bottomLeftCorner.y, width, height);
    }

    void setOriginX(float x) {
        bottomLeftCorner.x = x - width / 2;
    }

    void setOriginY(float y) {
        bottomLeftCorner.y = Gdx.graphics.getHeight() - y - height / 2;
    }

    @Override
    public float getLeft() {
        return bottomLeftCorner.x;
    }

    @Override
    public float getRight() {
        return bottomLeftCorner.x + width;
    }

    @Override
    public float getTop() {
        return bottomLeftCorner.y + height;
    }

    @Override
    public float getBottom() {
        return bottomLeftCorner.y;
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
