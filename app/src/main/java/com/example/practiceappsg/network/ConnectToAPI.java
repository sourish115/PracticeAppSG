package com.example.practiceappsg.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.practiceappsg.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class ConnectToAPI {
    private String[] mon;
    private int[] stat;
    private Context context;

    public String[] getMon() {
        return mon;
    }

    public int[] getStat() {
        return stat;
    }

    public ConnectToAPI(Context context) {
        if(mon == null)
            mon = new String[12];
        if(stat == null)
            stat = new int[12];
        this.context=context;

    }

    public void connect(){
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url("https://demo5636362.mockable.io/stats").build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Response", "Not Connected");
                offlineData();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String Jsondata = response.body().string();
                create(context, "storage.json", Jsondata);
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
    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private boolean create(Context context, String fileName, String jsonString){
        String FILENAME = "storage.json";
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    private void offlineData(){
        String Jsondata = read(context,"storage.json");
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

}
