package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.classes.RectangularEntity;

public class Paddle extends RectangularEntity {
    private Ball ball;
    private Vector2 ballLaunchVelocity;
    private boolean hasBall = false;

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
        float v = (float) (Math.abs(velocity) / Math.sqrt(2));
        ball = new Ball(
                this.getOriginX(),
                this.getOriginY() + this.getHeight() / 2 + radius,
                radius,
                0);
        ballLaunchVelocity = new Vector2(v, v);
        hasBall = true;

        return ball;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.setOriginX(Gdx.input.getX());
        if (hasBall) {
            this.ball.setOriginX(Gdx.input.getX());
            if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
                ball.setVelocity(ballLaunchVelocity);
                hasBall = false;
            }
        }
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
    }
}
