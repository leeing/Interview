package peersim.core;

/**
 * Instances of classes implementing this interface can form networks (graphs)
 * in the simulator framework.
 * The interface is similar to one of a container (containing neighbors),
 * only the types of the contained elements have to be {@link Node}.
 * The neighbor collection is defined in a random access list style, but
 * it must follow the contract of a set too (elements must be unique).
 * <p>
 * Also note that there is no possibility to remove elements from the
 * neighbor set. This is because removal is usually costly and it does not make
 * sense in the context of our applications,
 * where this interface is used for 1) initialization and 2)
 * providing an interface for other protocols for accessing the neighbor list.
 * Protocols that manage links remove, refresh, etc their link set internally
 * or through custom methods.
 * Therefore it would only put an unnecessary burden on implementors.
 *
 * 实现这个接口的类实例能在仿真框架中行组成网络（图）
 * 这个接口类似于包含邻居的容器，包含的元素类型是Node。邻居集合的列表是以一个随机访问的列表
 * 的形式来定义的，但是列表元素必须是唯一的。
 *
 * 同时也要注意不能从邻居集合中删除元素。因为删除一般代价大且在我们的应用中不起什么作用。
 * 这个接口用于：
 * 1. 初始化
 * 2. 为其它协议提供了一个接口用来访问邻居列表。
 * 管理删除，更新links的Protocols
 */
public interface Linkable extends Cleanable {

    /**
     * Returns the size of the neighbor list.
     */
    public int degree();

    /**
     * Returns the neighbor with the given index. The contract is that
     * listing the elements from index 0 to index degree()-1 should list
     * each element exactly once if this object is not modified in the
     * meantime. It throws an IndexOutOfBounds exception if i is negative
     * or larger than or equal to {@link #degree}.
     */
    public Node getNeighbor(int i);

    /**
     * Add a neighbor to the current set of neighbors. If neighbor
     * is not yet a neighbor but it cannot be added from other reasons,
     * this method should not return normally, that is, it must throw
     * a runtime exception.
     * @return true if the neighbor has been inserted; false if the
     *    node is already a neighbor of this node
     */
    public boolean addNeighbor(Node neighbour);

    /**
     * Returns true if the given node is a member of the neighbor set.
     */
    public boolean contains(Node neighbor);

    /**
     * A possibility for optimization. An implementation should try to
     * compress its internal representation. Normally this is called
     * by initializers or other components when
     * no increase in the expected size of the neighborhood can be
     * expected.
     */
    public void pack();
}
