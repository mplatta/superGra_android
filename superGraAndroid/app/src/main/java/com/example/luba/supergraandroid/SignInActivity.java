package com.example.luba.supergraandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class SignInActivity extends AppCompatActivity {

    private EditText ipInputEditText;
    private static TextView connectText;
    private static String ipInput = "";
    private static String ip_skan = "";
    private static String ipUrl = "http://";
    private static String ipPort = ":34450/api/test";
    private static String macAddr = "";
    private ConnectivityManager connMgr;


    public static void setIp_skan(String ip_skan) {
        SignInActivity.ip_skan = ip_skan;
    }

    public static void setIpInput(String ipInput) {
        SignInActivity.ipInput = ipInput;
    }

    public static void setconnectText(String connectT) {
        connectText.setText(connectT);
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

        macAddr = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        ipInputEditText = (EditText)findViewById(R.id.ip_address_edit_txt);
        connectText = (TextView)findViewById(R.id.validateConnection);

        connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        connectText.setText(macAddr);

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
            String ip_url_addr = ipUrl + ipInput + ipPort;
            //String ip_url_michu = "http://192.168.0.5:34450/api/test";

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()){
                new ConnectionModule().execute(ip_url_addr);
                Log.i("staram się połączyć", "ok");
            }

        }
        else {
            connectText.setText("Zły adres IP..");
        }

    }

}

