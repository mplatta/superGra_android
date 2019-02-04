package com.example.luba.supergraandroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ActivityEkranGry extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);

       // handler = new Handler();

       // handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("elo", "Miszka");

            JSONObject postData = new JSONObject();
            String out = null;
            try {
                postData.put("Id", SignInActivity.getAndroidId());
                Log.i("ZXC", postData.toString() );
                //TODO: tu w out masz stringa  jsonem, wyciągnij go jsonobject i dopiero te akcje, ale ic więcej nie potrzebujemy tutaj po prostu sprawdzamy czy jest sieć
                out = new ConnectionModule()
                            .execute(SignInActivity.getIp_url_addr(), postData.toString())
                            .get();
                Log.i("luba", out);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            handler.postDelayed(this, 500);
        }
    };
}
