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

    public static Array<Block> blockBatch(
            int x, int y,
            int width, int height,
            int columns, int rows,
            int horizontalSpacing, int verticalSpacing) {
        Array<Block> blocks = new Array<>();

        int blockWidth = (width - horizontalSpacing * (columns - 1)) / columns;
        int blockHeight = (height - verticalSpacing * (rows - 1)) / rows;

        int deltaX = (width - blockWidth) / (columns - 1);
        int deltaY = (height - blockHeight) / (rows - 1);

        int maxX = x + width;
        int maxY = y + height;

        for (int blockY = y; blockY <= maxY - blockHeight; blockY += deltaY) {
            for (int blockX = x; blockX <= maxX - blockWidth; blockX += deltaX) {
                blocks.add(new Block(blockX, blockY, blockWidth, blockHeight));
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
