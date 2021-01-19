package com.example.practiceappsg.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectToAPI {
    private String[] mon;
    private int[] stat;

    public String[] getMon() {
        return mon;
    }

    public int[] getStat() {
        return stat;
    }

    public ConnectToAPI() {
        if(mon == null)
            mon = new String[12];
        if(stat == null)
            stat = new int[12];

    }

    public void connect(){
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
