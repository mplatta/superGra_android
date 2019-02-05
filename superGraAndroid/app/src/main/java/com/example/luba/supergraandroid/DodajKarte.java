package com.example.luba.supergraandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class DodajKarte extends AppCompatActivity {


    private SQLiteDatabase mDatabase;
    private EditText editNazwa;
    private EditText editOpis;
    private Integer numberOfLines = 1;

    private LinearLayout skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_karte);

        BazaDanychHelper dbHelper = new BazaDanychHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        editNazwa = (EditText) findViewById(R.id.add_nazwa);
        editOpis = (EditText) findViewById(R.id.add_opis);

        skill = (LinearLayout) findViewById(R.id.add_linear_layout);

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

        boolean eN = validateField(this.editNazwa);
        boolean eO = validateField(this.editOpis);

        if(eN && eO){
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
        mDatabase.insert(BazaDanych.BazaDanychPokemon.TABLE_NAME, null, cv);
    }

    public void addSkill(View view) {

        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));

        ll.setOrientation(LinearLayout.HORIZONTAL);
        EditText etPar = new EditText(this);
        etPar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));
        etPar.setHint("Parametr");
        etPar.setId(numberOfLines);
        ll.addView(etPar);
        numberOfLines++;

        EditText et = new EditText(this);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1));
        et.setSingleLine(true);
        et.setHint("Wartość");
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et.setId(numberOfLines);
        ll.addView (et);
        numberOfLines++;

        skill.addView(ll);
    }

    public void wybierz(View view) {
        Character character = new Character();

        character.setAndId(Config.getAndroidId());
        character.setCharacterId(0); // nie zmieniaj, to jest potrzebne tylko dla serwera narazie
        character.setName(editNazwa.getText().toString());
        character.setType("klasa postaci"); // poza nazwą i opisem trzeba jeszcze dać możlwiość wpisania klasy postaci (tutaj nazwane jako typ bo słówko class jest niedostępne)
        character.setDescription(editOpis.getText().toString());

        for (int i = 0; i < skill.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout)skill.getChildAt(i);

            EditText skill_name = (EditText)layout.getChildAt(0);

            if (!skill_name.getText().toString().isEmpty()) {
                EditText skill_value = (EditText)layout.getChildAt(1);
                character.getStats().add(new Stat(skill_name.getText().toString(), Integer.parseInt(skill_value.getText().toString())));
            }
        }

        String out = null;
        boolean ok = true;
//        boolean ok = false;
//
//        try {
//            out = new ConnectionModule()
//                .execute(Config.getApiCreateCharacter(), character.getJSON())
//                .get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject resultJSON = null;
//        try {
//            resultJSON = new JSONObject(out);
//            Boolean status = resultJSON.getBoolean("Status");
//
//            if (status) {
//                Integer char_id = resultJSON.getInt("Id");
//
//                if (char_id != null) {
//                    character.setCharacterId(char_id);
//
//                    ok = true;
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        if (ok) {
            Intent intent = new Intent(this, ActivityEkranGry.class);
            intent.putExtra("ChosenCharacter", character);
            startActivity(intent);
        }

    }
}
