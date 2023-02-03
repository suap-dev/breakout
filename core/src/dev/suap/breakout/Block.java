package dev.suap.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

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
            Vector2 bottomLeft, Vector2 topRight,
            int columns, int rows,
            int horizontalSpacing, int verticalSpacing) {

        Array<Block> blocks = new Array<>();

        int left = (int) bottomLeft.x;
        int right = (int) topRight.x;
        int bottom = (int) bottomLeft.y;
        int top = (int) topRight.y;

        int width = right - left;
        int height = top - bottom;

        int blockWidth = (width - horizontalSpacing * (columns - 1)) / columns;
        int blockHeight = (height - verticalSpacing * (rows - 1)) / rows;

        int deltaX = (width - blockWidth) / (columns - 1);
        int deltaY = (height - blockHeight) / (rows - 1);

        for (int blockY = bottom; blockY + blockHeight <= top; blockY += deltaY) {
            for (int blockX = left; blockX + blockWidth <= right + 0.001; blockX += deltaX) {
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
