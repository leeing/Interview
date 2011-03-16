package peersim.core;

import peersim.config.*;

// XXX a quite primitive scheduler, should be able to be configured
// much more flexibly using a simple syntax for time ranges.
/**
 * A binary function over the time points. That is,
 * for each time point returns a boolean value.
 *
 * The concept of time depends on the simulation model. Current time
 * has to be set by the simulation engine, irrespective of the model,
 * and can be read using {@link CommonState#getTime()}. This scheduler
 * is interpreted over those time points.
 *
 * <p>In this simple implementation the valid times will be
 * <tt>from, from+step, from+2*step, etc,</tt>
 * where the last element is strictly less than <tt>until</tt>.
 * Alternatively, if <tt>at</tt> is defined, then the schedule will be a single
 * time point. If FINAL is
 * defined, it is also added to the set of active time points.
 * It refers to the time after the simulation has finished (see
 * {@link CommonState#getPhase}).
 *
 * <p>
 * 一个很基本的调度器。可以用时间的区间来配置。
 * 对每个时间点返回一个布尔值。
 *
 * 时间的概念依赖于仿真模型。当前时间必须由仿真引擎来设置，与模型无关，并且能用
 * Commonstate.getTime()来读取。这个调度器是用来解析那些时间点的。
 *
 * 在这个简单的实现中，有效的时间是 from from+step, from+2*step 等等。并且最后一个元素
 * 严格地小于until。并且，如果at也被定义了，那么这个调度会是一个单一的时间点。如果FINAL被
 * 定义了，也会添加到活动的时间点中去。它指在仿真结束后的时间，参看：Commonstate.getPhase
 */
public class Scheduler {

// ========================= fields =================================
// ==================================================================
    /**
     * Defaults to 1.
     * @config
     */
    private static final String PAR_STEP = "step";
    /**
     * Defaults to -1. That is, defaults to be ineffective.
     * @config
     */
    private static final String PAR_AT = "at";
    /**
     * Defaults to 0.
     * @config
     */
    private static final String PAR_FROM = "from";
    /**
     * Defaults to <tt>Long.MAX_VALUE</tt>.
     * @config
     */
    private static final String PAR_UNTIL = "until";
    /**
     * Defines if component is active after the simulation has finished.
     * Note that the exact time the simulation finishes is not know in advance
     * because other components can stop the simulation at any time.
     * By default not set.
     * @see CommonState#getPhase
     * @config
     */
    private static final String PAR_FINAL = "FINAL";
    public final long step;
    public final long from;
    public final long until;
    public final boolean fin;
    /** The next scheduled time point.*/
    protected long next = -1;

// ==================== initialization ==============================
// ==================================================================
    /** Reads configuration parameters from the component defined by
     * <code>prefix</code>. {@value #PAR_STEP} defaults to 1.
     */
    public Scheduler(String prefix) {

        this(prefix, true);
    }

// ------------------------------------------------------------------
    /** Reads configuration parameters from the component defined by
     * <code>prefix</code>. If useDefault is false, then at least parameter
     * {@value #PAR_STEP} must be explicitly defined. Otherwise {@value #PAR_STEP}
     * defaults to 1.
     */
    public Scheduler(String prefix, boolean useDefault) {
        if (Configuration.contains(prefix + "." + PAR_AT)) {
            // FROM, UNTIL, and STEP should *not* be defined
            if (Configuration.contains(prefix + "." + PAR_FROM)
                    || Configuration.contains(prefix + "." + PAR_UNTIL)
                    || Configuration.contains(prefix + "." + PAR_STEP)) {
                throw new IllegalParameterException(prefix,
                        "Cannot use \"" + PAR_AT + "\" together with \""
                        + PAR_FROM + "\", \"" + PAR_UNTIL + "\", or \""
                        + PAR_STEP + "\"");
            }

            from = Configuration.getLong(prefix + "." + PAR_AT);
            until = from + 1;
            step = 1;
        } else {
            if (useDefault) {
                step = Configuration.getLong(prefix + "." + PAR_STEP, 1);
            } else {
                step = Configuration.getLong(prefix + "." + PAR_STEP);
            }
            if (step < 1) {
                throw new IllegalParameterException(prefix,
                        "\"" + PAR_STEP + "\" must be >= 1");
            }

            from = Configuration.getLong(prefix + "." + PAR_FROM, 0);
            until = Configuration.getLong(prefix + "." + PAR_UNTIL, Long.MAX_VALUE);
        }

        if (from < until) {
            next = from;
        } else {
            next = -1;
        }

        fin = Configuration.contains(prefix + "." + PAR_FINAL);
    }

// ===================== public methods ==============================
// ===================================================================
    /** true if given time point is covered by this scheduler */
    public boolean active(long time) {

        if (time < from || time >= until) {
            return false;
        }
        return (time - from) % step == 0;
    }

// -------------------------------------------------------------------
    /** true if current time point is covered by this scheduler */
    public boolean active() {

        return active(CommonState.getTime());
    }

//-------------------------------------------------------------------
    /**
     * Returns the next time point. If the returned value is negative, there are
     * no more time points. As a side effect, it also updates the next time point,
     * so repeated calls to this method return the scheduled times.
     */
    public long getNext() {
        long ret = next;
        // check like this to prevent integer overflow of "next"
        if (until - next > step) {
            next += step;
        } else {
            next = -1;
        }
        return ret;
    }
}
