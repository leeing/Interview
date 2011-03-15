package simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author leeing
 * @date Mar 11, 2011
 */
public class Node {
    private int id;
    private Map<Integer,Integer> fileIndex = new HashMap<Integer,Integer>();

    private List<Node> neighbors = new ArrayList<Node>();

    public Node(int id){
        this.id = id;
    }

    public List<Node> getNeighbors(){
        return this.neighbors;
    }

}
