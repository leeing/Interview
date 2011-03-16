package peersim.cdsim;

import peersim.core.*;

/**
 * Shuffles the network. After shuffling, the order in which the nodes
 * are iterated over during a cycle of a cycle driven simulation
 * will be random. It has an effect only in cycle driven simulations.
 *
 * 为网络重新洗牌，在洗牌后，节点被迭代的顺序将变成随机的。但注意这只在cycle-driven仿真时
 * 有效。
 */
public class Shuffle implements Control {

// ========================= fields =================================
// ==================================================================
// ==================== initialization ==============================
// ==================================================================
    /** Does nothing. */
    public Shuffle(String prefix) {
    }

// ===================== public methods ==============================
// ===================================================================
    /**
     * Calls {@link Network#shuffle()}.
     * As a result, the order in which the nodes
     * are iterated over during a cycle of a cycle driven simulation
     * will be random. It has an effect only in cycle driven simulations.
     */
    public final boolean execute() {

        Network.shuffle();
        return false;
    }
}
