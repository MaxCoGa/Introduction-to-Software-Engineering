package test;//Maxime Côté-Gagné(8851539)
//This file contains material supporting section 2.9 of the textbook:
//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 

/**
* This class contains instances of coordinates in either polar or
* cartesian format.  It also provides the utilities to convert
* them into the other type. It is not an optimal design, it is used
* only to illustrate some design issues.
*
* @author Fran&ccedil;ois B&eacute;langer
* @author Dr Timothy C. Lethbridge
* @version July 2000
*/
public abstract class PointCP5
{

	  /**
	   * Contains C(artesian) or P(olar) to identify the type of
	   * coordinates that are being dealt with.
	   */
    public char typeCoord;
    
    /**
     * Contains the current value of X or RHO depending on the type
     * of coordinates.
     */
    public double xOrRho;
    /**
     * Contains the current value of Y or THETA value depending on the
     * type of coordinates.
     */
    public double yOrTheta;
    

    /**
     * Constructs a coordinate object, with a type identifier.
     */
    public PointCP5 (char type, double xOrRho, double yOrTheta)
    {
        if (type != 'C' && type != 'P')
            throw new IllegalArgumentException ();
        this.xOrRho = xOrRho;
        this.yOrTheta = yOrTheta;
        this.typeCoord = type;
    }
    //valeur abstraite
    public abstract double getX ();
    public abstract double getY ();
    public abstract double getRho ();
    public abstract double getTheta ();
    public abstract PointCP5 convertStorageToCartesian ();
    public abstract PointCP5 convertStorageToPolar ();
    /**
     * Calculates the distance in between two points using the Pythagorean
     * theorem  (C ^ 2 = A ^ 2 + B ^ 2). Not needed until E2.30.
     *
     * @param pointA The first point.
     * @param pointB The second point.
     * @return The distance between the two points.
     */   
    public double getDistance (PointCP5 pointB)
    {
        double deltaX = getX () - pointB.getX ();
        double deltaY = getY () - pointB.getY ();
        return Math.sqrt ((Math.pow (deltaX, 2) + Math.pow (deltaY, 2)));
    }
    /**
     * Rotates the specified point by the specified number of degrees.
     * Not required until E2.30
     *
     * @param point The point to rotate
     * @param rotation The number of degrees to rotate the point.
     * @return The rotated image of the original point.
     */
    public PointCPCartesian rotatePoint (double rotation)
    {
        double radRotation = Math.toRadians (rotation);
        double X = getX ();
        double Y = getY ();
        return new PointCPCartesian ('C',
                (Math.cos (radRotation) * X) - (Math.sin (radRotation) * Y),
                (Math.sin (radRotation) * X) + (Math.cos (radRotation) * Y));
    }

    /**
     * Returns information about the coordinates.
     *
     * @return A String containing information about the coordinates.
     */
    public String toString ()
    {
        return "Stored as " + (typeCoord == 'C'
                ? "Cartesian  (" + getX () + "," + getY () + ")"
                :
        "Polar [" + getRho () + "," + getTheta () + "]") + "\n";
    }
}