package peersim.core;

/**
 * This class extends GeneralNode by allowing to modify single
 * protocol instances at nodes.
 *
 * @author Alberto Montresor
 * @version $Revision: 1.3 $
 */
public class ModifiableNode extends GeneralNode {

    /**
     * Invokes the super constructor.
     */
    public ModifiableNode(String prefix) {
        super(prefix);
    }

    /**
     * Substitutes the specified protocol at this node.
     *
     * @param pid protocol identifier
     * @param prot the protocol object
     */
    public void setProtocol(int pid, Protocol prot) {
        protocol[pid] = prot;
    }
}
