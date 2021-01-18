package com.example.practiceappsg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class CreateGraph extends View {


    public static boolean LINE = true;

    private Paint paint;
    private float[] values;
    private String[] str;
    private String[] verlabels;
    private String title;
    private boolean type;
    Context context;

    public CreateGraph(Context context, float[] values, String title, String[] str,String[] verlabels) {
        super(context);
        if (values == null)
            values = new float[0];
        else
            this.values = values;
        if (title == null)
            title = "";
        else
            this.title = title;
        if (str == null)
            this.str = new String[0];
        else
            this.str = str;
        if (verlabels == null)
            this.verlabels = new String[0];
        else
            this.verlabels = verlabels;
        this.type = type;
        paint = new Paint();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        context=getContext();
        float border = 50;
        float horstart = border * 2;
        float height = getHeight();
        float width = getWidth();
        float max = getMax();
        Log.w("max", ""+max);
        float min = getMin();
        Log.w("min", ""+min);
        float diff = max - min;
        float graphheight = height - (2 * border);
        float graphwidth = width - (2 * border);

        paint.setTextAlign(Paint.Align.LEFT);
        int vers = verlabels.length;
        for (int i = 0; i < verlabels.length; i++) {
            paint.setColor(Color.DKGRAY);
            float y = ((graphheight / vers) * i) + border;
            canvas.drawLine(horstart, y, width, y, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(10);
            canvas.drawText(verlabels[i], 0, y, paint);
        }
        int hors = values.length;
        for (int i = 0; i < str.length; i++) {
            paint.setColor(Color.DKGRAY);
            float x = ((graphwidth / hors) * i) + horstart;
            canvas.drawLine(x, height - border, x, border, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            if (i==str.length)
                paint.setTextAlign(Paint.Align.RIGHT);
            if (i==0)
                paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.WHITE);
            paint.setTextSize(9);
            canvas.drawText( str[i], x, height - 4, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);



        if (max != min) {
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);

                float datalength = values.length;
                float colwidth = (width - (2 * border)) / datalength;
                for (int i = 0; i < values.length; i++) {
                    //  float val = values[i] - min;

                    //  float rat = val / diff;
                    //  float h = graphheight * rat;
                    //  canvas.drawRect((i * colwidth) + horstart, (border - h) + graphheight, ((i * colwidth) + horstart) + (colwidth - 1), height - (border - 1), paint);
                    float graph_h = getHeight()-(border*2);
                    // Log.e("", "graph_h > "+graph_h);

                    float ind_h = graph_h/7;
                    //Log.e("", "ind_h > "+ind_h);

                    float t = values[i]/5;

                    float top = (graph_h - ind_h*(t));
                    // Log.e("", " > "+i+1);
                    // Log.e("", "top > "+top);

                    //for values between 0 and 5 ,vice versa
                    //Log.e("", " values[i] > "+values[i]);
                    float acc = ind_h/5;
                    acc = acc * (values[i]%5);

                    //  Log.e("", " acc > "+acc);

                    canvas.drawRect((i * colwidth) + horstart, top+border-acc , ((i * colwidth) + horstart) + (colwidth - 1), graph_h+border, paint);
                }
        }
    }



    private float getMax() {
        float largest = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] > largest)
                largest = values[i];
        return largest;
    }

    private float getMin() {
        float smallest = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] < smallest)
                smallest = values[i];
        return smallest;
    }
}
