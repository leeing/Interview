package peersim.core;

/**
 * Generic interface for classes that are responsible for observing or modifying
 * the ongoing simulation. It is designed to allow maximal flexibility therefore
 * poses virtually no restrictions on the implementation.
 *
 * 负责观察或修改后续仿真的通用类。它极富有弹性，因而能在实现时功能不被限制。
 */
public interface Control {

    /**
     * Performs arbitrary modifications or reports arbitrary information over the
     * components.
     * 对组件进行任意修改或报告任何信息。如果返回true，说明仿真应当停止。
     * @return true if the simulation has to be stopped, false otherwise.
     */
    public boolean execute();
}
