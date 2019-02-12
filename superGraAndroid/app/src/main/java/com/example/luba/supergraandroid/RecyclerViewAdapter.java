package com.example.luba.supergraandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context cx;
    private Cursor cursor;
    private List<Character> characters;
    private SQLiteDatabase mDatabase;

    private Dialog myDialog;

    public RecyclerViewAdapter(Context cx, Cursor cursor) {
        this.cx = cx;
        this.cursor = cursor;

        characters = new ArrayList<>();

        BazaDanychHelper dbHelper = new BazaDanychHelper(cx);
        mDatabase = dbHelper.getWritableDatabase();
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cx);
        View view = inflater.inflate(R.layout.item_karta, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        myDialog = new Dialog(cx);
        myDialog.setContentView(R.layout.szczegoly_karty);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder.item_pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.pokemon_imie);
                TextView dialog_description_tv = (TextView) myDialog.findViewById(R.id.pokemon_opis);
                ImageView dialog_image = (ImageView) myDialog.findViewById(R.id.pokemon_fota);
                LinearLayout skills = (LinearLayout) myDialog.findViewById(R.id.ll_skills_more);
                Button buttonWybierz = (Button)myDialog.findViewById(R.id.pokemon_btn_wybierz);

                dialog_name_tv.setText(characters.get(viewHolder.getAdapterPosition()).getName());
                dialog_description_tv.setText(characters.get(viewHolder.getAdapterPosition()).getDescription());
//                dialog_image.setImageResource(mData.get(viewHolder.getAdapterPosition()).getFoto());
//                Toast.makeText(mContext,"Test click"+String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                int count = 0;

                int _i = viewHolder.getAdapterPosition();

                Character _ch = characters.get(_i);
                setMoreInfo(_ch);

                showStats(_ch.getStats(), skills);

                final Character ch = _ch;


                myDialog.show();
                Log.d("ASDASD", characters.get(viewHolder.getAdapterPosition()).getJSON());
                buttonWybierz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startGame(characters.get(viewHolder.getAdapterPosition()));
                    }
                });
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) return;

        Character ch = new Character();

        Integer id = cursor.getInt(cursor.getColumnIndex(BazaDanych.BazaDanychPokemon.CH_COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(BazaDanych.BazaDanychPokemon.CH_COLUMN_NAME));
        String desc = cursor.getString(cursor.getColumnIndex(BazaDanych.BazaDanychPokemon.CH_COLUMN_DESCRIPTION));
        String type = cursor.getString(cursor.getColumnIndex(BazaDanych.BazaDanychPokemon.CH_COLUMN_CLASS));


        ch.setAndId(Config.getAndroidId());
        ch.setCharacterId(id);
        ch.setName(name);
        ch.setDescription(desc);
        ch.setType(type);

        characters.add(ch);

        holder.tv_opis.setText(desc);
        holder.tv_nazwa.setText(name);
        holder.img.setImageResource(R.drawable.diglett254);
    }

    private void setMoreInfo(Character ch) {
        Cursor c = mDatabase.rawQuery("SELECT " + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_NAME + ", " +
                BazaDanych.BazaDanychPokemon.SKILL_COLUMN_VALUE + " FROM " +
                BazaDanych.BazaDanychPokemon.SKILL_TABLE_NAME + " WHERE " +
                BazaDanych.BazaDanychPokemon.SKILL_COLUMN_CH_ID + "='" + ch.getCharacterId().toString() + "'", null);

        ArrayList<Stat> stats = new ArrayList<>();

        while (c.moveToNext()) {
            stats.add(new Stat(c.getString(0), c.getInt(1)));
        }

        ch.setStats(stats);

        c = mDatabase.rawQuery("SELECT " + BazaDanych.BazaDanychPokemon.EQ_COLUMN_NAME + " FROM " +
                BazaDanych.BazaDanychPokemon.EQ_TABLE_NAME + " WHERE " +
                BazaDanych.BazaDanychPokemon.EQ_COLUMN_CH_ID + "='" + ch.getCharacterId().toString() + "'", null);

        ArrayList<String> eq = new ArrayList<>();

        while (c.moveToNext()) {
            eq.add(c.getString(0));
        }

        ch.setEquipment(eq);

        c.close();
    }

    private void showStats(ArrayList<Stat> stats, LinearLayout llSkills) {
        int numberOfLines = 300;
        llSkills.removeAllViews();

        for(Stat s : stats) {
            LinearLayout ll = new LinearLayout(cx);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            ll.setOrientation(LinearLayout.HORIZONTAL);
            EditText etPar = new EditText(cx);
            etPar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            etPar.setSingleLine(true);
            etPar.setText(s.getName() + ": ");
            etPar.setInputType(InputType.TYPE_NULL);
            etPar.setId(numberOfLines);
            ll.addView(etPar);
            numberOfLines++;

            EditText et = new EditText(cx);
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

    private void backToMain() {
        Toast.makeText(cx, "Something go wrong with connection!", Toast.LENGTH_LONG).show();

        Intent i = new Intent(cx, SignInActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        cx.startActivity(i);
    }

    private void startGame(Character character) {
        String out = null;
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
            Intent ekranGry = new Intent(cx, ActivityEkranGry.class);
            ekranGry.putExtra("ChosenCharacter", character);
            cx.startActivity(ekranGry);
        } else {
            backToMain();
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    void  swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_pokemon;
        private TextView tv_nazwa;
        private TextView tv_opis;
        private ImageView img;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_pokemon = (LinearLayout) itemView.findViewById(R.id.item_pokemon);
            tv_nazwa = (TextView) itemView.findViewById(R.id.tvNazwaPokemona);
            tv_opis = (TextView) itemView.findViewById(R.id.tvOpisPokemona);
            img = (ImageView) itemView.findViewById(R.id.ivPokemonImage);

        }
    }
}
