package simulation;

import java.util.Map;
import peersim.core.GeneralNode;

/**
 *
 * @author leeing
 * @date Mar 10, 2011
 */
public class PeerNode extends GeneralNode{

    Map<Integer,Long> fileindex;

    public PeerNode(String prefix){
        super(prefix); 
    }

    boolean isExits(int fileID){
        return fileindex.keySet().contains(fileID);
    }

    @Override
    public String toString(){
        return "节点"+super.getID()+" 包含文件个数："+fileindex.size();
    }

    public Map<Integer,Long> getFileIndex(){
        return this.fileindex;
    }

    public void setFileIndex(Map<Integer,Long> fileIndex){
        this.fileindex = fileIndex;
    }
}
