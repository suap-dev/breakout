package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.classes.RectangularEntity;

public class Paddle extends RectangularEntity {
    public Paddle(Vector2 origin, float width, float height, float rotation) {
        super(origin, width, height, rotation);
    }

    public Paddle(Vector2 origin, float width, float height) {
        super(origin, width, height);
    }

    public Paddle(float x, float y, float width, float height, float rotation) {
        super(x, y, width, height, rotation);
    }

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update(float deltaTime) {
        this.setOriginX(Gdx.input.getX());
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
    }
}
