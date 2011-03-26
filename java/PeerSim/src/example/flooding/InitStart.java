package example.flooding;

import peersim.cdsim.CDProtocol;
import peersim.config.Configuration;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.Linkable;
import peersim.core.Network;
import peersim.core.Node;
import peersim.core.Scheduler;
import peersim.dynamics.NodeInitializer;
import peersim.edsim.NextCycleEvent;
import peersim.transport.Transport;

/**
 * Schedules the first execution of the cycle based protocol instances in
 * the event driven engine.
 * It implements {@link Control} but it will most often be invoked only
 * once for each protocol as an
 * initializer, since the scheduled events schedule themselves for the
 * consecutive executions (see {@link NextCycleEvent}).
 *
 * <p>All {@link CDProtocol} specifications in the configuration need to
 * contain a {@link Scheduler} specification at least for the step size
 * (see config parameter {@value peersim.core.Scheduler#PAR_STEP} of
 * {@link Scheduler}). This value is used as the cycle length for the
 * corresponding protocol.
 *@see NextCycleEvent
 */
public class InitStart implements Control {

// ============================== fields ==============================
// ====================================================================
    /**
     * Parameter that is used to define the class that is used to schedule
     * the next cycle. Its type is (or extends) {@link NextCycleEvent}.
     * Defaults to {@link NextCycleEvent}.
     * @config
     */
    private static final String PAR_NEXTC = "nextcycle";
    /**
     * The protocols that this scheduler schedules for the first execution.
     * It might contain several protocol names, separated by whitespace. All
     * protocols will be scheduled based on the common parameter set for this
     * scheduler and the parameters of the protocol (cycle length).
     * Protocols are scheduled independently of each other.
     * @config
     */
    private static final String PAR_PROTOCOL = "protocol";
    /**
     * If set, it means that the initial execution of the given protocol is
     * scheduled for a different random time for all nodes. The random time
     * is a sample between the current time (inclusive) and the cycle length
     * (exclusive), the latter being specified by the step parameter
     * (see {@link Scheduler}) of the assigned protocol.
     * @see #execute
     * @config
     */
    private static final String PAR_RNDSTART = "randstart";
    /**
     * Contains the scheduler objects for all {@link CDProtocol}s defined in the
     * configuration. The length of the array is the number of protocols defined,
     * but those entries that belong to protocols that are not {@link CDProtocol}s
     * are null.
     */
    private final int[] pid;
    private int copysize = 10;
    private int startnode = 1;

// =============================== initialization ======================
// =====================================================================
    /**
     * Loads protocol schedulers for all protocols.
     */
// --------------------------------------------------------------------
    /**
     * Initialization based on configuration parameters.
     */
    public InitStart(String n) {

        String[] prots = Configuration.getString(n + "." + PAR_PROTOCOL).split("\\s");
        pid = new int[prots.length];
        copysize = Configuration.getInt(n + "." + "copysize", 10);
        startnode = Configuration.getInt(n + "." + "startnode", 1);


    }

// ========================== methods ==================================
// =====================================================================
    /**
     * Schedules the protocol at all nodes
     * for the first execution adding it to the priority queue of the event driven
     * simulation. The time of the first execution is determined by
     * {@link #firstDelay}. The implementation calls {@link #initialize}
     * for all nodes.
     * @see #initialize
     */
    public boolean execute() {

        /*
        for(int i=0;i<Network.size();i++)
        {
        Node node=Network.get(i);
        Linkable linkable =
        (Linkable) node.getProtocol( FastConfig.getLinkable(pid[0]) );
        System.out.println("Node "+i+" degree is"+linkable.degree());
        }
         */
        for (int i = 0; i < startnode; i++)//����startnode���ڵ㿪ʼ����
        {
            initialize(Network.get(CommonState.r.nextInt(Network.size() - 1)));
        }



        return false;
    }

// --------------------------------------------------------------------
    /**
     * Schedules the protocol at given node
     * for the first execution adding it to the priority queue of the event driven
     * simulation. The time of the first execution is determined by a reference
     * point in time and {@link #firstDelay}, which defines the delay from the
     * reference point.
     * The reference point is the maximum of the current time, and the
     * value of parameter {@value peersim.core.Scheduler#PAR_FROM} of the
     * protocol being
     * scheduled. If the calculated time of the first execution
     * is not valid according to the schedule of the
     * protocol then no execution is scheduled for that protocol.
     * <p>
     * A final note: for performance reasons, the recommended practice is
     * not to use parameter {@value peersim.core.Scheduler#PAR_FROM}
     * in protocols, but
     * to schedule {@link InitStart} itself for the desired time, whenever
     * possible (e.g., it is not possible if {@link InitStart} is used as a
     * {@link NodeInitializer}).
     */
    public void initialize(Node node) {
        /*XXX
         * If "from" is not the current time and this is used as a control (not node
         * initializer) then we dump _lots_ of events in the queue
         * that are just stored there until "from" comes. This reduces performance,
         * and should be fixed. When fixed, the final comment can be removed from the
         * docs.
         */

        final long time = CommonState.getTime();
        for (int i = 0; i < pid.length; ++i) {
            Linkable linkable =
                    (Linkable) node.getProtocol(FastConfig.getLinkable(pid[i]));
            if (linkable.degree() > 0) {
                double searchval = (double) CommonState.r.nextInt(copysize);
                for (int l = 0; l < linkable.degree(); l++) {
                    Node peern = linkable.getNeighbor(l);

                    // XXX quick and dirty handling of failures
                    // (message would be lost anyway, we save time)
                    if (!peern.isUp()) {
                        return;
                    }

                    ((Transport) node.getProtocol(FastConfig.getTransport(pid[i]))).send(
                            node,
                            peern,
                            new FloodMessage(searchval, 0, node, node, String.valueOf(node.getID())),
                            pid[i]);

                }
                System.out.println("Start " + node.getID() + " search value is:" + searchval);
            }

        }
    }
// --------------------------------------------------------------------
    /**
     * Returns the time (through giving the delay from the current time)
     * when this even is first executed.
     * If {@value #PAR_RNDSTART} is not set, it returns zero, otherwise
     * a random value between 0, inclusive, and cyclelength, exclusive.
     * @param cyclelength The cycle length of the cycle based protocol
     * for which this method is called
     */
}
