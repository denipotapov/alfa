package com.alfach.compete.conf;

import java.io.IOException;
import java.util.*;

/**
 * Created by User on 11/3/2016.
 */
public class Configuration {

    public final static String BASE_CONFIGURATION_FILE = "base.properties";
    public final static String BROWSER_TYPE_PROP_NAME = "browser.NAME";

    private Configuration(){}

    public static void loadProperties(){
        Properties properties = new Properties();
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(BASE_CONFIGURATION_FILE));
        }
        //ToDo: No Logger implemented yet. So as a simplest way just print stack trace :(. Worse approach
        catch (IOException e) {e.printStackTrace();}

        if (properties != null)
            for (Map.Entry s : properties.entrySet())
                System.setProperty(s.getKey().toString(), s.getValue().toString());
    }

}
