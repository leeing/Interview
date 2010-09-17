/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leeing.scalajava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author leeing
 */
public class Person {
    private String name;
    private static final Logger logger = LoggerFactory.getLogger(Person.class);
    
    public Person(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        Person p = new Person("leeing");
        logger.info("this is P:"+p.name);
        logger.debug("it's a debug message!");
    }
}
