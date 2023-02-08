package dev.suap.breakout.entities;


import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import dev.suap.breakout.classes.RectangularEntity;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public class Block extends RectangularEntity{
    private static final Random random = new Random();
    
    public Block(Vector2 origin, float width, float height, float rotation) {
        super(origin, width, height, rotation);
    }

    public Block(Vector2 origin, float width, float height) {
        super(origin, width, height);
    }

    public Block(float x, float y, float width, float height, float rotation) {
        super(x, y, width, height, rotation);
    }

    public Block(float x, float y, float width, float height) {
        super(x, y, width, height);
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

        Block b;
        Color c = Color.PINK;
        for (float blockY = bottom; blockY + blockHeight <= top; blockY += deltaY) {
            for (float blockX = left; blockX + blockWidth <= right; blockX += deltaX) {
                b = new Block(blockX, blockY, blockWidth, blockHeight, random.nextInt(720)-360);
                b.setColor(c.lerp(Color.ORANGE, 0.025f).cpy());
                b.addInterpolatedRotation(0, random.nextFloat()*2, Interpolation.swingOut);
                blocks.add(b);
            }
        }

        return blocks;
    }
}
