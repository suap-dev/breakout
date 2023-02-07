package dev.suap.breakout.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable {
    public void draw(ShapeRenderer shapeRenderer);

    public void setColor(Color color);

    public Color getColor();
}
