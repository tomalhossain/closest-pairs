/**
 * Outputs a random list of points.  The number of points and optionally the
 * bounds are given on the command line in the format
 * java MakeRandomPointSet n [x-range [y-range]]
 * where the range used will be [-x-range,-y-range] to [x-range,y-range]
 * where y-range defaults to x-range and if neither is given then the range
 * is (0,0)-(1,1).
 *
 * @author Jim Glenn
 * @version 0.1 2012-02-03
 */

public class MakeRandomPointSet {
    public static void main(String[] args)
    {
        if (args.length == 0)
            {
                System.err.println("USAGE: java MakeRandomPointSet n [xRange [yRange]]");
                System.exit(1);
            }

        // get number of points from command line
        int n = Integer.parseInt(args[0]);

        // default x- and y-ranges to [0.0, 1.0)
        double minX = 0.0;
        double minY = 0.0;
        double maxX = 1.0;
        double maxY = 1.0;

        // read x range
        if (args.length >= 2)
            {
                double xRange = Double.parseDouble(args[1]);
                minX = -xRange;
                maxX = xRange;

                // default y range to x range
                minY = minX;
                maxY = maxX;
            }

        // read y range
        if (args.length >= 3)
            {
                double yRange = Double.parseDouble(args[2]);
                minY = -yRange;
                maxY = yRange;
            }

        // figure out scale factor to convert output of Math.random to
        // the desired range
        double xScale = maxX - minX;
        double yScale = maxY - minY;
        
        // make the output
        System.out.println(n);
        for (int i = 0; i < n; i++)
            {
                double x = Math.random() * xScale + minX;
                double y = Math.random() * yScale + minY;
                System.out.println(x + " " + y);
            }
    }
}
	

