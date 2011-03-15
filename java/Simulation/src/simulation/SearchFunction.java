package simulation;

import peersim.cdsim.CDProtocol;
import peersim.core.Node;
import peersim.vector.SingleValueHolder;

/**
 *
 * @author leeing
 * @date Mar 10, 2011
 */
public class SearchFunction extends SingleValueHolder implements CDProtocol{

    public SearchFunction(String prefix){
        super(prefix);
    }
    public void nextCycle(Node node, int i) {
        System.out.println(node);
    }

}
