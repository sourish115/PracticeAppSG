package com.example.practiceappsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getIntent().getExtras();

        int[] values = b.getIntArray("Stats");
        Log.e("FEB","is "+values[1]);
        String[] horlabels = b.getStringArray("Months");
        String[] verlabels = new String[]{"50","100","150","200","250","300","350"};
        //float[] values = new float[] { 23, 45, 78, 4, 80, 99, 234, 34, 109, 88, 100, 301};
        //String[] verlabels = new String[] { "5", "10", "15", "20" };
        //String[] horlabels = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep","Oct","Nov","Dec" };
        CreateGraph graphView = new CreateGraph(this,values,"GraphView",horlabels,verlabels);
        setContentView(graphView);
    }
}