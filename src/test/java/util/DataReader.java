package util;

import java.util.ResourceBundle;

public class DataReader {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getenv("environment"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
