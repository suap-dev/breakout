package dev.suap.breakout.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import dev.suap.breakout.interfaces.Collidable;
import dev.suap.breakout.interfaces.Entity;
import dev.suap.breakout.interfaces.Rotatable;

public class RectangularEntity extends Rectangle implements Entity, Rotatable, Collidable {
	private Vector2 translationMatrix;
	private float angularVelocity;
	private float rotation;

	// for interpolation
	private boolean isInterpolating = false;
	private float startingRotation;
	private float targetRotation;
	private float interpolationTime;
	private float progress = 1.1f; // if progress > 1 then interpolation is over
	private Interpolation method;

	private boolean inCollision = false;

	private Color color = Color.WHITE;

	public RectangularEntity(Vector2 origin, float width, float height, float rotation) {
		super(-width / 2, -height / 2, width, height);
		this.translationMatrix = origin;
		this.rotation = rotation;
	}

	public RectangularEntity(Vector2 origin, float width, float height) {
		this(origin, width, height, 0);
	}

	public RectangularEntity(){
		super();
	}

	/**
	 * Contructs a rectangle.
	 * 
	 * @param x        The x coordinate of the left edge of the rectangle.
	 * @param y        The y coordinate of the bottom edge of the rectangle.
	 * @param width    The width of the rectangle.
	 * @param height   The height of the rectangle.
	 * @param rotation The rotation of the rectangle. CAUTION: This rotation is just
	 *                 visual, doesn't work with collision detection!
	 */
	public RectangularEntity(float x, float y, float width, float height, float rotation) {
		super(-width / 2, -height / 2, width, height);
		this.translationMatrix = new Vector2(x + width / 2, y + height / 2);
		this.rotation = rotation;
		this.angularVelocity = 0;
	}

	/**
	 * Contructs a rectangle.
	 * 
	 * @param x      The x coordinate of the left edge of the rectangle.
	 * @param y      The y coordinate of the bottom edge of the rectangle.
	 * @param width  The width of the rectangle.
	 * @param height The height of the rectangle.
	 */
	public RectangularEntity(float x, float y, float width, float height) {
		this(x, y, width, height, 0);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param targetRotation Rotation of the object after interpolation ends.
	 * @param time           In seconds - time span of the interpolation.
	 * @param method         Interpolation method.
	 */
	public void addInterpolatedRotation(float targetRotation, float time, Interpolation method) {
		this.isInterpolating = true;
		this.progress = 0;
		this.startingRotation = this.rotation;
		this.targetRotation = targetRotation;
		this.interpolationTime = time;
		this.method = method;
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.translate(translationMatrix.x, translationMatrix.y, 0);
		shapeRenderer.rotate(0, 0, 1, rotation);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.identity();
	}

	@Override
	public void update(float deltaTime) {
		if (isInterpolating) {
			if (progress < 1.0f) {
				rotation = method.apply(startingRotation, targetRotation, progress);
				progress += deltaTime / this.interpolationTime;
			} else {
				rotation = targetRotation;
				isInterpolating = false;
			}
		} else {
			rotation += angularVelocity * deltaTime;
		}
	}

	/**
	 * @param angularVelocity Angular velocity in degrees.
	 */
	@Override
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	@Override
	public Vector2 getOrigin() {
		return translationMatrix;
	}

	@Override
	public void setOrigin(Vector2 origin) {
		this.translationMatrix = origin;
	}

	@Override
	public void setOriginX(float x) {
		this.translationMatrix.x = x;
	}

	@Override
	public void setOriginY(float y) {
		this.translationMatrix.y = y;
	}

	@Override
	public float getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/** @return the x-coordinate of the bottom left corner */
	public float getX() {
		return x + translationMatrix.x;
	}

	/**
	 * Sets the x-coordinate of the bottom left corner
	 * 
	 * @param x The x-coordinate
	 * @return this rectangle for chaining
	 */
	@Override
	public Rectangle setX(float x) {
		this.x = x - translationMatrix.x;

		return this;
	}

	/** @return the y-coordinate of the bottom left corner */
	@Override
	public float getY() {
		return y + translationMatrix.y;
	}

	/**
	 * Sets the y-coordinate of the bottom left corner
	 * 
	 * @param y The y-coordinate
	 * @return this rectangle for chaining
	 */
	@Override
	public Rectangle setY(float y) {
		this.y = y - translationMatrix.y;

		return this;
	}

	

    @Override
    public float getLeft() {
        return getX();
    }

    @Override
    public float getRight() {
        return getX() + getWidth();
    }

    @Override
    public float getBottom() {
        return getY();
    }

    @Override
    public float getTop() {
        return getY() + getHeight();
    }

    @Override
    public boolean isInCollision() {
        return this.inCollision;
    }

    @Override
    public void markAsColliding() {
        this.inCollision = true;
    }

    @Override
    public void resolveCollision() {
        this.inCollision = false;
    }
}
