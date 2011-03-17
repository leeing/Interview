package org.leeing.test;

import peersim.core.Control;


/**
 *
 * @author leeing
 * @date Mar 17, 2011
 */
public class MyControl implements Control{

    public MyControl(String prefix){
        super();
    }
    public boolean execute() {
        System.out.println("MyControl");
        return false;
    }

}
