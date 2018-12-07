package com.example.cjcu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button bt_getWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void getWebDataClick(View view) {
        Log.i("Capoo","hello");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("http://odata.tn.edu.tw/ebookapi/api/getOdataJH/?level=all");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(15000);
                    conn.connect();

                    BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = bfr.readLine()) != null){
                        sb.append(line);
                    }
                    Log.i("Capoo",sb.toString());

                    JSONArray ja = new JSONArray(sb.toString());
                    JSONObject jo = ja.getJSONObject(0);
                    Log.i("Capoo",ja.get(0).toString());
                    Log.i("Capoo",jo.getString("school"));
                }
                catch (Exception e){
                    Log.i("Capoo",e.toString());
                }
            }
        });
        thread.start();
    }

    public void init(){
        bt_getWeb = (Button)findViewById(R.id.bt_WebClick);
    }
}
