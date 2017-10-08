import java.awt.geom.Point2D;

/**
 * A pair of points and the distance between them.
 *
 * @author Jim Glenn
 * @version 0.3 2015-09-10 refactored to remove speedup tricks
 * @version 0.2 2012-02-02
 */

public class PointPair {
    /**
     * The points.
     */
    private Point2D.Double p1;
    private Point2D.Double p2;

    /**
     * Creates a pair of points.
     *
     * @param p1 one point
     * @param p2 another point
     */
    public PointPair(Point2D.Double p1, Point2D.Double p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Returns the first point in this pair.
     *
     * @return the first point in this pair
     */
    public Point2D.Double getPoint1()
    {
        return p1;
    }

    /**
     * Returns the second point in this pair.
     *
     * @return the second point in this pair
     */
    public Point2D.Double getPoint2()
    {
        return p2;
    }

    /**
     * Returns the distance between the points in this pair.
     *
     * @return the distance between the points in this pair
     */
    public double getDistance()
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    /**
     * Determines if this pair of points is closer apart or farther
     * apart than the given pair.  The return values is negative
     * zero, or positive according to whether this pair is closer,
     * equal, or farther than the given pair.
     *
     * @param p a pair
     * @return negative if this pair is closer, positive if farther, 0 otherwise
     */
    public int closerThan(PointPair p)
    {
        return (int)Math.signum(getDistance() - p.getDistance());
    }

    /**
     * Normalizes this pair so the point with the smallest x-coordinate
     * comes first.  If the x-coordinates are the same then the point
     * with the smallest y-coordinate will come first.
     *
     * @return this pair
     */
    public PointPair normalize()
    {
        if (p1.x > p2.x || p1.x == p2.x && p1.y > p2.y)
            {
                Point2D.Double temp = p1;
                p1 = p2;
                p2 = temp;
            }

        return this;
    }
    /**
     * Returns a printable representation of this pair and the distance
     * between.
     *
     * @return a printable representation of this pair and distance
     */
    public String toString()
    {
	normalize();
        return "(" + p1.x + "," + p1.y +")-(" + p2.x + "," + p2.y + ")=" + getDistance();
    }
}