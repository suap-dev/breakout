package dev.suap.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

import dev.suap.breakout.entities.Ball;
import dev.suap.breakout.entities.Block;
import dev.suap.breakout.entities.Paddle;

import com.badlogic.gdx.graphics.GL20;

public class BreakoutGame extends ApplicationAdapter {
	ShapeRenderer renderer;
	Ball ball;
	Paddle paddle;

	Array<Block> blocks;
	ArrayIterator<Block> iter;
	Block b;

	@Override
	public void create() {
		renderer = new ShapeRenderer();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		ball = new Ball(
				width / 2, height / 4,
				15,
				4, 4);

		paddle = new Paddle(
				width / 2, 20,
				120, 20);

		blocks = Block.blockBatch(
				new Vector2((float) 0 + 8, height / 2),
				new Vector2(width - 7, height - 5),
				6, 6,
				15, 15);
	}

	@Override
	public void render() {
		// paddle update handles the mouse movement as well
		paddle.update();
		// ball moves on its own
		ball.update();

		// begin creating the frame
		renderer.begin(ShapeType.Filled);

		// handle collision for all blocks and then draw them
		for (iter = blocks.iterator(); iter.hasNext();) {
			b = iter.next();
			ball.handleCollision(b);
			if (b.isInCollision()) {
				iter.remove();
				ball.resolveCollision();
			} else {
				b.draw(renderer);
			}
		}
		// handle collision with the paddle
		ball.handleCollision(paddle);

		// draw the ball and the paddle
		paddle.draw(renderer);
		ball.draw(renderer);

		// clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// and put the frame in it
		renderer.end();
	}
}
