package dev.suap.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class BreakoutGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	Array<Ball> balls = new Array<>();
	Random random = new Random();

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		for (int i = 0; i < 10; i++) {
			balls.add(new Ball(
					random.nextInt(Gdx.graphics.getWidth()),
					random.nextInt(Gdx.graphics.getHeight()),
					random.nextInt(100),
					random.nextInt(15),
					random.nextInt(15)));
		}

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);
		for (Ball ball : balls) {
			ball.update();
			ball.draw(shapeRenderer);
		}
		shapeRenderer.end();
	}
}
