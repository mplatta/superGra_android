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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SignInActivity extends AppCompatActivity {

    private EditText ipInputEditText;
    private static TextView connectText;
    private static String ipInput = "";
    private ConnectivityManager connMgr;
    private static String androidId;

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
    }

    @SuppressLint("HardwareIds")
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

            Config.setIp(ipInput);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            String out = null;
            if (networkInfo != null && networkInfo.isConnected()){

                JSONObject postData = new JSONObject();
                try {
                    postData.put("Id", androidId);

                    out = new ConnectionModule()
                            .execute(Config.getApiGetQueue(), postData.toString())
                            .get();
                } catch (JSONException e) {
                    e.printStackTrace();
                    connectionError();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    connectionError();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    connectionError();
                }

                JSONObject resultJSON = null;

                try {
                    resultJSON = new JSONObject(out);
                    int status = resultJSON.getInt("Action");

                    if (status == 0) {
                        Intent intent = new Intent(this, ListaKart.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        connectionError();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    connectionError();
                }
            }

        }
        else {
            connectText.setText("Zły adres IP..");
        }
    }

    private void connectionError() {
        Toast.makeText(this, "Something go wrong with connection!", Toast.LENGTH_LONG).show();
    }
}

