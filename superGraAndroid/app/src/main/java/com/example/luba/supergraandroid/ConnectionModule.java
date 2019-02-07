package com.example.luba.supergraandroid;

import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionModule extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        StringBuilder data = new StringBuilder();

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");

            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(params[1].toString());
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return data.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
