package com.example.practiceappsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float[] values = new float[] { 17, 21, 15, 23};
        String[] verlabels = new String[] { "10", "15", "20", "25" };
        String[] horlabels = new String[] { "Jan", "Feb", "Mar", "Apr" };
        CreateGraph graphView = new CreateGraph(this,values,"GraphView",verlabels,horlabels);
        setContentView(graphView);
    }
}