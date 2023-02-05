package dev.suap.breakout.interfaces;

public interface Collidable {    
    int getLeft();
    int getRight();
    int getBottom();
    int getTop();
    
    void markAsColliding();
    void resolveCollision();
    boolean isInCollision();
}
