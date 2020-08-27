package closestPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairAlgorithm {

	/*
	 * Method to use the brute force way to find the smallest distance. It'll run through the given array of points and for each point, 
	 * compute the distance between it and all subsequent points in the input array and return the minimum.
	 * Parameters:
	 * 	points: Array of points to compare. 
	 */
	public static PointPair bruteForce(PlanePoint[] points)
	{
		double currentMin = Double.MAX_VALUE;		//current minimum distance.
		double currentDistance;						//distance between the current points.
		
		PlanePoint closestA = null;
		PlanePoint closestB = null;
		
		
		for(int i = 0; i < points.length; i++)
		{
			for(int j = i+1; j < points.length; j++)
			{
				currentDistance = PointPair.euclideanDistance(points[i], points[j]);
				
				if(currentDistance < currentMin)
				{
					currentMin = currentDistance;
					closestA = points[i];
					closestB = points[j];
				}
			}
		}
		return new PointPair(closestA, closestB);
	}
	
	/*
	 * Execution method for the closest pair algorithm. It will take an array of points and compute the closest pair. 
	 * If the array has length 1 it will print an error message. Otherwise, it will execute the closest pair algorithm 
	 * after it creates copies of the input points and sorts them by x and y coordinate for the algorithm to use later.
	 * Parameters:
	 * 	points: an array of points in a 2d plane.
	 */
	public static void executeClosestPair(PlanePoint[] points)
	{
		if(points.length == 1)
		{
			System.out.println("Not enough points given");
		}
		else
		{
			PlanePoint[] pointsSortedOnX = Arrays.copyOf(points, points.length);
		
			Arrays.sort(pointsSortedOnX, Comparator.comparing(PlanePoint::getX));
		
			PlanePoint[] pointsSortedOnY = Arrays.copyOf(points, points.length);
		
			Arrays.sort(pointsSortedOnY, Comparator.comparing(PlanePoint::getY));
		
			PointPair returnVal = closestPair(pointsSortedOnX, pointsSortedOnY);
		
			System.out.println("Closest Points " + returnVal.toString());
			System.out.println("Distance between them: " + PointPair.euclideanDistance(returnVal));
		}
	}
	
	/*
	 * This method is the true implementation of the algorithm. It is given two arrays of points and will use the brute force method when the length is <=3.
	 * Otherwise, it'll break the passed in arrays of points into Q and R, where the points in Q are the points with the smallest X and Y coordinates 
	 * and the points in R are the points with the largest X and Y coordinates. It will then recurse on Q and R looking for the closest pair in each. 
	 * Once the recursion ends and the smallest points with Q and R are located, one of 3 things are possible:
	 * 1. The points with the smallest euclidean distance between them are in Q.
	 * 2. The points with the smallest euclidean distance between them are in R. 
	 * 3. The points with the smallest euclidean distance between them are split between Q and R.
	 * In cases 1 and 2 we already have the solution, however, if we're in case 3, it is more complicated. To check for that, we first find the 
	 * min between the closest pair in Q and R, that is our closest pair of points, then we make a call to closest split pair for the combine step.
	 * That method will compute the smallest pair split across Q and R. If that pair is closer than the closest pairs in Q and R, return it. 
	 * Otherwise, return the closest pair from Q or R, whichever is closer.
	 * Parameters:
	 * 	pointsSortedOnX: the input points sorted on their X coordinate.
	 * 	pointsSortedOnY: the input points sorted on their Y coordinate.
	 */
	public static PointPair closestPair(PlanePoint[] pointsSortedOnX, PlanePoint[] pointsSortedOnY)
	{
		if(pointsSortedOnX.length <= 3)
		{
			return bruteForce(pointsSortedOnX);
		}
		else
		{
			PlanePoint[] pointsWithSmallestXCoordinates = createQX(pointsSortedOnX);
		
			PlanePoint[] pointsWithSmallestYCoordinates = createQY(pointsSortedOnY);
		
			PlanePoint[] pointsWithLargestXCoordinates = createRX(pointsSortedOnX);
		
			PlanePoint[] pointsWithLargestYCoordinates = createRY(pointsSortedOnY);
			
			
			PointPair closestPairInQ = closestPair(pointsWithSmallestXCoordinates, pointsWithSmallestYCoordinates);
		
			PointPair closestPairInR = closestPair(pointsWithLargestXCoordinates, pointsWithLargestYCoordinates);
			
		
			double delta = Math.min(PointPair.euclideanDistance(closestPairInQ), PointPair.euclideanDistance(closestPairInQ));
		
			PointPair closestCurrentSplitPair = closestSplitPair(pointsSortedOnX, pointsSortedOnY, delta);
			
			PointPair currentBest = closestPairInQ;
			
			if(PointPair.compareDistance(closestPairInR, currentBest) < 0)
			{
				currentBest = closestPairInR;
			}
			
			if(PointPair.compareDistance(closestCurrentSplitPair, currentBest) < 0)
			{
				currentBest = closestCurrentSplitPair;
			}
			
			return currentBest;
		}
	}
	
	
	/*
	 * This method will create the array of points QX or the points with the smallest x coordinates. Since the points are already in sorted order, we can 
	 * just find the midpoint in the input array and fill a new array with the points before the midpoint and return it.
	 * Parameters:
	 * 	pointsSortedOnX: the input points sorted on their x coordinate.
	 */
	public static PlanePoint[] createQX(PlanePoint[] pointsSortedOnX)
	{
		int mid = pointsSortedOnX.length / 2;
		
		PlanePoint[] qx = new PlanePoint[mid];
		
		
		for(int i = 0; i < mid; i++)
		{
			qx[i] = pointsSortedOnX[i];
		}
		
		return qx;
	}
	
	/*
	 * This method will create the array of points QY or the points with the smallest y coordinates. Since the points are already in sorted order, we can 
	 * just find the midpoint in the input array and fill a new array with the points before the midpoint and return it.
	 * Parameters:
	 * 	pointsSortedOnY: the input points sorted on their y coordinate.
	 */
	public static PlanePoint[] createQY(PlanePoint[] pointsSortedOnY)
	{
		int mid = pointsSortedOnY.length / 2;
		
		PlanePoint[] qy = new PlanePoint[mid];
		
		
		for(int i = 0; i < mid; i++)
		{
			qy[i] = pointsSortedOnY[i];
		}
		
		return qy;
	}
	
	/*
	 * This method will create the array of points RX or the points with the largest x coordinates. Since the points are already in sorted order, we can 
	 * just find the midpoint in the input array and fill a new array with the points after and including the midpoint and return it.
	 * Parameters:
	 * 	pointsSortedOnX: the input points sorted on their x coordinate.
	 */
	public static PlanePoint[] createRX(PlanePoint[] pointsSortedOnX)
	{
		int mid = pointsSortedOnX.length / 2;
		
		PlanePoint[] rx = new PlanePoint[mid];
		
		
		for(int i = 0; i < mid; i++)
		{
			rx[i] = pointsSortedOnX[i+mid];
		}
		
		return rx;
	}
	
	/*
	 * This method will create the array of points RY or the points with the largest y coordinates. Since the points are already in sorted order, we can 
	 * just find the midpoint in the input array and fill a new array with the points after and including the midpoint and return it.
	 * Parameters:
	 * 	pointsSortedOnY: the input points sorted on their y coordinate.
	 */
	public static PlanePoint[] createRY(PlanePoint[] pointsSortedOnY)
	{
		int mid = pointsSortedOnY.length / 2;
		
		PlanePoint[] ry = new PlanePoint[mid];
		
		
		for(int i = 0; i < mid; i++)
		{
			ry[i] = pointsSortedOnY[i+mid];
		}
		
		return ry;
	}
	
	/*
	 * This method will compute the distance between the closest pair split between Q and R. It will do this via the variable xBar, which is the point larger
	 * than all the points in Q. If the closest pair is split between Q and R it will be within +-delta or +-7 spaces of xBar. It will create a new array sy that
	 * is a new point array containing these points. It will then run through this new array and compute the distance between the points in them in a brute force way.
	 * If the distance between the closest points split across Q and R is less than our best (passed in via delta) it will return that pair of points.
	 * Otherwise, it will return null.
	 * Parameters:
	 * 	pointsSortedOnX: input points sorted on x coordinate.
	 * 	pointsSortedOnY: input points sorted on y coordinate.
	 * 	delta: distance between the points currently thought to be closest.
	 */
	public static PointPair closestSplitPair(PlanePoint[] pointsSortedOnX, PlanePoint[] pointsSortedOnY, double delta)
	{
		int xBarIndex = (pointsSortedOnX.length / 2);
		
		PlanePoint[] sy = createSY(pointsSortedOnX, delta, xBarIndex);
		
		double best = delta;
		PointPair bestPair = null;
		
		PlanePoint p;
		PlanePoint q;
		
		for(int i = 0; i < sy.length-1; i++)
		{
			for(int j = 1; j < Math.min(7, sy.length-i); j++)
			{
				p = sy[i];
				
				if(i+j >= sy.length)
				{
					q = sy[sy.length-1];
				}
				else
				{
					q = sy[i+j];
				}
				
				double distanceBetweenPandQ = PointPair.euclideanDistance(p, q);
				
				if(distanceBetweenPandQ < best)
				{
					bestPair = new PointPair(p, q);
					best = distanceBetweenPandQ;
				}
			}
		}
		return bestPair;
	}
	
	/*
	 * This method will create an array to be used in closestSplitPair, this array will contain the points that are within delta of xBar. It will do this
	 * by running through the points sorted by X and checking if the x coordinate is in the interval [x-delta, x+delta]. It will then return this as an array.
	 * Parameters:
	 * 	pointsSortedOnX: the input points sorted on their x coordinate.
	 * 	delta: the distance between the currently closest pair of points.
	 * 	xBarIndex: the array index of xBar.
	 */
	public static PlanePoint[] createSY(PlanePoint[] pointsSortedOnX, double delta, int xBarIndex)
	{
		double intervalLowerBound = pointsSortedOnX[xBarIndex].getX() - delta;
		double intervalUpperBound = pointsSortedOnX[xBarIndex].getX() + delta;
		
		ArrayList<PlanePoint> intermedArrayList = new ArrayList<>();
		
		for(int i = 0; i < pointsSortedOnX.length; i++)
		{
			if(pointsSortedOnX[i].getX() >= intervalLowerBound && pointsSortedOnX[i].getX() <= intervalUpperBound)
			{
				intermedArrayList.add(pointsSortedOnX[i]);
			}
		}
		return intermedArrayList.toArray(new PlanePoint[intermedArrayList.size()]);
	}
	
	/*
	 * Main method.
	 */
	public static void main(String[] args)
	{
		int[] input = 
			{
				//2,3, 12,30, 40,50, 5,1, 12,10, 3,4
				//1,4, 6,8, 4,2, 0,9
					1,4, 6,8, 7,4
			};
		
		//Testing creation:
		PlanePoint[] inputArr = new PlanePoint[input.length / 2];
		
		for(int i = 0; i < inputArr.length; i++)
		{
			inputArr[i] = new PlanePoint(input[2*i], input[2*i+1]);
		}
				
		executeClosestPair(inputArr);
	}
}