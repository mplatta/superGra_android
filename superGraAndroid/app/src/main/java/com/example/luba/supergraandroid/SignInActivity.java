package com.example.luba.supergraandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText ipInputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        startActivity(new Intent(SignInActivity.this, CreateCharacter.class));
        ipInputEditText = (EditText)findViewById(R.id.ip_address_edit_txt);
    }

    private boolean validateIp() {  //TODO podpiac pod edittexta
        String ipInput = ipInputEditText.getText().toString().trim();

        if (ipInput.isEmpty()) {
            ipInputEditText.setError("No chyba nie!"); //TODO napisać cos madrego
            return false;
        } else if (!Patterns.IP_ADDRESS.matcher(ipInput).matches()) {
            ipInputEditText.setError("zle!!"); //TODO napisać cos madrego
            return false;
        } else {
            ipInputEditText.setError(null);
            return true;
        }
    }
}
