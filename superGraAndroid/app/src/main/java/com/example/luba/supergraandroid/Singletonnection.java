package com.example.luba.supergraandroid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Singletonnection {
    private static Singletonnection instance;
    String ipconnection = "http://192.168.1.1/";
    URL url;
    HttpURLConnection con;
    String id = "";

    private Singletonnection() {
    }

    public static Singletonnection getInstance() {
        if (instance == null) {
            instance = new Singletonnection();
        }
        return instance;
    }

/*    {
        try {
            url = new URL(ipconnection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}