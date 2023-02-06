package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import dev.suap.breakout.interfaces.Collidable;

import com.badlogic.gdx.math.Vector2;

public class Block implements Collidable {
    float x;
    float y;
    float width;
    float height;
    boolean inCollision = false;

    public Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);
    }

    public static Array<Block> blockBatch(
            Vector2 bottomLeft, Vector2 topRight,
            float columns, float rows,
            float horizontalSpacing, float verticalSpacing) {

        Array<Block> blocks = new Array<>();

        float left = bottomLeft.x;
        float right = topRight.x;
        float bottom = bottomLeft.y;
        float top = topRight.y;

        float width = right - left;
        float height = top - bottom;

        float blockWidth = (width - horizontalSpacing * (columns - 1)) / columns;
        float blockHeight = (height - verticalSpacing * (rows - 1)) / rows;

        float deltaX = (width - blockWidth) / (columns - 1);
        float deltaY = (height - blockHeight) / (rows - 1);

        for (float blockY = bottom; blockY + blockHeight <= top; blockY += deltaY) {
            for (float blockX = left; blockX + blockWidth <= right; blockX += deltaX) {
                blocks.add(new Block(blockX, blockY, blockWidth, blockHeight));
            }
        }

        return blocks;
    }

    void setOriginX(float x) {
        this.x = x - width / 2;
    }

    void setOriginY(float y) {
        this.y = Gdx.graphics.getHeight() - y - height / 2;
    }

    public float getLeft() {
        return x;
    }

    public float getRight() {
        return x + width;
    }

    public float getTop() {
        return y + height;
    }

    public float getBottom() {
        return y;
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
