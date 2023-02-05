package dev.suap.breakout.interfaces;

public interface Collidable {    
    int getLeft();
    int getRight();
    int getBottom();
    int getTop();
    
    void setInCollision(boolean inCollision);
    boolean isInCollision();
}
