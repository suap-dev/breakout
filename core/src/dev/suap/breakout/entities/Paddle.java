package dev.suap.breakout.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.classes.RectangularEntity;

public class Paddle extends RectangularEntity {
    private Ball ball;
    private Vector2 ballLaunchVelocity;
    private boolean hasBall = false;
    private final float screenWidth;

    public Paddle(Vector2 origin, float width, float height, float rotation) {
        super(origin, width, height, rotation);
        screenWidth = Gdx.graphics.getWidth();
    }

    public Paddle(Vector2 origin, float width, float height) {
        super(origin, width, height);
        screenWidth = Gdx.graphics.getWidth();
    }

    public Paddle(float x, float y, float width, float height, float rotation) {
        super(x, y, width, height, rotation);
        screenWidth = Gdx.graphics.getWidth();
    }

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
        screenWidth = Gdx.graphics.getWidth();
    }

    public Ball spawnBall(float radius, Vector2 velocity) {
        ball = new Ball(
                getOriginX(),
                getOriginY() + getHeight() / 2 + radius,
                radius,
                0);
        ballLaunchVelocity = velocity;
        hasBall = true;

        return ball;
    }

    public Ball spawnBall(float radius, float velocity) {
        float v = (float) (Math.abs(velocity) / Math.sqrt(2));
        return spawnBall(radius, new Vector2(v, v));
    }

    private void reviveBall() {
        ball.setOriginX(getOriginX());
        ball.setOriginY(getOriginY() + getHeight() / 2 + ball.getRadius());
        ball.setVelocity(Vector2.Zero);
        ball.revive();
        hasBall = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        setOriginX(Gdx.input.getX());

        if (getLeft() < 1)
            setOriginX(1 + getWidth() / 2);
        else if (this.getRight() > screenWidth - 1)
            setOriginX(screenWidth - 1 - getWidth() / 2);

        if (hasBall) {
            this.ball.setOriginX(this.getOriginX());
            if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
                ball.setVelocity(ballLaunchVelocity.cpy());
                hasBall = false;
            }
        } else if (ball.isDead()) {
            reviveBall();
        }
        // this.setOriginY(Gdx.input.getY()); // usefull for testing
    }
}
