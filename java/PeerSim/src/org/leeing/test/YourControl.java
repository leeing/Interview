package org.leeing.test;

import peersim.core.Control;

/**
 *
 * @author leeing
 * @date Mar 17, 2011
 */
public class YourControl implements Control{

    public YourControl(String prefix){
        super();
    }
    public boolean execute() {
        System.out.println("Your control");
        return false;
    }

}
