package com.example.luba.supergraandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActivityEkranGry extends AppCompatActivity {

    private Handler handler;
    private Character character;
    private TextView tvNazwa;
    private TextView tvOpis;
    private TextView tvKlasa;
    private ImageView ivFota;
    private LinearLayout llSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);

        character = (Character)getIntent().getSerializableExtra("ChosenCharacter");

        ivFota = (ImageView)findViewById(R.id.gra_pokemon_fota);

        tvNazwa = (TextView)findViewById(R.id.gra_pokemon_imie);
        tvOpis = (TextView)findViewById(R.id.gra_pokemon_opis);
        tvKlasa = (TextView)findViewById(R.id.gra_klasa);
        llSkills = (LinearLayout)findViewById(R.id.ll_skills);

        tvNazwa.setText(character.getName());
        tvOpis.setText(character.getDescription());
        tvKlasa.setText(character.getType());

        if (character.getFoto()!=0){
            ivFota.setImageResource(character.getFoto());
        }

        showSkills();

        handler = new Handler();

        handler.post(runnable);
    }

    public void showSkills () {

        ArrayList<Stat> stats = character.getStats();

        for(int i=0; i<stats.size(); i++){

            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,1));

            ll.setOrientation(LinearLayout.HORIZONTAL);
            TextView etPar = new TextView(this);
            LinearLayout.LayoutParams layoutP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            layoutP.setMargins(10,10,10,10);
            etPar.setLayoutParams(layoutP);
            etPar.setText(stats.get(i).getName().toString());
            etPar.setTextColor(Color.WHITE);
            etPar.setTextSize(20);
            ll.addView(etPar);

            TextView et = new TextView(this);
            LinearLayout.LayoutParams layoutV = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            layoutV.setMargins(10,10,10,10);
            et.setLayoutParams(layoutV);
            et.setText(stats.get(i).getValue().toString());
            et.setTextColor(Color.parseColor("#d81b60"));
            et.setTextSize(20);
            ll.addView(et);

            llSkills.addView(ll);

        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            JSONObject postData = new JSONObject();
            String out = null;
            try {
                postData.put("Id", Config.getAndroidId());

                out = new ConnectionModule()
                            .execute(Config.getApiGetQueue(), postData.toString())
                            .get();

            } catch (JSONException e) {
                e.printStackTrace();
                backToMain();
            } catch (InterruptedException e) {
                e.printStackTrace();
                backToMain();
            } catch (ExecutionException e) {
                e.printStackTrace();
                backToMain();
            }

            if (out != null) {
                try {
                    JSONObject outJSON = new JSONObject(out);
                    reactionForAction(outJSON);
                } catch (JSONException e) {
                    e.printStackTrace();
                    backToMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    backToMain();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    backToMain();
                }
            }

            handler.postDelayed(this, 500);
        }
    };

    private void showSkils(ArrayList<Stat> stats) {
        int numberOfLines = 100;

        for(Stat s : stats) {
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            ll.setOrientation(LinearLayout.HORIZONTAL);
            EditText etPar = new EditText(this);
            etPar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            etPar.setSingleLine(true);
            etPar.setText(s.getName() + ": ");
            etPar.setInputType(InputType.TYPE_NULL);
            etPar.setId(numberOfLines);
            ll.addView(etPar);
            numberOfLines++;

            EditText et = new EditText(this);
            et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            et.setSingleLine(true);
            et.setText(s.getValue().toString());
            et.setInputType(InputType.TYPE_NULL);
            et.setId(numberOfLines);
            ll.addView(et);
            numberOfLines++;

            llSkills.addView(ll);
        }
    }

    private Character characterObjectFromJSONObject(JSONObject jsonObject) throws JSONException {
        Character ch = new Character();

        ch.setDescription(jsonObject.getString("Description"));
        ch.setType(jsonObject.getString("Class"));

        JSONArray skills = jsonObject.getJSONArray("Stats");

        for (int i = 0; i < skills.length(); i++) {
            JSONObject skill = skills.getJSONObject(i);
            ch.getStats().add(new Stat(skill.getString("Name"), skill.getInt("Value")));
        }

        JSONArray eq = jsonObject.getJSONArray("Equipment");

        for (int i = 0; i < eq.length(); i++) {
            ch.getEquipment().add(eq.getString(i));
        }

        return ch;
    }

    private void updateView(Integer id) throws ExecutionException, InterruptedException, JSONException {
        Character ch;

        Log.d("ASDASD2", Config.getApiGetCharacter() + id.toString());

        String out = null;
        out = new GetRequest()
                .execute(Config.getApiGetCharacter() + id.toString())
                .get();

        Log.d("ASDASD2", out);

        JSONObject jsonObject = new JSONObject(out);

        ch = characterObjectFromJSONObject(jsonObject);

        tvOpis.setText(ch.getDescription());
        // update klasy trzeba dodać

        llSkills.removeAllViews();
        showSkils(ch.getStats());

        character.setDescription(ch.getDescription());
        character.setCharacterId(id);
        character.setType(ch.getType());
        character.setStats(ch.getStats());
        character.setEquipment(ch.getEquipment());
    }

    private void reactionForAction(JSONObject out) throws JSONException, ExecutionException, InterruptedException {
        Integer action = out.getInt("Action");

        switch (action) {
            case 3:
                Log.d("ASDASD", out.toString());
                Integer id = out.getInt("CharacterId");
                updateView(id);
                break;
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
