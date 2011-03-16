package peersim.cdsim;

import peersim.core.Protocol;
import peersim.core.Node;

/**
 * Defines cycle driven protocols, that is, protocols that have a periodic
 * activity in regular time intervals.
 *
 * 定义Cycle driven 协议，即在一定的时间间隔内有周期性行为的协议。
 */
public interface CDProtocol extends Protocol {

    /**
     * A protocol which is defined by performing an algorithm in more or less
     * regular periodic intervals.
     * This method is called by the simulator engine once in each cycle with
     * the appropriate parameters.
     * 这个方法在每个cycle执行一次。
     * @param node
     *          the node on which this component is run
     * @param protocolID
     *          the id of this protocol in the protocol array
     */
    public void nextCycle(Node node, int protocolID);
}
