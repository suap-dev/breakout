package dev.suap.breakout.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Rotatable {
    /**
     * @return Returns the value of intended rotation in degrees per unit of time.
     */
    public float getAngularVelocity();

    /**
     * @param angularVelocity Angular velocity in degrees. The shape isn't requred
     *                        to act on this value.
     */
    public void setAngularVelocity(float angularVelocity);

    /**
     * @return Return a point representing the center of rotation.
     */
    public Vector2 getOrigin();

    public float getOriginX();
    public float getOriginY();

    /**
     * @param origin A point around which the rotation is applied.
     */
    public void setOrigin(Vector2 origin);

    public void setOriginX(float x);
    public void setOriginY(float y);

    /**
     * @return Current rotation in degrees.
     */
    public float getRotation();

    /**
     * @param rotation Angle in degrees. It sets the rotation of a shape.
     */
    public void setRotation(float rotation);
}
