package physics.collision;

import org.joml.Vector2f;

/**
 * <h1>Azurite</h1>
 * <p>
 * The GJKSM shape is described by all its support vectors.
 * A support vector is a point on the convex hull of a shape, that is furthest in the direction of a specified vector v.
 *
 * @author Juyas
 * @version 18.06.2021
 * @since 18.06.2021
 */
public abstract class GJKSMShape {

    private Vector2f position = new Vector2f(0, 0);

    public final void setPosition(Vector2f position) {
        this.position = position;
        adjust();
    }

    public final Vector2f position() {
        return position;
    }

    /**
     * Will be called by {@link this#setPosition(Vector2f)} after the new position was set.
     * Used to recalculate the absolute coordinates.
     */
    public abstract void adjust();

    /**
     * The center point or weight point of the shape.
     *
     * @return centroid of the shape
     */
    public abstract Vector2f centroid();

    /**
     * According to GJKSM this method is supposed to calculate the point of the shape, that is most in direction of v.
     * The general rule is, the more primitive the shape is, the more efficient this method can be.
     * This method may be described as max{v*x,x element of Shape} for any complex shape.
     *
     * @param v the direction
     * @return the point of the shape that is most in the direction of v
     */
    public abstract Vector2f supportPoint(Vector2f v);

}