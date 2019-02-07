package Design5;//Maxime Côté-Gagné(8851539)
// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

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
public class PointCPCartesian extends PointCP5
{
    /**
     * Constructs a coordinate object, with a type identifier.
     */
    public PointCPCartesian (char type, double xOrRho, double yOrTheta)
    {
        super (type, xOrRho, yOrTheta);
    }

    //Instance methods **************************************************

    public double getX ()
    {
        return xOrRho;
    }


    public double getY ()
    {
        return yOrTheta;
    }


    public double getRho ()
    {
        return (Math.sqrt (Math.pow (xOrRho, 2) + Math.pow (yOrTheta, 2)));
    }


    public double getTheta ()
    {
        return Math.toDegrees (Math.atan2 (yOrTheta, xOrRho));
    }

    /**
     * Converts Polar coordinates to Cartesian coordinates.
     */
    public PointCPCartesian convertStorageToCartesian ()
    {
        return new PointCPCartesian ('C', getX (), getY ());
    }

    /**
     * Converts cartesian coordinates to polar coordinates.
     */
    public PointCPPolar convertStorageToPolar ()
    {
        return new PointCPPolar ('P', getRho (), getTheta ());
    }
}