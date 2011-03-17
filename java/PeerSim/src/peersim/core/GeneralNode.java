package peersim.core;

import peersim.config.*;

/**
 * This is the default {@link Node} class that is used to compose the
 * {@link Network}.
 *
 * 这是默认的节点实现
 */
public class GeneralNode implements Node {

    /** used to generate unique IDs */
    private static long counterID = -1;
    /**
     * The protocols on this node.
     */
    protected Protocol[] protocol = null;
    /**
     * The current index of this node in the node
     * list of the {@link Network}. It can change any time.
     * This is necessary to allow
     * the implementation of efficient graph algorithms.
     */
    private int index;
    /**
     * The fail state of the node.
     */
    protected int failstate = Fallible.OK;
    /**
     * The ID of the node. It should be final, however it can't be final because
     * clone must be able to set it.
     */
    private long ID;

    
    /** Used to construct the prototype node. This class currently does not
     * have specific configuration parameters and so the parameter
     * <code>prefix</code> is not used. It reads the protocol components
     * (components that have type {@value peersim.core.Node#PAR_PROT}) from
     * the configuration.
     */
    public GeneralNode(String prefix) {

        String[] names = Configuration.getNames(PAR_PROT);
        CommonState.setNode(this);
        ID = nextID();
        protocol = new Protocol[names.length];

        // 将所有的协议按顺序存入节点中
        for (int i = 0; i < names.length; i++) {
            CommonState.setPid(i);
            Protocol p = (Protocol) Configuration.getInstance(names[i]);
            protocol[i] = p;
        }
    }

// -----------------------------------------------------------------
    @Override
    public Object clone() {

        GeneralNode result = null;
        try {
            result = (GeneralNode) super.clone();
        } catch (CloneNotSupportedException e) {
        } // never happens
        result.protocol = new Protocol[protocol.length];
        CommonState.setNode(result);
        result.ID = nextID();
        for (int i = 0; i < protocol.length; ++i) {
            CommonState.setPid(i);
            result.protocol[i] = (Protocol) protocol[i].clone();
        }
        return result;
    }

// -----------------------------------------------------------------
    /** returns the next unique ID */
    private long nextID() {

        return counterID++;
    }

    /**
     * 返回节点的状态
     * @param failState
     */
    public void setFailState(int failState) {

        // after a node is dead, all operations on it are errors by definition
        // 在一个节点 dead 之后，在它上面的任何操作都会抛出异常
        if (failstate == DEAD && failState != DEAD) {
            throw new IllegalStateException(
                    "Cannot change fail state: node is already DEAD");
        }
        switch (failState) {
            case OK:
                failstate = OK;
                break;
            case DEAD:
                //protocol = null;
                index = -1;
                failstate = DEAD;
                for (int i = 0; i < protocol.length; ++i) {
                    if (protocol[i] instanceof Cleanable) {
                        ((Cleanable) protocol[i]).onKill();
                    }
                }
                break;
            case DOWN:
                failstate = DOWN;
                break;
            default:
                throw new IllegalArgumentException(
                        "failState=" + failState);
        }
    }

// -----------------------------------------------------------------
    public int getFailState() {
        return failstate;
    }

// ------------------------------------------------------------------
    public boolean isUp() {
        return failstate == OK;
    }

// -----------------------------------------------------------------
    public Protocol getProtocol(int i) {
        return protocol[i];
    }

//------------------------------------------------------------------
    public int protocolSize() {
        return protocol.length;
    }

//------------------------------------------------------------------
    public int getIndex() {
        return index;
    }

//------------------------------------------------------------------
    public void setIndex(int index) {
        this.index = index;
    }

//------------------------------------------------------------------
    /**
     * Returns the ID of this node. The IDs are generated using a counter
     * (i.e. they are not random).
     */
    public long getID() {
        return ID;
    }

//------------------------------------------------------------------
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + ID + " index: " + index + "\n");
        for (int i = 0; i < protocol.length; ++i) {
            buffer.append("protocol[" + i + "]=" + protocol[i] + "\n");
        }
        return buffer.toString();
    }

//------------------------------------------------------------------
    /** Implemented as <code>(int)getID()</code>. */
    public int hashCode() {
        return (int) getID();
    }
}
