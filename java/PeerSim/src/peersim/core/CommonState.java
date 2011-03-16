package peersim.core;

import peersim.config.*;
import peersim.util.*;

/**
 * This is the common state of the simulation all objects see.
 * Static singleton. One of its purposes is
 * simplification of parameter structures and increasing efficiency by putting
 * state information here instead of passing parameters.
 *<p>
 * <em>The set methods should not be used by applications</em>,
 * they are for system
 * components. Use them only if you know exactly what you are doing, e.g.
 * if you are so advanced that you can write your own simulation engine.
 * Ideally, they should not be visible, but due to the lack of more
 * flexibility in java access rights, we are forced to make them public.
 *
 * 这是仿真时所有对象都可见的状态。
 * 静态的Singleton。它的其中一个目的是为了简化参数的结构，提高效率。
 *
 * 这里的set方法不应该会应用程序使用，它们是系统内部组件使用的。高级用户才建议去修改这些参数，
 * 让它们成为public是为了编程的方便。
 *
 */
public class CommonState {

//======================= constants ===============================
//=================================================================
    /**
     * Constant that can be used as a value of simulation phase.
     * It means that the simulation has finished.
     *
     * 在仿真时期使用的常数，用于表示仿真已经结束
     *
     * @see #getPhase
     */
    public static final int POST_SIMULATION = 1;
    /**
     * Constant that can be used as a value of simulation phase.
     * It means that the simulation phase information has not been set (unknown).
     *
     * 表示仿真阶段的信息还没有被设置
     *
     * @see #getPhase
     */
    public static final int PHASE_UNKNOWN = 0;
// ======================= fields ==================================
// =================================================================
    /**
     * Current time. Note that this value is simulator independent, all simulation
     * models have a notion related to time. For example, in the cycle based model,
     * the cycle id gives time, while in even driven simulations there is a more
     * realistic notion of time.
     *
     * 当前时间。注意这个值是独立于仿真器的。所有传真模型都有一个关于时间的概念。例如，在cycle
     * 仿真模型中，cycle id 给定了时间，而在event仿真模型中，是更真实的时间的表示。
     */
    private static long time = 0;
    /**
     * The maximal value {@link #time} can ever take.
     */
    private static long endtime = -1;
    /**
     * Number of used bits in the long representation of time, calculated
     * based on the endtime.
     *
     * 对时间的long表示使用的bit数，基于endtime来计算
     */
    private static int toshift = -1;
    /**
     * Information about where exactly the simulation is.
     *
     */
    private static int phase = PHASE_UNKNOWN;
    /**
     * The current pid.
     */
    private static int pid;
    /**
     * The current node.
     */
    private static Node node;
    /**
     * This source of randomness should be used by all components.
     * This field is public because it doesn't matter if it changes
     * during an experiment (although it shouldn't) until no other sources of
     * randomness are used within the system. Besides, we can save the cost
     * of calling a wrapper method, which is important because this is needed
     * very often.
     */
    public static ExtendedRandom r = null;
// ======================== initialization =========================
// =================================================================
    /**
     * Configuration parameter used to define which random generator
     * class should be used. If not specified, the default implementation
     * {@link ExtendedRandom} is used. User-specified random generators
     * must extend class {@link ExtendedRandom}.
     * @config
     */
    public static final String PAR_RANDOM = "random";
    /**
     * Configuration parameter used to initialize the random seed.
     * If it is not specified the current time is used.
     *
     * 初始化种子的参数。
     * 如果没能指定，那么使用当前的时间
     *
     * @config
     */
    public static final String PAR_SEED = "random.seed";

    /**
     * Initializes the field {@link r} according to the configuration.
     * Assumes that the configuration is already
     * loaded.
     *
     * 根据配置文件初始化域r，假设配置文件已经载入到内存中。
     */
    static {

        long seed =
                Configuration.getLong(PAR_SEED, System.currentTimeMillis());
        initializeRandom(seed);
    }

    /** Does nothing. To avoid construction but allow extension. */
    protected CommonState() {
    }

// ======================= methods =================================
// =================================================================
    /**
     * Returns current time. In event-driven simulations, returns the current
     * time (a long-value).
     * In cycle-driven simulations, returns the current cycle (a long that
     * can safely be cast into an integer).
     */
    public static long getTime() {
        return time;
    }

//-----------------------------------------------------------------
    /**
     * Returns current time in integer format. The purpose is to enhance the
     * performance of protocols (ints are smaller and faster) when absolute
     * precision is not required. It assumes that endtime has been set via
     * {@link #setEndTime} by the simulation engine. It uses the endtime for
     * the optimal mapping to get the maximal precision.
     * In particular, in the cycle
     * based model, time is the same as cycle which can be safely cast into
     * integer, so no precision is lost.
     *
     * 用整数的形式返回当前的时间。目的是为了提高 protocol 的性能（在绝对精度不需要的情况下）.
     * 它假设endtime已经被仿真引擎的setEndTime方法设置。
     */
    public static int getIntTime() {
        return (int) (time >> toshift);
    }

//-----------------------------------------------------------------
    /**
     * Sets the current time.
     */
    public static void setTime(long t) {
        time = t;
    }

//-----------------------------------------------------------------
    /**
     * Returns endtime.
     * It is the maximal value {@link #getTime} ever returns. If it's negative, it
     * means the endtime is not known.
     */
    public static long getEndTime() {
        return endtime;
    }

//-----------------------------------------------------------------
    /**
     * Sets the endtime.
     */
    public static void setEndTime(long t) {
        if (endtime >= 0) {
            throw new RuntimeException("You can set endtime only once");
        }
        if (t < 0) {
            throw new RuntimeException("No negative values are allowed");
        }

        endtime = t;
        toshift = 32 - Long.numberOfLeadingZeros(t);
        if (toshift < 0) {
            toshift = 0;
        }
    }

//-----------------------------------------------------------------
    /**
     * Returns the simulation phase. Currently the following phases are
     * understood.
     * <ul>
     * <li>{@link #PHASE_UNKNOWN} phase is unknown</li>
     * <li>{@link #POST_SIMULATION} the simulation is completed</li>
     * </ul>
     */
    public static int getPhase() {
        return phase;
    }

// -----------------------------------------------------------------
    public static void setPhase(int p) {
        phase = p;
    }

// -----------------------------------------------------------------
    /**
     * Returns the current protocol identifier. In other words, control is
     * held by the indicated protocol on node {@link #getNode}.
     */
    public static int getPid() {
        return pid;
    }

//-----------------------------------------------------------------
    /** Sets the current protocol identifier.*/
    public static void setPid(int p) {
        pid = p;
    }

//-----------------------------------------------------------------
    /**
     * Returns the current node. When a protocol is executing, it is the node
     * hosting the protocol.
     */
    public static Node getNode() {
        return node;
    }

//-----------------------------------------------------------------
    /** Sets the current node */
    public static void setNode(Node n) {
        node = n;
    }

//-----------------------------------------------------------------
    public static void initializeRandom(long seed) {
        if (r == null) {
            r = (ExtendedRandom) Configuration.getInstance(PAR_RANDOM, new ExtendedRandom(seed));
        }
        r.setSeed(seed);
    }
//-----------------------------------------------------------------

    /*
    public static void main(String pars[]) {

    setEndTime(Long.parseLong(pars[0]));
    setTime(Long.parseLong(pars[1]));
    System.err.println(getTime()+" "+getIntTime());
    }
     */
}
