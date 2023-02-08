package dev.suap.breakout.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.interfaces.Collidable;
import dev.suap.breakout.interfaces.Entity;

public class CircularEntity implements Entity, Collidable {
    protected Vector2 origin;
    protected float radius;
    private Color color = Color.WHITE;

    protected boolean inCollision = false;

    protected Vector2 velocity;

    public CircularEntity(Vector2 origin, float radius, Vector2 velocity) {
        this.origin = origin;
        this.radius = radius;
        this.velocity = velocity;
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

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.circle(origin.x, origin.y, radius);
    }

    @Override
    public void update(float deltaTime) {
        origin.add(velocity.x * deltaTime, velocity.y * deltaTime);
        // if (origin.x - radius < 0 || origin.x + radius > Gdx.graphics.getWidth()) {
        // velocity.x = -velocity.x;
        // }
        // if (origin.y - radius < 0 || origin.y + radius > Gdx.graphics.getHeight()) {
        // velocity.y = -velocity.y;
        // }
    }

    @Override
    public boolean isInCollision() {
        return inCollision;
    }
}