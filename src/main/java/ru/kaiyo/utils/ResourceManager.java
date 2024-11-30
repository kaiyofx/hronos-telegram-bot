package ru.kaiyo.utils;

import ru.kaiyo.Main;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ResourceManager {

    public static InputStream getResource(String filename) {
        try {
            URL url = Main.class.getClassLoader().getResource(filename);
            if (url == null) return null;

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
