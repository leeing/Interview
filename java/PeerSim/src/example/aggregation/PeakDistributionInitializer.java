package example.aggregation;

import peersim.config.Configuration;
import peersim.core.Control;
import peersim.core.Network;
import peersim.vector.SingleValue;

/**
 * Initialize an aggregation protocol using a peak distribution; only one peak
 * is allowed. Note that any protocol implementing
 * {@link peersim.vector.SingleValue} can be initialized by this component.
 * 
 * @author Alberto Montresor
 * @version $Revision: 1.12 $
 */
public class PeakDistributionInitializer implements Control {

    /**
     * The load at the peak node.
     * 
     * @config
     */
    private static final String PAR_VALUE = "value";
    /**
     * The protocol to operate on.
     * 
     * @config
     */
    private static final String PAR_PROT = "protocol";
    /** Value at the peak node.
     * Obtained from config property {@link #PAR_VALUE}. */
    private final double value;
    /** Protocol identifier; obtained from config property {@link #PAR_PROT}. */
    private final int pid;

    /**
     * Creates a new instance and read parameters from the config file.
     */
    public PeakDistributionInitializer(String prefix) {
        value = Configuration.getDouble(prefix + "." + PAR_VALUE);
        pid = Configuration.getPid(prefix + "." + PAR_PROT);
    }

    /**
     * Initialize an aggregation protocol using a peak distribution.
     * That is, one node will get the peek value, the others zero.
     * @return always false
     */
    public boolean execute() {
        for (int i = 0; i < Network.size(); i++) {
            SingleValue prot = (SingleValue) Network.get(i).getProtocol(pid);
            prot.setValue(0);
        }
        SingleValue prot = (SingleValue) Network.get(0).getProtocol(pid);
        prot.setValue(value);

        return false;
    }
}
