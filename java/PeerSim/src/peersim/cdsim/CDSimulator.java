package peersim.cdsim;

import java.util.*;
import peersim.config.*;
import peersim.core.*;

/**
 * This is the cycle driven simulation engine. It is a fully static
 * singleton class. For a cycle driven simulation the configuration can
 * describe a set of {@link Protocol}s, and their ordering, a set of
 * {@link Control}s and their ordering and a set of initializers and their
 * ordering. See parameters {@value #PAR_INIT}, {@value #PAR_CTRL}. Out
 * of the set of protocols, this engine only executes the ones that
 * implement the {@link CDProtocol} interface.
 * <p>
 * One experiment run by {@link #nextExperiment} works as follows. First
 * the initializers are run in the specified order, then the following is
 * iterated {@value #PAR_CYCLES} times: If {@value #PAR_NOMAIN} is
 * specified, then simply the controls specified in the configuration are
 * run in the specified order. If {@value #PAR_NOMAIN} is not specified,
 * then the controls in the configuration are run in the specified order,
 * followed by the execution of {@link FullNextCycle}.
 * <p>
 * All components (controls and protocols) can have configuration
 * parameters that control their scheduling (see {@link Scheduler}). This
 * way they can skip cycles, start from a specified cycle, etc. As a
 * special case, components can be scheduled to run after the last cycle.
 * That is, each experiment is finished by running the controls that are
 * scheduled after the last cycle.
 * <p>
 * Finally, any control can interrupt an experiment at any time it is
 * executed by returning true in method {@link Control#execute}. However,
 * the controls scheduled to run after the last cycle are still executed
 * completely, irrespective of their return value and even if the
 * experiment was interrupted.
 * @see Configuration
 *
 * <p>
 * 这是cycle driven 的仿真引擎，它是一个静态的 singleton 类。对于一个 cycle driven 仿真，它可以
 * 设定一系列的 protocols，controls 以及 initializers 及其顺序。在所有的协议之中，这个引擎只运行
 * 实现了 CDProtocol 接口的协议。
 * 每个实验的仿真过程如下：
 * <ol>
 * <li>1. 首先initializer以一定的顺序运行，然后以下的阶段将被迭代 simulation.cycles 次 。</li>
 * <li>2. 如果 "simulation.nodefaultcycle"被设置，那么 control 也会以配置文件指定的顺序运行，如果没有
 *    被设置，那么control会在 FullNextCycle 执行之后再以指定的顺序执行。</li>
 * </ol>
 * <p>
 * 所有的组件都可能拥有配置参数，用于控制这们的调度。通过这种方法，它们可以跳跃cycles，从指定的 cycles开始
 * 等等。比如：可以在最后一个cycle之后运行一个组件。也就是说，每个实验都会在最后一个cycle运行完后执行一个
 * control。
 * <p>
 * 最后，任何一个control都可以任意时间interrupt一个实验：通过 在Control中的execute方法中返回true。然而最后
 * 一个 control 会被完全运行，与它们的返回值即是否 Interrupt 无关。
 *
 */
public class CDSimulator {

// ============== fields ===============================================
// =====================================================================
    /**
     * Parameter representing the maximum number of cycles to be performed
     * @config
     */
    public static final String PAR_CYCLES = "simulation.cycles";
    /**
     * This option is only for experts. It switches off the main cycle that
     * calls the cycle driven protocols. When you switch this off, you need to
     * control the execution of the protocols by configuring controls that do
     * the job (e.g., {@link FullNextCycle}, {@link NextCycle}). It's there for
     * people who want maximal flexibility for their hacks.
     *
     * <p>
     * 专家选项。会关闭调用 cycle driven protocols 的 main cycle。在关闭这个选项以后，
     * 你需要通过配置进行工作的job(如 FullNextCycle，NextCycle）来控制协议的执行。
     * 这样的目的是让你拥有最大的控制权来控制仿真的流程
     * 
     * @config
     */
    private static final String PAR_NOMAIN = "simulation.nodefaultcycle";
    /**
     * This is the prefix for initializers. These have to be of type
     * {@link Control}. They are run at the beginning of each experiment, in
     * the order specified by the configuration.
     *
     * 用于初始化，在每次实验开始时调用一次，按配置文件中的出现顺序进行初始化
     * Initializers必须是Control类型
     *
     * @see Configuration
     * @config
     */
    private static final String PAR_INIT = "init";
    /**
     * This is the prefix for controls. These have to be of type
     * {@link Control}. They are run before each cycle, in the order specified
     * by the configuration.
     *
     * 这是controls的前缀，它们必须是Control类型
     * @see Configuration
     * @config
     */
    private static final String PAR_CTRL = "control";
// --------------------------------------------------------------------
    /** The maximum number of cycles to be performed */
    private static int cycles;
    /** holds the modifiers of this simulation */
    private static Control[] controls = null;
    /** Holds the control schedulers of this simulation */
    private static Scheduler[] ctrlSchedules = null;

// =============== initialization ======================================
// =====================================================================
    /** to prevent construction */
    private CDSimulator() {
    }

// =============== private methods =====================================
// =====================================================================
    /**
     * Load and run initializers.
     *
     * 载入并运行 initializers。
     */
    private static void runInitializers() {

        // 得到所有以 init开头的类的实例，在这里主要关注返回的顺序
        Object[] inits = Configuration.getInstanceArray(PAR_INIT);

        // 得到所有以 init 开头的类的别名
        String names[] = Configuration.getNames(PAR_INIT);

        // 运行所有的 init 对象的 execute 方法
        for (int i = 0; i < inits.length; ++i) {
            System.err.println("- Running initializer " + names[i] + ": "
                    + inits[i].getClass());
            ((Control) inits[i]).execute();
        }
    }

// --------------------------------------------------------------------
    /**
     * 载入 Controls
     * @return
     */
    private static String[] loadControls() {

        //  检查是否包含 simulation.nodefaultcycle
        boolean nomaincycle = Configuration.contains(PAR_NOMAIN);

        // 获取所有以 control 开头的组件
        String[] names = Configuration.getNames(PAR_CTRL);

        if (nomaincycle) { // 如果包含 nomaincycle
            controls = new Control[names.length];
            ctrlSchedules = new Scheduler[names.length];
        } else {
            // provide for an extra control that handles the main cycle
            controls = new Control[names.length + 1];
            ctrlSchedules = new Scheduler[names.length + 1];
            // calling with a prefix that cannot exist
            controls[names.length] = new FullNextCycle(" ");
            ctrlSchedules[names.length] = new Scheduler(" ");
        }
        for (int i = 0; i < names.length; ++i) {
            controls[i] = (Control) Configuration.getInstance(names[i]);
            ctrlSchedules[i] = new Scheduler(names[i]);
        }
        System.err.println("CDSimulator: loaded controls " + Arrays.asList(names));
        return names;
    }

// ---------------------------------------------------------------------
    /**
     * This method is used to check whether the current configuration can be
     * used for cycle-driven simulations. It checks for the existence of
     * configuration parameter {@value #PAR_CYCLES}.
     */
    public static final boolean isConfigurationCycleDriven() {
        return Configuration.contains(PAR_CYCLES);
    }

// ---------------------------------------------------------------------
    /**
     * Runs an experiment, resetting everything except the random seed.
     */
    public static final void nextExperiment() {

        // 读取所要实验的次数
        cycles = Configuration.getInt(PAR_CYCLES);
        if (CommonState.getEndTime() < 0) // 初始值为-1，小于0表示还没有初始化。
        {
            CDState.setEndTime(cycles); // 在 cycle driven 中，cycyles 代表时间概念
        }

        // 初始化，CDState 里包含一些全局可见的参数
        CDState.setCycle(0); // 设置当前 cycle 为 0
        CDState.setPhase(CDState.PHASE_UNKNOWN); // 设置时期
        System.err.println("CDSimulator: resetting");
        controls = null;
        ctrlSchedules = null;
        Network.reset(); //读取配置参数，构造原型节点，并通过克隆来构造网络中的其它节点。
        System.err.println("CDSimulator: running initializers");
        runInitializers();//载入并运行 initializers。

        // main cycle
        loadControls(); 

        System.err.println("CDSimulator: starting simulation");
        // 开始仿真
        for (int i = 0; i < cycles; ++i) {
            CDState.setCycle(i); // 设置当前的周期数

            boolean stop = false;
            for (int j = 0; j < controls.length; ++j) {
                // 检查 controls 数组，如果当前它应该被调度执行，那么执行control的execute方法
                if (ctrlSchedules[j].active(i)) {
                    // 如果 stop 为 true 那么 controls[j]不再执行
                    stop = stop || controls[j].execute();
                }
            }
            // stop 为 true 时退出仿真
            if (stop) {
                break;
            }
            System.err.println("CDSimulator: cycle " + i + " done");
        }

        // 当前状态转变为 POST_SIMULATION，用于后续的分析处理
        CDState.setPhase(CDState.POST_SIMULATION);

        // analysis after the simulation
        // 仿真之后的分析
        for (int j = 0; j < controls.length; ++j) {
            if (ctrlSchedules[j].fin) {
                controls[j].execute();
            }
        }
    }
}
