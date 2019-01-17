package com.example.luba.supergraandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SignInActivity extends AppCompatActivity {

    private EditText ipInputEditText;
    private TextView connectText;
    private static String ip_skan = "";

    public static String getIp_skan() {
        return ip_skan;
    }

    public static void setIp_skan(String ip_skan) {
        SignInActivity.ip_skan = ip_skan;
    }


    @Override
    protected void onResume() {
        super.onResume();
        ipInputEditText.setText(ip_skan);
        SignInActivity.setIp_skan("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ipInputEditText = (EditText)findViewById(R.id.ip_address_edit_txt);
        connectText = (TextView)findViewById(R.id.validateConnection);
    }

    private boolean validateIp() {  //TODO podpiac pod edittexta
        String ipInput = ipInputEditText.getText().toString().trim();

        if (ipInput.isEmpty()) {
            ipInputEditText.setError("No wpisz coś"); //TODO napisać cos madrego
            return false;
        } else if (!Patterns.IP_ADDRESS.matcher(ipInput).matches()) {
            ipInputEditText.setError("źle!!"); //TODO napisać cos madrego
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
        }
        else {
            connectText.setText("Zły adres IP..");
        }

    }
}