package config;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PropertyManager {

    private static Logger LOGGER = LoggerFactory.getLogger(PropertyManager.class);
    private static Properties properties;

    public static void loadProperties() {
         properties = new Properties();
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("application.properties"));
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream(getProperty("environment")+"/petstore.properties"));
        } catch (Exception e) {
           LOGGER.info(e.getMessage());
        }
    }
    public static String getProperty(String key) {
        if(properties==null){
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
