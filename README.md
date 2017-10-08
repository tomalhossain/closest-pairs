## Closest Pair

This Java program utilizes the divide-and-conquer paradigm to find the closest pair of points in a set of points in a given 2-D plane.

### Features of the Program

1. The input may be arbitrarily large, up to the limits of physical memory
2. The input must be read from standard input where the first line contains the number of points in the input and each subsequent line contains the x-coordinate of a point followed by the y-coordinate, separated by a single space;
3. The final line of output shows the closest pair of points and the distance between then in the format (x1,y1)-(x2,y2)=d, where x1 is less than x2 (or, if x1 and x2 are equals, y1 is less than y2)
4. The points in the input are distinct.
5. The run time is O(n�logn) where n is the number of points in the given 2-D plane.

### Files Included in this Repository

1. FindClosest.java - Contains the main function which prints the closest pair to standard output.
2. PointPair.java - A pair of points and the distance between them, written by Professor Jim Glenn of Amherst College.
3. PointSet.java - This class houses the sorting algorithms, closest pair recursive function, and a brute force function that in tandem returns the closest pair within a list of points in a coordinate plane.
4. MakeRandomPointSet.java - Outputs a suitable list of points for use as input to serve as the command-line argument when running FindClosest
5. random_100 - Sample input file for to serve as the command-line argument when running FindClosest

### Running the Program

The program can be run by either manually specifying a number of xy coordinates via standard input or through specifying the name of a file containing a list of xy coordinates to be read in by the program.

#### Running Manually

```shell
java FindClosest
```

Then input the number of points you wish to populate your coordinate-plane with. In this example, we've chosen 4 points. Next, specify, line by line, the list of xy coordinates corresponding with those 4 points.

4
-2.05 2.6
1.56 3.0
-0.4 -0.6
5 1

The output for this process would be the following:

(-2.05,2.6)-(-0.4,-0.6)=3.6003472054789385

#### Running From A Text File

If you've got a text file formatted exactly as the manually inputted text above, you may specify that file to be read in by the program like this:

```shell
java FindClosest < sample_text_file.txt
```

You may use the MakeRandomPointSet program included in this repository to automatically generate point set files that can be used in the aforementioned manner to run the closest pairs program. 4 parameters are needed to run the MakeRandomPointSet program:

1. The number of points you wish to generate.
2. The x-range of said points.
3. The y-range of said points.
4. The name of the text file you wish to write the output of the program to.

Thus, the following command would generate 9000 points in the x and y range of 50 and save the results to a file named 'sample_point_set.txt':

```shell
java MakeRandomPointSet 9000 50 50 > sample_point_set.txt
```

That's it! Happy pairing.
