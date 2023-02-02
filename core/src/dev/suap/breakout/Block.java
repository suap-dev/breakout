package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    int x;
    int y;
    int width;
    int height;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void update() {
        this.setOriginX(Gdx.input.getX());
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
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

    int getLeft() {
        return x;
    }

    int getRight() {
        return x + width;
    }

    int getTop() {
        return y + height;
    }

    int getBottom() {
        return y;
    }
}
