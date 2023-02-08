package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.classes.RectangularEntity;

public class Paddle extends RectangularEntity {
    private Ball ball;

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

    public Ball spawnBall(float radius, float velocity) {
        ball = new Ball(
                this.getOriginX(),
                this.getOriginY() + this.getHeight() / 2 + radius,
                radius,
                velocity);

        return ball;
    }

    @Override
    public void update(float deltaTime) {
        this.setOriginX(Gdx.input.getX());
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
    }
}
