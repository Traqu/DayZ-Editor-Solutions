package Utilities;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties property = new Properties();

    public PropertiesReader() {

    }

    public static String getProperty(String propertyName) {
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            return property.getProperty(propertyName);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getVersion(boolean respectTitleFormatting) {
        String result;
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            String prefix = "  -  version ";
            result = prefix + property.getProperty("version");

        } catch (IOException ex) {
            ex.printStackTrace();
            result = null;
        }
        return result;
    }

    public static String getVersion() {
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            return property.getProperty("version");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getAuthor() {
        try {
            property.load(PropertiesReader.class.getClassLoader().getResourceAsStream(".properties"));
            return property.getProperty("author");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}