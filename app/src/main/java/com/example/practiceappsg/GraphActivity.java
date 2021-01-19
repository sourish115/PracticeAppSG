package com.example.practiceappsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float[] values = new float[] { 7, 10, 15, 17, 9, 4, 13, 5, 8, 19, 11, 12};
        String[] verlabels = new String[] { "5", "10", "15", "20" };
        String[] horlabels = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep","Oct","Nov","Dec" };
        CreateGraph graphView = new CreateGraph(this,values,"GraphView",horlabels,verlabels);
        setContentView(graphView);
    }
}