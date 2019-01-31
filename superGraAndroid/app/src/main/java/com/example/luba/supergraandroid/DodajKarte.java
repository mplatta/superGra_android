package com.example.luba.supergraandroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DodajKarte extends AppCompatActivity {


    private SQLiteDatabase mDatabase;
    private static EditText editNazwa;
    private static EditText editOpis;
    private static EditText editSila;
    private static EditText editSzybkosc;
    private static EditText editZywiol;
    private static EditText editZycie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_karte);

        BazaDanychHelper dbHelper = new BazaDanychHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        editNazwa = (EditText) findViewById(R.id.add_nazwa);
        editOpis = (EditText) findViewById(R.id.add_opis);
        editSila = (EditText) findViewById(R.id.add_sila);
        editSzybkosc = (EditText) findViewById(R.id.add_szybkosc);
        editZywiol = (EditText) findViewById(R.id.add_zywiol);
        editZycie = (EditText) findViewById(R.id.add_zycie);

    }

    private boolean validateField(EditText textInputField) {

        String txt = textInputField.getText().toString();

        if (txt.isEmpty()) {
            textInputField.setError("No wpisz coś");
            return false;
        } else {
            textInputField.setError(null);
            return true;
        }
    }

    public void validateSave(View view){

        Boolean eN = validateField(this.editNazwa);
        Boolean eO = validateField(this.editOpis);
        Boolean eS = validateField(this.editSila);
        Boolean eSz = validateField(this.editSzybkosc);
        Boolean eZ = validateField(this.editZywiol);
        Boolean eZy = validateField(this.editZycie);

        if(eN && eO && eS && eSz && eZ && eZy){
            Toast.makeText(getApplicationContext(),"Please ok",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill to gowno",Toast.LENGTH_SHORT).show();
        }

    }


}
