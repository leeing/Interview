package peersim.core;

/**
 * Class that represents one node with a network address. An {@link Network} is
 * made of a set of nodes. The functionality of this class is thin: it must be
 * able to represent failure states and store a list of protocols. It is the
 * protocols that do the interesting job.
 *
 * 以一个网络地址代表的一个节点类。Network是由一系列的节点组成的。这个类的功能很单一：它必须
 * 能表示错误状态并存储一个protocols的列表，而protocol做的才是真正的任务。
 */
public interface Node extends Fallible, Cloneable {

    /**
     * Prefix of the parameters that defines protocols.
     * @config
     */
    public static final String PAR_PROT = "protocol";

    /**
     * Returns the <code>i</code>-th protocol in this node. If <code>i</code>
     * is not a valid protocol id
     * (negative or larger than or equal to the number of protocols), then it throws
     * IndexOutOfBoundsException.
     */
    public Protocol getProtocol(int i);

    /**
     * Returns the number of protocols included in this node.
     */
    public int protocolSize();

    /**
     * Sets the index of this node in the internal representation of the node list.
     * Applications should not use this method. It is defined as public simply
     * because it is not possible to define it otherwise.
     * Using this method will result in
     * undefined behavior. It is provided for the core system.
     */
    public void setIndex(int index);

    /**
     * Returns the index of this node. It is such that
     * <code>Network.get(n.getIndex())</code> returns n. This index can
     * change during a simulation, it is not a fixed id. If you need that, use
     * {@link #getID}.
     * @see Network#get
     */
    public int getIndex();

    /**
     * Returns the unique ID of the node. It is guaranteed that the ID is unique
     * during the entire simulation, that is, there will be no different Node
     * objects with the same ID in the system during one invocation of the JVM.
     * Preferably nodes
     * should implement <code>hashCode()</code> based on this ID.
     */
    public long getID();

    /**
     * Clones the node. It is defined as part of the interface
     * to change the access right to public and to get rid of the
     * <code>throws</code> clause.
     */
    public Object clone();
}
