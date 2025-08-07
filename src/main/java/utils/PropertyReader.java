package utils;

import org.apache.logging.log4j.Logger;
import utils.exceptions.ExceptionHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

    private static final Logger logger = LogFactory.getLogger(PropertyReader.class);
    private static ThreadLocal<Properties> tproperties = new ThreadLocal<>();
    private static final Object lock = new Object();

    private static void initialisePropertyReader() {
        synchronized (lock) {
            try {
                Properties properties = new Properties();
                properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("config.properties"));
                tproperties.set(properties);
            } catch (IOException e) {
                logger.error("Error occurred while reading the properties file");
                throw new ExceptionHandler("Error occurred while reading the properties file");
            }
        }
//        try {
//            tproperties.set(new Properties());
//            tproperties.get().load(PropertyReader.class.getClassLoader().getResourceAsStream("config.properties"));
//        } catch (IOException e) {
//            logger.error("Error occurred while reading the properties file");
//            throw new ExceptionHandler("Error occurred while reading the properties file");
//        }
    }

    public static String getPropertyValue(String key) throws ExceptionHandler {
        if (Objects.isNull(tproperties.get())) {
            initialisePropertyReader();
        }
        String value = tproperties.get().getProperty(key);
        if (value == null) {
            logger.error("Property not found for key: " + key);
            throw new ExceptionHandler("Property not found for key: " + key);
        }
        return tproperties.get().getProperty(key);
    }
}
