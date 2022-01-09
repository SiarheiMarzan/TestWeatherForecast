package util;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(getEnv("environment"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }

    public static String getEnv(String name) {
        String value = System.getenv(name);
        if (value == null) {
            value = System.getenv(name);
        }
        return value;
    }
}
