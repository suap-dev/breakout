package dev.suap.breakout.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.interfaces.Collidable;
import dev.suap.breakout.interfaces.Entity;
import dev.suap.breakout.interfaces.Rotatable;

public class CircularEntity implements Entity, Collidable, Rotatable {
    protected Vector2 origin;
    protected float radius;

    private Color color = Color.WHITE;

    protected boolean inCollision = false;

    public CircularEntity(Vector2 origin, float radius) {
        this.origin = origin;
        this.radius = radius;
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
        // nothing to do here for now...
    }

    @Override
    public boolean isInCollision() {
        return inCollision;
    }

    @Override
    public float getAngularVelocity() {
        // irrelevant, it's a circle shape
        return 0;
    }

    @Override
    public void setAngularVelocity(float angularVelocity) {
        // irrelevant, it's a circle shape
    }

    @Override
    public Vector2 getOrigin() {
        return origin;
    }

    @Override
    public float getOriginX() {
        return origin.x;
    }

    @Override
    public float getOriginY() {
        return origin.y;
    }

    @Override
    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    @Override
    public void setOriginX(float x) {
        origin.x = x;
    }

    @Override
    public void setOriginY(float y) {
        origin.y = y;
    }

    @Override
    public float getRotation() {
        // irrelevant, it's a circle shape
        return 0;
    }

    @Override
    public void setRotation(float rotation) {
        // irrelevant, it's a circle shape
    }
    
    public float getRadius() {
        return radius;
    }
}