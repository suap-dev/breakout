package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.interfaces.Collidable;

public class Ball implements Collidable {
    Vector2 origin;
    float radius;
    Color color = Color.WHITE;

    Vector2 velocity;

    private boolean inCollision = false;
    private boolean isCollisionHorizontal = false;
    // private boolean isCollisionVertical = false; // not really needed

    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        this.origin = new Vector2(x, y);
        this.radius = radius;
        this.velocity = new Vector2(xSpeed, ySpeed);
    }

    public Ball(Vector2 origin, float radius, Vector2 velocity) {
        this.origin = origin;
        this.radius = radius;
        this.velocity = velocity;
    }

    public void update() {
        origin.add(velocity);

        if (origin.x - radius < 0 || origin.x + radius > Gdx.graphics.getWidth()) {
            velocity.x = -velocity.x;
        }
        if (origin.y - radius < 0 || origin.y + radius > Gdx.graphics.getHeight()) {
            velocity.y = -velocity.y;
        }
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.circle(origin.x, origin.y, radius);
    }

    public void handleCollision(Collidable other) {
        boolean isCollidingWithOther = isColliding(other);

        if (!this.inCollision && !other.isInCollision() && isCollidingWithOther) {
            this.inCollision = true;
            other.markAsColliding();

            if (isCollisionHorizontal)
                velocity.y = -velocity.y;
            else
                velocity.x = -velocity.x;

        } else if (this.inCollision && other.isInCollision() && !isCollidingWithOther) {
            this.inCollision = false;
            other.resolveCollision();
        }
    }

    private boolean isCollidingHorisontally(Collidable other) {
        isCollisionHorizontal = origin.x < other.getRight()
                &&
                origin.x > other.getLeft()
                &&
                this.getBottom() < other.getTop()
                &&
                this.getTop() > other.getBottom();
        return isCollisionHorizontal;
    }

    private boolean isCollidingVertically(Collidable other) {
        return this.getLeft() < other.getRight()
                &&
                this.getRight() > other.getLeft()
                &&
                origin.y < other.getTop()
                &&
                origin.y > other.getBottom();
    }

    public boolean isColliding(Collidable other) {
        return isCollidingHorisontally(other) || isCollidingVertically(other);
    }

    @Override
    public boolean isInCollision() {
        return inCollision;
    }

    @Override
    public float getLeft() {
        return origin.x - radius;
    }

    @Override
    public float getRight() {
        return origin.x + radius;
    }

    @Override
    public float getTop() {
        return origin.y + radius;
    }

    @Override
    public float getBottom() {
        return origin.y - radius;
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
