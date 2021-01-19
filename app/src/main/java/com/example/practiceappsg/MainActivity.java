package com.example.practiceappsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.Button;

import com.example.practiceappsg.network.ConnectToAPI;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String[] mon;
    private int[] stat;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mon = new String[12];
        stat = new int[12];

        ConnectToAPI conn = new ConnectToAPI();
        conn.connect();
        mon = conn.getMon();
        stat = conn.getStat();

        Bundle b = new Bundle();
        b.putStringArray("Months", mon);
        b.putIntArray("Stats", stat);

        btn = findViewById(R.id.get_graph);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GraphActivity.class);
                i.putExtras(b);
                startActivity(i);

            }
        });


    }

}