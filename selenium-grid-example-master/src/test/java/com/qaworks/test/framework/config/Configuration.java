package com.qaworks.test.framework.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by matttully on 07/06/2016.
 */
public class Configuration {

    public static final String KEY_GRID_RUN = "grid.run";

    public static final String KEY_BROWSER_TYPE = "browser.type";

    public static final String KEY_BROWSER_VERSION = "browser.version";

    private Properties myprops = null;

    private static Configuration configuration;

    private Configuration() throws Exception {

        myprops = new Properties(System.getProperties());

        try {
            myprops.load(new FileInputStream("src/test/java/com/qaworks/test/framework/config/configuration.properties"));
        } catch (FileNotFoundException fnfe) {
            System.err.println("ERROR: " + fnfe.getMessage());
        } catch (IOException ioe) {
            // TODO decide what we require here?
            System.err.println("ERROR: " + ioe.getMessage());
        }
        myprops.putAll(System.getProperties());
    }

    public static synchronized Configuration getConfiguration()
            throws Exception {
        if (configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    protected String getProperty(String key) {
        String value = null;
        if (myprops != null) {

            value = myprops.getProperty(key);

            if (value == null || value.length() == 0) {
                value = myprops.getProperty(key + ".default");
            }
        }
        return value;
    }

    public boolean getIsGridRun() {
        String gridRun = getProperty(KEY_GRID_RUN);
        if (gridRun.equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public String getBrowserType() {
        return getProperty(KEY_BROWSER_TYPE);
    }

    public String getBrowserVersion() {
        return getProperty(KEY_BROWSER_VERSION);
    }
}

