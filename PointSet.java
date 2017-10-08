import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This class houses the sorting algorithms, closest pair recursive function, and a brute force function that in tandem returns the closest pair
 * within a list of points in a coordinate plane. 
 * 
 * @author Tomal Hossain 
 * Data Structures and Algorithms II 
 * October 1, 2015
 * 
 */

public class PointSet {

    public static void quickSortX(Point2D.Double xPoints[], int left, int right) {
        int index = partitionX(xPoints, left, right);
        if (left < index - 1)
              quickSortX(xPoints, left, index - 1);
        if (index < right)
              quickSortX(xPoints, index, right);
  }
    
    public static int partitionX(Point2D.Double[] xPoints, int left, int right) {
          int i = left, j = right;
          Point2D.Double tmp;
          Point2D.Double pivot = xPoints[(left + right) / 2];
          while (i <= j) {
                while (xPoints[i].getX() < pivot.getX() ) {
                      i++;
                }
                while (xPoints[j].getX() > pivot.getX()) {
                      j--;
                }
                if (i <= j) {
                      tmp = xPoints[i];
                      xPoints[i] = xPoints[j];
                      xPoints[j] = tmp;
                      i++;
                      j--;
                }
          }
          return i;
    }
   
    public static void quickSortY(Point2D.Double yPoints[], int left, int right) {
        int index = partitionY(yPoints, left, right);
        if (left < index - 1)
              quickSortY(yPoints, left, index - 1);
        if (index < right)
              quickSortY(yPoints, index, right);
  }
    
    public static int partitionY(Point2D.Double[] yPoints, int left, int right) {
          int i = left, j = right;
          Point2D.Double tmp;
          Point2D.Double pivot = yPoints[(left + right) / 2];
          while (i <= j) {
                while (yPoints[i].getY() < pivot.getY() ) {
                      i++;
                }
                while (yPoints[j].getY() > pivot.getY()) {
                      j--;
                }
                if (i <= j) {
                      tmp = yPoints[i];
                      yPoints[i] = yPoints[j];
                      yPoints[j] = tmp;
                      i++;
                      j--;
                }
          };
          return i;
    }
    /**
     * @param The original list of Point2D.Doubles
     * @return Calls the closestPair function with parameters of
     * two sorted lists of the passed in collection of 
     * Point2D.Doubles - one sorted by x-coordinate and the other by
     * y-coordinate. 
     */

	public static PointPair preProcessing (Point2D.Double[] points) { 
		
		Point2D.Double[] xPoints = new Point2D.Double[points.length];
		Point2D.Double[] yPoints = new Point2D.Double[points.length]; 
		
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = points[i];
			yPoints[i] = points[i];
		}
	
		quickSortX(xPoints, 0, xPoints.length-1);
		quickSortY(yPoints, 0, yPoints.length-1);
		
		return closestPair (xPoints, yPoints); 
	}
	/**
	 * Most important function that will find the solution to the question.
	 *  
	 */
	public static PointPair closestPair (Point2D.Double[] xPoints, Point2D.Double[] yPoints) {

		if (xPoints.length <= 3) { 
			return bruteForce (xPoints);  
		}
		
		else { // this else statement sets up the recursive calls to the closestPair function until the parameter lengths 
			   // are small enough for bruteForce to take over 
		
		Point2D.Double[] xPointsLeft = new Point2D.Double[xPoints.length/2];
		Point2D.Double[] xPointsRight = new Point2D.Double[xPoints.length - xPointsLeft.length];
		Point2D.Double[] yPointsLeft = new Point2D.Double[xPoints.length/2];
		Point2D.Double[] yPointsRight = new Point2D.Double[xPoints.length - yPointsLeft.length];
	
		for (int i = 0; i < xPointsLeft.length; i++) { // Fills the two left halves of the sets sorted by x and y (for now y array  is x - later sorted by y) 
			xPointsLeft[i] = xPoints[i];
			yPointsLeft[i] = xPoints[i];
		}
		
		for (int i = xPointsLeft.length; i < xPoints.length; i++) { // Divides the two sets sorted by x and y into left halves 
			xPointsRight[i - xPointsLeft.length] = xPoints[i]; 		
			yPointsRight[i - xPointsLeft.length] = xPoints[i]; 
		}
		
		quickSortX(yPointsLeft, 0, yPointsLeft.length - 1); // sorts the other left half by y-coordinate, after it's been sorted by x-coordinate
		quickSortX(yPointsRight, 0, yPointsRight.length - 1); // sorts the other left half by y-coordinate, after it's been sorted by x-coordinate
		
		PointPair leftPair = closestPair (xPointsLeft, yPointsLeft); 
		PointPair rightPair = closestPair (xPointsRight, yPointsRight); 
		
		PointPair closestPair = leftPair; 
		
		if (rightPair.getDistance() < closestPair.getDistance()) {
			closestPair = rightPair; // closestPair will be the greater of the final two pairs of points of the two main halves of the coordinate plane
		}
		
		double dist = closestPair.getDistance();
		
		// Checking across the vertical division of the point set

		ArrayList <Point2D.Double> yPoints2 = new ArrayList <Point2D.Double>();  
		Point2D.Double median = xPoints[xPoints.length/2]; // median will be the point on the dividing line on each recursive call of closestPairs 
		
		int a = xPoints.length/2 - 1;	 
		int b = xPoints.length/2 + 1;     
			
		while (a >= 0 && Math.abs(median.getX() - xPoints[a].getX()) <= dist) { // only adds points to the resulting list if x-coordinate is within dist less than the median.
			yPoints2.add(xPoints[a]);
			a--;
		}
		
		while (b < xPoints.length && Math.abs(median.getX() - xPoints[b].getX()) <= dist) { // only adds points to the resulting list if x-coordinate is within dist greater than.
			yPoints2.add(xPoints[b]);
			b++;
		}
		yPoints2.add(median); // It's important to add the median point itself to the resulting array! 
		
		Point2D.Double [] yPoints2b = new Point2D.Double[yPoints2.size()];
		yPoints2b = yPoints2.toArray(yPoints2b); // The ArrayList is converted into a Java array as the other lists in order to be sorted by quickSortY 
		
		quickSortY(yPoints2b, 0, yPoints2b.length - 1); 
	
		for (int i = 0; i < yPoints2b.length; i++) {
			int j = i + 1;
			while (j < yPoints2b.length && Math.abs(yPoints2b[i].getY() - yPoints2b[j].getY()) <= dist) { // this prevents checking all of the points
				PointPair check = new PointPair(yPoints2b[i], yPoints2b[j]);							  // within each resulting array. Rather only,	
				if (check.getDistance() < dist) {														  // points withing vertical dist are checked. 
					closestPair = check; 
					dist = closestPair.getDistance(); 
				}
			j++; 
			}
		}	
		return closestPair;
	}	
}
	/**
	 * 
	 * @return Returns the point pair that is closest within a 2 or 3 grouping. 
	 */	
	
	public static PointPair bruteForce (Point2D.Double[] points) { 
		if (points.length == 2) { 			
			PointPair pair = new PointPair(points[0], points[1]);
			return pair;
		}
		else {
			PointPair pair0 = new PointPair(points[0], points[1]);
			PointPair pair1 = new PointPair(points[1], points[2]);
			PointPair pair2 = new PointPair(points[0], points[2]);
			
			if (pair0.getDistance() < pair1.getDistance() && pair0.getDistance() < pair2.getDistance() )
				return pair0;
			else if (pair1.getDistance() < pair0.getDistance() && pair1.getDistance() < pair2.getDistance() )
				return pair1;
			else 
				return pair2;	
		}	
	}
}	