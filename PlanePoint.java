package closestPair;

/*
 * This class is meant to represent a point in a 2d coordinate plane.
 * Author: Andrew Gendreau
 */
public class PlanePoint {
	
	final int xCoordinate;	//x coordinate
	final int yCoordinate;	//y coordinate
	
	/*
	 * Constructor for a point.
	 * Parameters: 
	 * 	x: x coordinate of the point.
	 * 	y: y coordinate of the point.
	 */
	public PlanePoint(int x, int y)
	{
		xCoordinate = x;
		yCoordinate = y;
	}
	
	/*
	 * Getter for the y coordinate.
	 */
	public int getY()
	{
		return yCoordinate;
	}
	
	/*
	 * Getter for the x coordinate.
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/*
	 * This method is meant to be used as an implementation of a comparator to sort the points on their x coordinate.
	 * Parameters:
	 * 	p: a PlanePoint object to be compared.
	 * 	q: a PlanePoint object for p to be compared to.
	 */
	public int xComparator(PlanePoint p, PlanePoint q)
	{
		return p.getX() - q.getX();
	}

	/*
	 * This method is meant to be used as an implementation of a comparator to sort the points on their y coordinate.
	 */
	public int yComparator(PlanePoint p, PlanePoint q)
	{
		return p.getY() - q.getY();
	}
	
	/*
	 * ToString method to print a point to the screen.
	 */
	public String toString()
	{
		return "(" + getX() + "," + getY() + ")";
	}
}