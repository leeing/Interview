package peersim;

import java.io.*;

import peersim.cdsim.*;
import peersim.config.*;
import peersim.core.*;
import peersim.edsim.*;

/**
 * peersim.Simulator.java
 * 
 * 程序的入口，主要作用就是读取配置文件，并根据仿真类型来调用仿真引擎。
 */
public class Simulator {

    // 某些静态变量，一看就懂。

    public static final int CDSIM = 0;

    public static final int EDSIM = 1;

    public static final int UNKNOWN = -1;

    protected static final String[] simName = {
        "peersim.cdsim.CDSimulator",
        "peersim.edsim.EDSimulator",};

    // 配置文件中的 simulation.experiments 参数，如果没有标明，则默认为进行1次实验
    public static final String PAR_EXPS = "simulation.experiments";

    // 设置程序的输出，默认为 stdout
    public static final String PAR_REDIRECT = "simulation.stdout";

    // 仿真类型，有3种，即 CDSIM,EDSIM 和 UNKNOWN
    private static int simID = UNKNOWN;

    // 根据配置文件返回当前的仿真类型
    public static int getSimID() {

        if (simID == UNKNOWN) {
            // 怎么判断类型？很简单，看配置文件里有没有 simulation.cycles 的定义
            if (CDSimulator.isConfigurationCycleDriven()) {
                simID = CDSIM;
            } else if (EDSimulator.isConfigurationEventDriven()) {
                // 类似的，如果配置文件里有 simulation.endtime 的定义，即为 Event-driven
                simID = EDSIM;
            }
        }
        return simID;
    }

    /**
     * 程序的入口，，运行时要将配置文件的位置作为参数提供给 Simulator
     */
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        System.err.println("Simulator: loading configuration");

        // 读入配置文件
        Configuration.setConfig(new ParsedProperties(args));

        // 设置输出到某一个 PrintStream，默认为 System.out
        PrintStream newout =(PrintStream) Configuration.getInstance(PAR_REDIRECT, System.out);

        if (newout != System.out) {
            System.setOut(newout);
        }

        // 设置实验的次数，默认为1次，可以通过设置 simulation.experiments 来改变默认值
        // 在这里说一下，PeerSim 的配置文件学习是第一步，想要用好的好，首先就要明白 PeerSim
        // 是如何解析配置文件的，具体的解析可以看 peersim.config 包里边的类，因为在这里
        // 并不是核心的类，因此不多作解释
        int exps = Configuration.getInt(PAR_EXPS, 1);

        // 读取实验的类型，如果不能确定仿真类型，就退出。
        final int SIMID = getSimID();
        if (SIMID == UNKNOWN) {
            System.err.println(
                    "Simulator: unable to determine simulation engine type");
            return;
        }

        try {

            for (int k = 0; k < exps; ++k) { // 一次循环进行一次实验
                if (k > 0) {
                    // 仿真过程中的种子，可以通过在配置文件中的random.seed 来设置。
                    // 如果不设置的话，就将当前的时间作为种子
                    long seed = CommonState.r.nextLong();
                    CommonState.initializeRandom(seed); 
                }
                System.err.print("Simulator: starting experiment " + k);
                System.err.println(" invoking " + simName[SIMID]);
                System.err.println("Random seed: "+ CommonState.r.getLastSeed());
                System.out.println("\n\n");

                // 作者说可以用反射机制的，不过这样写容易理解。我个人觉得没必要用反射。
                // 根据仿真的类型，调用相应的仿真引擎。
                switch (SIMID) {
                    case CDSIM:
                        CDSimulator.nextExperiment();// 具体怎么运行且听下文分解
                        break;
                    case EDSIM:
                        EDSimulator.nextExperiment(); // 同上
                        break;
                }
            }

        } catch (MissingParameterException e) {
            System.err.println(e + "");
            System.exit(1);
        } catch (IllegalParameterException e) {
            System.err.println(e + "");
            System.exit(1);
        }

        // undocumented testing capabilities
        // 这一部份源码没有任何说明，看样子作者是用来做测试的
        if (Configuration.contains("__t")) {
            System.out.println(System.currentTimeMillis() - time);
        }
        if (Configuration.contains("__x")) {
            Network.test();
        }

    }
}
