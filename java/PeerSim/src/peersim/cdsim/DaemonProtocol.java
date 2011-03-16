package peersim.cdsim;

import java.util.Arrays;
import peersim.config.Configuration;
import peersim.core.Node;
import peersim.core.Control;

/**
 * A protocol that is not really a protocol, but a trick to carry out all
 * kinds of tasks during the simulation. Many users will probably not need it,
 * but it is a nice way to e.g. run controls at any time, not only between cycles.
 */
public class DaemonProtocol implements CDProtocol {

// ========================= fields =================================
// ==================================================================
    /**
     * This is the prefix for network dynamism managers.
     * @config
     */
    private static final String PAR_CTRL = "control";
    /**
     * The controls will be run according to this frequency.
     * It is interpreted within a cycle, in terms of cycle time
     * ({@link CDState#getCycleT}). The first cycletime is 0.
     * Defaults to 1.
     * @config
     */
    private static final String PAR_STEP = "cstep";
// --------------------------------------------------------------------
    private static Control[] controls = null;
    private static int step;

// ========================= initialization =========================
// ==================================================================
    public DaemonProtocol(String s) {
        step = Configuration.getInt(s + "." + PAR_STEP, 1);

        String[] names = Configuration.getNames(s + "." + PAR_CTRL);
        controls = new Control[names.length];
        for (int i = 0; i < names.length; ++i) {
            controls[i] = (Control) Configuration.getInstance(names[i]);
        }
        System.err.println(s + ": loaded controls " + Arrays.asList(names));
    }

// ------------------------------------------------------------------
    public Object clone() {

        DaemonProtocol ip = null;
        try {
            ip = (DaemonProtocol) super.clone();
        } catch (CloneNotSupportedException e) {
        } // never happens
        return ip;
    }

// ========================= methods =================================
// ===================================================================
    /**
     * Runs the configured controls if {@link CDState#getCycleT} %
     * {@value #PAR_STEP}=0.
     */
    public void nextCycle(Node node, int protocolID) {

        if (CDState.getCycleT() % step != 0) {
            return;
        }
        for (int j = 0; j < controls.length; ++j) {
            controls[j].execute();
        }
    }
}
