package example.hot;

import peersim.core.Protocol;

/**
 * <p>
 * This class does nothing. It is simply a container inside each node to collect
 * peer coordinates.
 * </p>
 * <p>
 * The actual "who knows whom" relation (the topology) container is decoupled
 * from the HOT package. It is maintained by any {@link peersim.core.Linkable} 
 * implementing
 * protocol declared in the config file.
 * </p>
 * 
 * @author Gian Paolo Jesi
 */
public class InetCoordinates implements Protocol {

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    /** 2d coordinates components. */
    private double x, y;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    /**
     * Standard constructor that reads the configuration parameters. Invoked by
     * the simulation engine. By default, all the coordinates components are set
     * to -1 value. The {@link InetInitializer} class provides a coordinates
     * initialization.
     * 
     * @param prefix
     *            the configuration prefix for this class.
     */
    public InetCoordinates(String prefix) {
        /* Un-initialized coordinates defaults to -1. */
        x = y = -1;
    }

    public Object clone() {
        InetCoordinates inp = null;
        try {
            inp = (InetCoordinates) super.clone();
        } catch (CloneNotSupportedException e) {
        } // never happens
        return inp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
