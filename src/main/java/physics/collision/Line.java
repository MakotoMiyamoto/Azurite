package physics.collision;

import org.joml.Vector2f;

/**
 * <h1>Azurite</h1>
 * <p>
 * The GJKSM shape implementation of a line.
 *
 * @author Juyas
 * @version 19.06.2021
 * @since 19.06.2021
 */
public class Line extends GJKSMShape {

    private final Vector2f relativeA, relativeB;
    private Vector2f absoluteA, absoluteB;

    private final Vector2f relativeCentroid;
    private Vector2f absoluteCentroid;

    public Line(Vector2f relativeA, Vector2f relativeB) {
        this.relativeA = new Vector2f(relativeA);
        this.relativeB = new Vector2f(relativeB);
        //calc relative centroid
        this.relativeCentroid = this.relativeB.sub(this.relativeA, new Vector2f());
        float length = this.relativeCentroid.length();
        this.relativeCentroid.normalize(length / 2);
    }

    public Vector2f getRelativeA() {
        return relativeA;
    }

    public Vector2f getRelativeB() {
        return relativeB;
    }

    public Vector2f getAbsoluteA() {
        return absoluteA;
    }

    public Vector2f getAbsoluteB() {
        return absoluteB;
    }

    @Override
    public void adjust() {
        this.absoluteA = position().add(relativeA, new Vector2f());
        this.absoluteB = position().add(relativeB, new Vector2f());
        this.absoluteCentroid = position().add(relativeCentroid, new Vector2f());
    }

    @Override
    public Vector2f centroid() {
        return absoluteCentroid;
    }

    @Override
    public Vector2f supportPoint(Vector2f v) {
        return absoluteA.dot(v) > absoluteB.dot(v) ? absoluteA : absoluteB;
    }

}