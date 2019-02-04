package com.example.luba.supergraandroid;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class KursorAdapter extends RecyclerView.Adapter<KursorAdapter.BazDanychVievHolder> {

    private Context mContext;
    private Cursor mCursor;

    public KursorAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class BazDanychVievHolder extends RecyclerView.ViewHolder{

        public TextView tvNazwa;
        public TextView tvSila;

        public BazDanychVievHolder(@NonNull View itemView) {
            super(itemView);
            tvNazwa = itemView.findViewById(R.id.textView_probaNazwa);
            tvSila = itemView.findViewById(R.id.textView_probaSila);
        }
    }

    @NonNull
    @Override
    public BazDanychVievHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.proba_bazy_item, viewGroup, false);
        return new BazDanychVievHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazDanychVievHolder bazDanychVievHolder, int i) {

        if(!mCursor.moveToPosition(i)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(BazaDanych.BazaDanychPokemon.COLUMN_NAZWA));
        int sila = mCursor.getInt(mCursor.getColumnIndex(BazaDanych.BazaDanychPokemon.COLUMN_SILA));

        bazDanychVievHolder.tvNazwa.setText(name);
        bazDanychVievHolder.tvSila.setText(String.valueOf(sila));

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor!=null){
            notifyDataSetChanged();
        }

    }
}
