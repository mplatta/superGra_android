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
    private EditText editClass;
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
        editClass = (EditText) findViewById(R.id.add_zycie);

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

    public boolean validateSave(View view){

        boolean eN = validateField(this.editNazwa);
        boolean eO = validateField(this.editOpis);

        if(eN && eO){
            Toast.makeText(getApplicationContext(),"Please ok",Toast.LENGTH_SHORT).show();
            //addToDatabase();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill to gowno",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void addToDatabase(Character ch){
        ContentValues cv = new ContentValues();
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_NAZWA, editNazwa.getText().toString());
        cv.put(BazaDanych.BazaDanychPokemon.COLUMN_OPIS, editOpis.getText().toString());
        mDatabase.insert(BazaDanych.BazaDanychPokemon.TABLE_NAME, null, cv);

        ContentValues characterValues = new ContentValues();
//        characterValues.put(BazaDanych.BazaDanychPokemon.CH_COLUMN_ID, ch.getCharacterId());
        characterValues.put(BazaDanych.BazaDanychPokemon.CH_COLUMN_NAME, ch.getName());
        characterValues.put(BazaDanych.BazaDanychPokemon.CH_COLUMN_DESCRIPTION, ch.getDescription());
        characterValues.put(BazaDanych.BazaDanychPokemon.CH_COLUMN_CLASS, ch.getType());
        mDatabase.insert(BazaDanych.BazaDanychPokemon.CH_TABLE_NAME, null, characterValues);

        Cursor cursor = null;
        Integer ch_id = null;

        cursor = mDatabase.query(
                BazaDanych.BazaDanychPokemon.CH_TABLE_NAME,
                new String[] { BazaDanych.BazaDanychPokemon.CH_COLUMN_ID },
                BazaDanych.BazaDanychPokemon.CH_COLUMN_NAME + "=" + ch.getName(),
                null,
                null,
                null,
                null
            );

        cursor.moveToFirst();
        ch_id = cursor.getInt(0);

        for (Stat s : ch.getStats()) {
            ContentValues skillsValues = new ContentValues();
            skillsValues.put(BazaDanych.BazaDanychPokemon.SKILL_COLUMN_CH_ID, ch_id);
            skillsValues.put(BazaDanych.BazaDanychPokemon.SKILL_COLUMN_NAME, s.getName());
            skillsValues.put(BazaDanych.BazaDanychPokemon.SKILL_COLUMN_VALUE, s.getValue());
            mDatabase.insert(BazaDanych.BazaDanychPokemon.SKILL_TABLE_NAME, null, skillsValues);
        }

        for (String s : ch.getEquipment()) {
            ContentValues eqValues = new ContentValues();
            eqValues.put(BazaDanych.BazaDanychPokemon.EQ_COLUMN_CH_ID, ch_id);
            eqValues.put(BazaDanych.BazaDanychPokemon.EQ_COLUMN_NAME, s);
            mDatabase.insert(BazaDanych.BazaDanychPokemon.EQ_TABLE_NAME, null, eqValues);
        }

//        skillsValues.put(BazaDanych)

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
        etPar.setSingleLine(true);
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
        ll.addView(et);
        numberOfLines++;

        skill.addView(ll);
    }

    public void wybierz(View view) {

        if (!validateSave(view)){
            return;
        }
        Character character = new Character();

        character.setAndId(Config.getAndroidId());
        character.setCharacterId(0); // nie zmieniaj, to jest potrzebne tylko dla serwera narazie
        character.setName(editNazwa.getText().toString());
        character.setType(editClass.getText().toString());
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
//        boolean ok = true;
        boolean ok = false;

        try {
            out = new ConnectionModule()
                .execute(Config.getApiCreateCharacter(), character.getJSON())
                .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            backToMain();
        } catch (ExecutionException e) {
            e.printStackTrace();
            backToMain();
        }

        JSONObject resultJSON = null;
        try {
            resultJSON = new JSONObject(out);
            Boolean status = resultJSON.getBoolean("Status");

            if (status) {
                Integer char_id = resultJSON.getInt("Id");

                if (char_id != null) {
                    character.setCharacterId(char_id);

                    ok = true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (ok) {
            //addToDatabase(character);

            Intent intent = new Intent(this, ActivityEkranGry.class);
            intent.putExtra("ChosenCharacter", character);
            startActivity(intent);
        } else {
            backToMain();
        }
    }

    private void backToMain() {
        Toast.makeText(this, "Something go wrong with connection!", Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, SignInActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
