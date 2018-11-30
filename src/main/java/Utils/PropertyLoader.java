package Utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String PROP_FILE = "/app.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

}
