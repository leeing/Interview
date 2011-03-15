package simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import peersim.core.Control;
import peersim.core.Network;

/**
 *
 * @author leeing
 * @date Mar 11, 2011
 */
public class InitFiles implements Control{

    public InitFiles(String prefix){
        super();
    }
    public boolean execute() {
        System.out.println("为节点分配文件:"+Network.getCapacity());

        for(int i = 0;i<Network.getCapacity();i++){
            PeerNode peer = (PeerNode)Network.get(i);
            Map<Integer,Long> fileIndex  = new HashMap<Integer,Long>();
            Random random = new Random();
            for(int j = 0;j< random.nextInt(100);j++){
                fileIndex.put(random.nextInt(1000), (long)random.nextInt(100));
            }
            peer.setFileIndex(fileIndex);
            System.out.println("Node "+i+" 拥有文件："+fileIndex.size()+" :"+fileIndex);
        }
        return false;
    }

}
