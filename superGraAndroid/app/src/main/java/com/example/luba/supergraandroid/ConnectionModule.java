package com.example.luba.supergraandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class ConnectionModule extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        String url_adres = strings[0];
        Log.i("adres",url_adres);
        String responseString = "";
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(url_adres);
            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");

            conn.connect();
            Log.i("superGraAndroid","Odczythgljbhkj,m");

            int response = conn.getResponseCode();

            // Kod 200, czytamy dane
            if (response == 200){

                int len = conn.getContentLength();
                is = conn.getInputStream();

                // Konwersja IS na String
                Reader reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[len];

                // Rzeczywista liczba wczytanych znaków
                int lenActual = reader.read(buffer);
                responseString = new String(buffer).substring(0,lenActual);

                Log.i("superGraAndroid","Odczytany JSON: " + responseString);

            }
        } catch (MalformedURLException e) {
            Log.i("connection", "cos poslzo zle");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("IO gowno", "cos poslzo zle");
            e.printStackTrace();
        }
        finally{
            // Zamykamy IS i Connection
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }

        return responseString;

    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject resultJSON = new JSONObject(result);
            Boolean s = resultJSON.getBoolean("Status");
            //String status = resultJSON.getString("Status");
            Log.d("--------RESUT JSON", s.toString());
            if (s){
                SignInActivity.setconnectText("True");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
