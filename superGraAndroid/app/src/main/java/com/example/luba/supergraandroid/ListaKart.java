package com.example.luba.supergraandroid;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.vision.text.Line;

public class ListaKart extends AppCompatActivity {

    private LinearLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_kart);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        viewPager = (ViewPager) findViewById(R.id.vpViewPager);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //dodawanie Fragmentu
        adapter.AddFragment(new FragmentKarty(), "WSZYSTKIE KARTY");

        viewPager.setAdapter(adapter);

    }
}
