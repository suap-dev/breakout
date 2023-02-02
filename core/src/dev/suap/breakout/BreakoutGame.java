package dev.suap.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.GL20;

public class BreakoutGame extends ApplicationAdapter {
	ShapeRenderer renderer;
	Ball ball;
	Paddle paddle;

	@Override
	public void create() {
		renderer = new ShapeRenderer();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		ball = new Ball(
				width / 2, height / 2,
				15,
				5, 5);

		paddle = new Paddle(
				width / 2, 20,
				120, 20);

	}

	@Override
	public void render() {
		ball.exposeCollision(paddle);
		paddle.update();
		ball.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.begin(ShapeType.Filled);
		paddle.draw(renderer);
		ball.draw(renderer);
		renderer.end();
	}
}
