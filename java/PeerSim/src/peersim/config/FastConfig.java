package peersim.config;

/**
 * Reads configuration regarding relations between protocols.
 * 
 * Technically, this class is not necessary because protocols could
 * access the configuration directly. However, it provides much faster
 * access to "linkable" and "transport" information, enhancing runtime speed.
 *
 * This class is a static singleton and is initialized only when first accessed.
 * During initialization it reads and caches the configuration info it handles.
 *
 * 读取关于协议之间关系的配置
 *
 * 从技术上来说，这个类不是必须的，因为protocols可以直接访问Configuration，然而它提供了对
 * "linkable"和"transport"的快速访问，提升了运行时的速度。
 *
 * 这个类是一个静态的singleton，只在第一次访问时被初始化。在初始化时，它读取并缓存这些配置
 */
public class FastConfig {

// ======================= fields ===========================================
// ===========================================================================
    /**
     * Parameter name in configuration that attaches a linkable protocol to a
     * protocol. The property can contain multiple protocol names, in one line,
     * separated by non-word characters (e.g. whitespace or ",").
     * @config
     */
    private static final String PAR_LINKABLE = "linkable";
    /**
     * Parameter name in configuration that attaches a transport layer protocol to a
     * protocol.
     * @config
     */
    private static final String PAR_TRANSPORT = "transport";
    /**
     * This array stores the protocol ids of the {@link peersim.core.Linkable}
     * protocols that are linked to the protocol given by the array index.
     *
     * 这个数组存储 peersim.core.Linkable 的 protocols ids。
     */
    protected static final int[][] links;
    /**
     * This array stores the protocol id of the {@link peersim.transport.Transport}
     * protocol that is linked to the protocol given by the array index.
     */
    protected static final int[] transports;

// ======================= initialization ===================================
// ==========================================================================
    /**
     * This static initialization block reads the configuration for information that
     * it understands. Currently it understands property {@value #PAR_LINKABLE}
     * and {@value #PAR_TRANSPORT}.
     *
     * Protocols' linkable and transport definitions are prefetched
     * and stored in arrays, to enable fast access during simulation.
     *
     * Note that this class does not perform any type checks. The purpose of the
     * class is purely to speed up access to linkable and transport information,
     * by providing a fast alternative to reading directly from the
     * <code>Configuration</code> class.
     *
     * 读取配置信息。当前它能理解的是 PAR_LINKABLE 和 PAR_TRANSPORT 参数
     */
    static {
        // 取得所有 protocol 的名字
        String[] names = Configuration.getNames(Configuration.PAR_PROT);
        links = new int[names.length][];
        transports = new int[names.length];
        for (int i = 0; i < names.length; ++i) {
            if (Configuration.contains(names[i] + "." + PAR_LINKABLE)) {
                // get string of linkables
                String str = Configuration.getString(names[i] + "." + PAR_LINKABLE);
                // split around non-word characters
                String[] linkNames = str.split("\\W+");
                links[i] = new int[linkNames.length];
                for (int j = 0; j < linkNames.length; ++j) {
                    links[i][j] = Configuration.lookupPid(linkNames[j]);
                }
            } else {
                links[i] = new int[0]; // empty set
            }
            if (Configuration.contains(names[i] + "." + PAR_TRANSPORT)) {
                transports[i] =
                        Configuration.getPid(names[i] + "." + PAR_TRANSPORT);
            } else {
                transports[i] = -1;
            }
        }
    }

// ---------------------------------------------------------------------
    /** to prevent construction */
    private FastConfig() {
    }

// ======================= methods ==========================================
// ==========================================================================
    /**
     * Returns true if the given protocol has at least one linkable protocol
     * associated with it, otherwise false.
     */
    public static boolean hasLinkable(int pid) {
        return numLinkables(pid) > 0;
    }

// ---------------------------------------------------------------------
    /**
     * Returns the number of linkable protocols associated with a given protocol.
     *
     * 返回与一个给定的 protocol 关联的 linkable protocol 的个数。
     */
    public static int numLinkables(int pid) {
        return links[pid].length;
    }

// ---------------------------------------------------------------------
    /**
     * Returns the protocol id of the <code>linkIndex</code>-th linkable used by
     * the protocol identified by pid. Throws an
     * IllegalParameterException if there is no linkable associated with the given
     * protocol: we assume here that this happens when the configuration is
     * incorrect.
     *
     * 返回第 linkIndex 个 protocol id。
     */
    public static int getLinkable(int pid, int linkIndex) {
        if (linkIndex >= numLinkables(pid)) {
            String[] names = Configuration.getNames(Configuration.PAR_PROT);
            throw new IllegalParameterException(names[pid],
                    "Protocol " + pid + " has no " + PAR_LINKABLE
                    + " parameter with index" + linkIndex);
        }
        return links[pid][linkIndex];
    }

//---------------------------------------------------------------------
    /**
     * Invokes <code>getLinkable(pid, 0)</code>.
     */
    public static int getLinkable(int pid) {
        return getLinkable(pid, 0);
    }

// ---------------------------------------------------------------------
    /**
     * Returns true if the given protocol has a transport protocol associated with
     * it, otherwise false.
     */
    public static boolean hasTransport(int pid) {
        return transports[pid] >= 0;
    }

// ---------------------------------------------------------------------
    /**
     * Returns the id of the transport protocol used by the protocol identified
     * by pid.
     * Throws an IllegalParameterException if there is no transport associated
     * with the given protocol: we assume here that his happens when the
     * configuration is incorrect.
     */
    public static int getTransport(int pid) {
        if (transports[pid] < 0) {
            String[] names = Configuration.getNames(Configuration.PAR_PROT);
            throw new IllegalParameterException(names[pid],
                    "Protocol " + pid + " has no " + PAR_TRANSPORT + " parameter");
        }
        return transports[pid];
    }
}
