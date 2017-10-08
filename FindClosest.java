import java.util.*;
import java.awt.geom.Point2D;

public class FindClosest
{
    private static Scanner scan;

	public static void main(String[] args)
    {
        scan = new Scanner(System.in);
	// read number of points and make arrays
	int numPoints = scan.nextInt(); // reads the quantity of points in a point set
	Point2D.Double[] points = new Point2D.Double[numPoints]; // instantiates the Point set

	// read points and add to array
	for (int p = 0; p < numPoints; p++) {
		points[p] = new Point2D.Double(scan.nextDouble(), scan.nextDouble()); 
	}
	// entry point into PointSet class which houses the closest pair function 
	PointPair closestPair = PointSet.preProcessing (points);
	
	System.out.println(closestPair.toString()); 
    }
}