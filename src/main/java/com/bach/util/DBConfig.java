package com.bach.util;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream in = DBConfig.class.getResourceAsStream("/db.properties")) {
            Properties props = new Properties();
            props.load(in);

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu muốn, có thể throw RuntimeException để developer biết ngay.
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
