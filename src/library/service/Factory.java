/**
 *
 * @author Mark Barone
 */
package library.service;

import java.io.FileInputStream;
import java.util.Properties;

public class Factory {
    
    public Factory (){
    }
    
    public IService getService(String serviceName) throws Exception {
        Class c = Class.forName(getImplName(serviceName));
        return (IService) c.newInstance();
    }
    
    private String getImplName(String serviceName) throws Exception {
        Properties props;
        try (FileInputStream fis = new FileInputStream("C:\\PrimroseProject\\Primrose Assessment Platform\\config\\properties.txt")) {
            props = new Properties();
            props.load(fis);
        }
        return props.getProperty(serviceName);
    }
}
