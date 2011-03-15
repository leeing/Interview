package org.leeing.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import peersim.config.Configuration;
import peersim.config.ParsedProperties;

/**
 *
 * @author leeing
 * @date Mar 4, 2011
 */
public class TestConfig {
    public static void main(String[] args) {
        try {
            ParsedProperties p = new ParsedProperties("config.txt");
            Configuration.setConfig( p );
            String[] names = Configuration.getNames("protocol.avg");
            for(int i = 0;i<names.length;i++){
                System.out.println(names[i]);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
