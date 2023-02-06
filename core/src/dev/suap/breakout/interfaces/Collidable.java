package dev.suap.breakout.interfaces;

public interface Collidable {
    float getLeft();

    float getRight();

    float getBottom();

    float getTop();

    void markAsColliding();

    void resolveCollision();

    boolean isInCollision();
}
