package com.example.luba.supergraandroid;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaKart extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_kart);

        tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        viewPager = (ViewPager) findViewById(R.id.vpViewPager);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //dodawanie Fragmentu
        adapter.AddFragment(new FragmentKarty(), "WSZYSTKIE KARTY");
        adapter.AddFragment(new FragmentPostac(), "MOJA KARTA");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_star);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_lista);
    }
}
