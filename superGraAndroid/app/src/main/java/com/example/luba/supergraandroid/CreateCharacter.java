package com.example.luba.supergraandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


public class CreateCharacter extends AppCompatActivity {
    private String[] character_calss = {"Mag", "Wojownik", "Paladyn", "Druid", "Lucznik", "Szaman", "Zabojca", "Kaplan"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        parentLinearLayout = (LinearLayout) findViewById(R.id.linear_Stats);
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, character_calss);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autotxv_Class);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
    }
    private LinearLayout parentLinearLayout;

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }
    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }
}
