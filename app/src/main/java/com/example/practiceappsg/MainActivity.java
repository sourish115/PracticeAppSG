package com.example.practiceappsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.HttpAuthHandler;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url("https://demo5636362.mockable.io/stats").build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Response", "Not Connected");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String Jsondata = response.body().string();
                try {
                    JSONObject Jobj = new JSONObject(Jsondata);
                    JSONArray jArr = Jobj.getJSONArray("data");
                    int lim = jArr.length();
                    for(int i=0;i<lim;i++){
                        JSONObject tObj = jArr.getJSONObject(i);
                        mon[i] = tObj.getString("month");
                        stat[i] = Integer.parseInt(tObj.getString("stat"));
                        Log.d("JSON DATA", mon + " ## " + stat);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}