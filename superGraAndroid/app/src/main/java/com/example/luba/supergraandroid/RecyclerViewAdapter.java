package com.example.luba.supergraandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Pokemon> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Pokemon> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_karta, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.szczegoly_karty);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        viewHolder.item_pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.pokemon_imie);
                TextView dialog_description_tv = (TextView) myDialog.findViewById(R.id.pokemon_opis);
                ImageView dialog_image = (ImageView) myDialog.findViewById(R.id.pokemon_fota);
                Button buttonWybierz = (Button)myDialog.findViewById(R.id.pokemon_btn_wybierz);

                dialog_name_tv.setText(mData.get(viewHolder.getAdapterPosition()).getNazwa());
                dialog_description_tv.setText(mData.get(viewHolder.getAdapterPosition()).getOpis());
                dialog_image.setImageResource(mData.get(viewHolder.getAdapterPosition()).getFoto());
                Toast.makeText(mContext,"Test click"+String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                myDialog.show();

                buttonWybierz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ekranGry = new Intent(mContext, ActivityEkranGry.class);
                        mContext.startActivity(ekranGry);
                    }
                });
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tv_nazwa.setText(mData.get(position).getNazwa());
        holder.tv_opis.setText(mData.get(position).getOpis());
        holder.img.setImageResource(mData.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_pokemon;
        private TextView tv_nazwa;
        private TextView tv_opis;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_pokemon = (LinearLayout) itemView.findViewById(R.id.item_pokemon);
            tv_nazwa = (TextView) itemView.findViewById(R.id.tvNazwaPokemona);
            tv_opis = (TextView) itemView.findViewById(R.id.tvOpisPokemona);
            img = (ImageView) itemView.findViewById(R.id.ivPokemonImage);

        }
    }
}
