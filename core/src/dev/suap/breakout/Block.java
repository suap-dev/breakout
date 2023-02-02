package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

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

    public Array<Block> blockBatch(int x, int y, int width, int height, int columns, int rows, int spacingX,
            int spacingY) {
        Array<Block> blocks = new Array<>();

        int blockWidth = (width - spacingX * columns) / (1 + columns);
        int blockHeight = (height - spacingY * rows) / (1 + rows);

        int deltaX = width - blockWidth / (columns - 1);
        int deltaY = height - blockHeight / (rows - 1);

        for (int blockY = y; blockY < height - blockHeight; blockY += deltaY) {
            for (int blockX = x; blockX < width - blockWidth; blockX += deltaX) {
                // TODO
            }
        }

        return blocks;
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
