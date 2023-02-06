package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import dev.suap.breakout.interfaces.Collidable;

import com.badlogic.gdx.math.Vector2;

public class Block implements Collidable {
    Vector2 bottomLeftCorner;
    float width;
    float height;

    boolean inCollision = false;

    public Block(float x, float y, float width, float height) {
        bottomLeftCorner = new Vector2(x, y);
        this.height = height;
        this.width = width;
    }

    public Block(Vector2 bottomLeftCorner, float width, float height) {
        this.bottomLeftCorner = bottomLeftCorner;
        this.height = height;
        this.width = width;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(bottomLeftCorner.x, bottomLeftCorner.y, width, height);
    }

    public static Array<Block> blockBatch(
            Vector2 bottomLeft, Vector2 topRight,
            float columns, float rows,
            float horizontalSpacing, float verticalSpacing) {

        Array<Block> blocks = new Array<>();

        final float left = bottomLeft.x;
        final float right = topRight.x;
        final float bottom = bottomLeft.y;
        final float top = topRight.y;

        final float width = right - left;
        final float height = top - bottom;

        final float blockWidth = (width - horizontalSpacing * (columns - 1)) / columns;
        final float blockHeight = (height - verticalSpacing * (rows - 1)) / rows;

        final float deltaX = (width - blockWidth) / (columns - 1);
        final float deltaY = (height - blockHeight) / (rows - 1);

        for (float blockY = bottom; blockY + blockHeight <= top; blockY += deltaY) {
            for (float blockX = left; blockX + blockWidth <= right; blockX += deltaX) {
                blocks.add(new Block(blockX, blockY, blockWidth, blockHeight));
            }
        }

        return blocks;
    }

    void setOriginX(float x) {
        bottomLeftCorner.x = x - width / 2;
    }

    void setOriginY(float y) {
        bottomLeftCorner.y = Gdx.graphics.getHeight() - y - height / 2;
    }

    @Override
    public float getLeft() {
        return bottomLeftCorner.x;
    }

    @Override
    public float getRight() {
        return bottomLeftCorner.x + width;
    }

    @Override
    public float getTop() {
        return bottomLeftCorner.y + height;
    }

    @Override
    public float getBottom() {
        return bottomLeftCorner.y;
    }

    @Override
    public boolean isInCollision() {
        return this.inCollision;
    }

    @Override
    public void markAsColliding() {
        this.inCollision = true;
    }

    @Override
    public void resolveCollision() {
        this.inCollision = false;
    }
}
