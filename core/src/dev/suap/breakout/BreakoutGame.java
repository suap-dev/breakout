package dev.suap.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import dev.suap.breakout.entities.Ball;
import dev.suap.breakout.entities.Block;
import dev.suap.breakout.entities.Paddle;

import com.badlogic.gdx.graphics.GL20;

public class BreakoutGame extends ApplicationAdapter {
	ShapeRenderer renderer;
	Ball ball;
	Paddle paddle;
	Array<Block> blocks;

	@Override
	public void create() {
		renderer = new ShapeRenderer();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		ball = new Ball(
				width / 2, height / 2,
				15,
				4, 4);

		paddle = new Paddle(
				width / 2, 20,
				120, 20);

		blocks = Block.blockBatch(
				new Vector2((float) 0 + 8, ((float) height / 2)),
				new Vector2((float) width - 7, (float) height - 5),
				6, 6,
				15, 15);
	}

	@Override
	public void render() {
		ball.handleCollision(paddle);
		paddle.update();
		ball.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.begin(ShapeType.Filled);
		for (Block b : blocks) {
			b.draw(renderer);
		}
		paddle.draw(renderer);
		ball.draw(renderer);
		renderer.end();
	}
}
