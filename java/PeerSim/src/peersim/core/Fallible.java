package peersim.core;

/**
 * Instances of classes implementing this interface
 * maintain a fail state, i.e. information about the availability
 * of the object.
 * 实现了这个接口的类会维护一个错误状态，比如：本对象的可用性信息。
 */
public interface Fallible {

    /**
     * Fail state which indicates that the object is operating normally.
     */
    public int OK = 0;
    /**
     * Fail state indicating that the object is dead and recovery is
     * not possible. When this state is set, it is a good idea to make sure
     * that the state of the object becomes such that any attempt to
     * operate on it causes a visible error of some kind.
     */
    public int DEAD = 1;
    /**
     * Fail state indicating that the object is not dead, but is temporarily
     * not accessible.
     */
    public int DOWN = 2;

    /**
     * Returns the state of this object. Must be one of the constants
     * defined in interface {@link Fallible}.
     */
    public int getFailState();

    /**
     * Sets the fails state of this object. Parameter must be one of the
     * constants defined in interface {@link Fallible}.
     */
    public void setFailState(int failState);

    /**
     * Convenience method to check if the node is up and running
     * @return must return true if and only if
     * <code>getFailState()==OK</code>
     */
    public boolean isUp();
}
