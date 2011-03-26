package peersim.edsim;

import peersim.core.*;

/**
 * The interface to be implemented by protocols run under the event-driven
 * model. A single method is provided, to deliver events to the protocol.
 *
 * @author Alberto Montresor
 * @version $Revision: 1.5 $
 */
public interface EDProtocol extends Protocol {

    /**
     * This method is invoked by the scheduler to deliver events to the
     * protocol. Apart from the event object, information about the node
     * and the protocol identifier are also provided. Additional information
     * can be accessed through the {@link CommonState} class.
     *
     * @param node the local node
     * @param pid the identifier of this protocol
     * @param event the delivered event
     */
    public void processEvent(Node node, int pid, Object event);
}
