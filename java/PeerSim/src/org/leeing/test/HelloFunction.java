package org.leeing.test;

import peersim.cdsim.CDProtocol;
import peersim.config.FastConfig;
import peersim.core.CommonState;
import peersim.core.Linkable;
import peersim.core.Node;
import peersim.vector.SingleValueHolder;

/**
 *
 * @author leeing
 * @date Mar 10, 2011
 */
public class HelloFunction extends SingleValueHolder implements CDProtocol {

    public HelloFunction(String prefix) {
        super(prefix);
    }

    public void nextCycle(Node node, int protocolID) {
        int linkableID = FastConfig.getLinkable(protocolID);
         Linkable linkable = (Linkable) node.getProtocol(linkableID);
          if (linkable.degree() > 0) {

            // 随机取一个节点
             System.err.println("邻居有："+linkable.degree()+" 个");
            Node peer = linkable.getNeighbor(CommonState.r.nextInt(linkable
                    .degree()));

            // Failure handling
            if (!peer.isUp()){
                return;
              }
            HelloFunction neighbor = (HelloFunction) peer
                    .getProtocol(protocolID);
              System.out.println("the neighbor's value is :"+neighbor.getValue());
        }

    }
 
}
