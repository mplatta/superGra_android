package com.example.luba.supergraandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;


public class DodajKarte extends AppCompatActivity {


    private SQLiteDatabase mDatabase;
    private static EditText editNazwa;
    private static EditText editOpis;
    private static EditText editSila;
    private static EditText editSzybkosc;
    private static EditText editZywiol;
    private static EditText editZycie;
    private Integer numberOfLines = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_karte);

        BazaDanychHelper dbHelper = new BazaDanychHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        editNazwa = (EditText) findViewById(R.id.add_nazwa);
        editOpis = (EditText) findViewById(R.id.add_opis);
        //editSila = (EditText) findViewById(R.id.add_sila);
        //editSzybkosc = (EditText) findViewById(R.id.add_szybkosc);
        //editZywiol = (EditText) findViewById(R.id.add_zywiol);
        //editZycie = (EditText) findViewById(R.id.add_zycie);

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
            addToDatabase();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill to gowno",Toast.LENGTH_SHORT).show();
        }

    }

    private void addToDatabase(){
        ContentValues cv = new ContentValues();
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_NAZWA, editNazwa.getText().toString());
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_OPIS, editOpis.getText().toString());
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_SILA, Integer.parseInt(editSila.getText().toString()));
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_SZYBKOSC, Integer.parseInt(editSzybkosc.getText().toString()));
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_ZYCIE, Integer.parseInt(editZycie.getText().toString()));
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_ZYWIOL, editZywiol.getText().toString());
        mDatabase.insert(BazaDanych.BazaDanychPokemon.TABLE_NAME, null, cv);
    }

    public void addSkill(View view) {

        LinearLayout skill = (LinearLayout) findViewById(R.id.add_linear_layout);

        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));

        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));
        textView.setHint("Paramet");
        textView.setId(numberOfLines);
        ll.addView(textView);
        numberOfLines++;

        EditText et = new EditText(this);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));
        et.setSingleLine(true);
        et.setHint("Wartość");
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et.setId(numberOfLines);
        ll.addView (et);
        numberOfLines++;

        skill.addView(ll);
    }

}
