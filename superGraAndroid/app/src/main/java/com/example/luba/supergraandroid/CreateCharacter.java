package com.example.luba.supergraandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateCharacter extends AppCompatActivity {
    private String[] character_calss = {"Mag", "Wojownik", "Paladyn", "Druid", "Lucznik", "Szaman", "Zabojca", "Kaplan"};
    private ArrayAdapter<String> adapter;

    private EditText edtNickName;
    private EditText edtDescription;
    private EditText autotxvClass;
    private EditText edtStatName;
    private EditText edtStatValue;
    private LayoutInflater inflater;
    private LinearLayout parentLinearLayout;


    private Integer numberID = 0;


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

        edtNickName = (EditText)findViewById(R.id.edt_NickName);
        edtDescription = (EditText)findViewById(R.id.edt_Description);
        autotxvClass = (EditText)findViewById(R.id.autotxv_Class);
        edtStatName = (EditText)findViewById(R.id.edt_StatName);
        edtStatValue = (EditText)findViewById(R.id.edt_StatValue);

    }
    //private LinearLayout parentLinearLayout;

    public void onAddField(View v) {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        EditText edt_ID = new EditText(this);
        edt_ID .setTag("numer-" + numberID);
        numberID++;
    }

    public void onSubmit(View v) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("Nick", edtNickName.getText().toString());
            postData.put("Opis", edtDescription.getText().toString());
            postData.put("Klasa", autotxvClass.getText().toString());
            postData.put("StatyNazwa", edtStatName.getText().toString());
            postData.put("StatyWartosc", edtStatValue.getText().toString());
//        String Stat = "";
//            for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
//                View v1 = parentLinearLayout.getChildAt(i);
//
//                Stat+="{"+"}"
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        numberID--;
    }
}
