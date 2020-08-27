package closestPair;

/*
 * This class is meant to represent a pair of points in an x,y coordinate plane.
 * Author: Andrew Gendreau
 */

public class PointPair {
	
	PlanePoint firstPoint;		//first point in the pointPair.
	PlanePoint secondPoint;		//second point in the pointPair.
	
	/*
	 * Constructor for a pointPair. 
	 * Parameters:
	 * 	first: the first point in the pair.
	 * 	second: the second point in the pair.
	 */
	PointPair(PlanePoint first, PlanePoint second)
	{
		firstPoint = first;
		secondPoint = second;
	}
	
	/*
	 * This method will compute and return the Euclidean distance given a pair of points. 
	 * If either of the given points are null, it will return the max value for double as a fail value.
	 * Parameters:
	 * 	pointP: the first point to be used in the calculation.
	 * 	PointQ: the second point to be used in the calculation.
	 */
	public static double euclideanDistance(PlanePoint pointP, PlanePoint pointQ)
	{
		if(pointP == null || pointQ == null)
		{
			return Double.MAX_VALUE;
		}
		else
		{
			return Math.sqrt(Math.pow(pointP.getX() - pointQ.getX(), 2) + Math.pow(pointP.getY() - pointQ.getY(), 2));
		}
	}
	
	/*
	 * This method will compute and return the Euclidean distance given a pointPair object. If the pointPair is null, then this will return the max double value.
	 * Parameters:
	 * 	pair: The pointPair object used in the computation.
	 */
	public static double euclideanDistance(PointPair pair)
	{
		if(pair == null)
		{
			return Double.MAX_VALUE;
		}
		else
		{
			return Math.sqrt(Math.pow(pair.firstPoint.getX() - pair.secondPoint.getX(), 2) + Math.pow(pair.firstPoint.getY() - pair.secondPoint.getY(), 2));
		}
	}
	
	/*
	 * This method is meant to compare the Euclidean distance between two pairs of points. If either pair are null, it will return 0.
	 * Parameters:
	 * 	a: the first pair of points to be compared.
	 * 	b: the second pair of points to be compared.
	 */
	public static int compareDistance(PointPair a, PointPair b)
	{
		if(a == null || b == null)
		{
			return 0;
		}
		else
		{
		return (int) (euclideanDistance(a)-euclideanDistance(b));
		}
	}
	
	/*
	 * toString method for a PointPair.
	 */
	public String toString()
	{
		return "(" + firstPoint.getX() + "," + firstPoint.getY() + ") (" + secondPoint.getX() + "," + secondPoint.getY() + ")";
	}
}
