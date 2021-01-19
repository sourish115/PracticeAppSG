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
        float border = 35;
        float horstart = border * 2;
        float height = getHeight();
        float width = getWidth();
        float graphheight = height - (2 * border);
        float graphwidth = width - (2 * border);

        paint.setTextAlign(Paint.Align.LEFT);
        int vers = verlabels.length;
        for (int i = 0; i < verlabels.length; i++) {
            paint.setColor(Color.DKGRAY);
            float y = ((graphheight / vers) * i) + border;
            canvas.drawLine(horstart, y, width, y, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText(verlabels[vers-1-i], 0, y, paint);
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
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText( str[i], x, height - 4, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);




            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);

                float datalength = values.length;
                float colwidth = (width - (2 * border)) / datalength;
                for (int i = 0; i < values.length; i++) {
                    float interval_h = graphheight/vers;
                    int multiplier = (int) (values[i]/5);
                    float top = (interval_h*(vers-multiplier));
                    float acc = interval_h/5;
                    acc = acc * (values[i]%5);

                    canvas.drawRect((i * colwidth) + horstart, top+border-acc , ((i * colwidth) + horstart) + (colwidth - 1), graphheight+border, paint);
                }

    }

}
