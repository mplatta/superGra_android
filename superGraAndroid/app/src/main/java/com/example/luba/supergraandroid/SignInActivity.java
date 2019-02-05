package com.example.luba.supergraandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class SignInActivity extends AppCompatActivity {

    private EditText ipInputEditText;
    private static TextView connectText;
    private static String ipInput = "";
    private static String ip_skan = "";
    private static String ipUrl = "http://";
    private static String ipPort = ":34450/api/queue/GetQueue";
    private static String ipAddQueue = ":34450/api/queue/AddQueue";
    private static String ip_url_addr = "http://192.168.0.73:34450/api/queue/GetQueue";
    private static String macAddr = "";
    private ConnectivityManager connMgr;
    private static String androidId;


    public static void setIp_skan(String ip_skan) {
        SignInActivity.ip_skan = ip_skan;
    }

    public static void setIpInput(String ipInput) {
        SignInActivity.ipInput = ipInput;
    }

    public static void setconnectText(String connectT) {
        connectText.setText(connectT);
    }

    public static String getIp_url_addr() {
        return ip_url_addr;
    }

    public static void setIp_url_addr(String ip_url_addr) {
        SignInActivity.ip_url_addr = ip_url_addr;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ipInputEditText.setText(ipInput);
        SignInActivity.setIp_skan("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Config.setAndroidId(androidId);

        ipInputEditText = (EditText)findViewById(R.id.ip_address_edit_txt);
        connectText = (TextView)findViewById(R.id.validateConnection);

        connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private boolean validateIp() {
        ipInput = ipInputEditText.getText().toString().trim();

        if (ipInput.isEmpty()) {
            ipInputEditText.setError("No wpisz coś");
            return false;
        } else if (!Patterns.IP_ADDRESS.matcher(ipInput).matches()) {
            ipInputEditText.setError("źle!!");
            return false;
        } else {
            ipInputEditText.setError(null);
            return true;
        }
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ZasadyActivity.class);
        startActivity(intent);

    }

    public void scanQR(View view){
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public void connect(View view){
        if(validateIp()){
            connectText.setText("Staram sie połączyć!");

            this.setIp_url_addr(ipUrl + ipInput + ipPort);

            Config.setIp(ipInput);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            String out = null;
            if (networkInfo != null && networkInfo.isConnected()){
//                new ConnectionModule().execute(ip_url_addr);
//                Log.i("staram się połączyć", "ok");
//
                Intent intent = new Intent(this, ListaKart.class);
                startActivity(intent);
//                JSONObject postData = new JSONObject();
//                try {
//                    postData.put("Id", androidId);
//                        Log.i("ZXC", postData.toString() );
//                        //TODO: tu w out masz stringa  jsonem, wyciągnij go jsonobject i dopiero te akcje, ale ic więcej nie potrzebujemy tutaj po prostu sprawdzamy czy jest sieć
//                    out = new ConnectionModule()
//                            .execute(ip_url_addr, postData.toString())
//                            .get();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
            }

        }
        else {
            connectText.setText("Zły adres IP..");
        }

    }

    public static String getAndroidId() {
        return androidId;
    }
}

