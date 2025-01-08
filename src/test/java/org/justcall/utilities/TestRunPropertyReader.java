package org.justcall.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRunPropertyReader {
    public Properties properties;

    public TestRunPropertyReader(String pathToProperties) {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToProperties);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPropertyMethod(String key) {
        return properties.getProperty(key);
    }
}
