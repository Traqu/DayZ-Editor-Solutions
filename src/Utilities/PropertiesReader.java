package Utilities;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties property = new Properties();

    public PropertiesReader() {

    }

//    public static String getProperty(String propertyName) {
//        try {
//            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
//            return property.getProperty(propertyName);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
    public static String getVersion(boolean formatToTitle){
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            String prefix = "    (Version ";
            String suffix = ")";
            return prefix + property.getProperty("version") + suffix;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static String getVersion(){
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            return property.getProperty("version");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
