package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int width;
    int height;

    public Paddle(int originX, int originY, int width, int height) {
        this.x = originX - width / 2;
        this.y = originY - height / 2;
        this.height = height;
        this.width = width;
    }

    public void update() {
        this.setOriginX(Gdx.input.getX());
        this.setOriginY(Gdx.input.getY());
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);
        // renderer.rec
    }

    private void setOriginX(int x) {
        this.x = x - width / 2;
    }

    private void setOriginY(int y) {
        this.y = Gdx.graphics.getHeight() - y - height / 2;
    }
}
