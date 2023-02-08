package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.classes.CircularEntity;
import dev.suap.breakout.interfaces.Collidable;

public class Ball extends CircularEntity {
    private Vector2 velocity;

    /**
     * @param x        Origin x component.
     * @param y        Origin y component.
     * @param radius   Radius of the ball.
     * @param velocity Magnitude of the velocity, angle set to 45.
     */
    public Ball(float x, float y, float radius, float velocity) {
        this(new Vector2(x, y),
                radius,
                new Vector2(
                        (float) (velocity / Math.sqrt(2.0f)),
                        (float) (velocity / Math.sqrt(2.0f))));
    }

    public Ball(float x, float y, float radius, float velocityX, float velocityY) {
        super(new Vector2(x, y), radius);
        this.velocity = new Vector2(velocityX, velocityY);
    }

    public Ball(Vector2 origin, float radius, Vector2 velocity) {
        super(origin, radius);
        this.velocity = velocity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    private boolean isCollisionHorizontal = false;
    // private boolean isCollisionVertical = false; // not really needed

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        origin.add(velocity.x * deltaTime, velocity.y * deltaTime);
        if (origin.x - radius < 0 || origin.x + radius > Gdx.graphics.getWidth()) {
            velocity.x = -velocity.x;
        }
        if (origin.y - radius < 0 || origin.y + radius > Gdx.graphics.getHeight()) {
            velocity.y = -velocity.y;
        }
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
}
