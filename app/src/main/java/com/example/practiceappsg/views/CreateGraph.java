package com.example.practiceappsg.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CreateGraph extends View {


    public static boolean LINE = true;

    private Paint paint;
    private int[] values;
    private String[] str;
    private String[] verticalLabels;
    private String title;
    private boolean type;
    private Context context;

    public CreateGraph(Context context, int[] values, String title, String[] str,String[] verlabels) {
        super(context);
        if (values == null)
            values = new int[0];
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
            this.verticalLabels = new String[0];
        else
            this.verticalLabels = verlabels;
        this.type = type;
        paint = new Paint();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        context=getContext();
        float border = 45;
        float horizontalStart = border * 2;
        float height = getHeight();
        float width = getWidth();
        float graphHeight = height - (2 * border);
        float graphWidth = width - (2 * border);

        paint.setTextAlign(Paint.Align.LEFT);
        int versLength = verticalLabels.length;
        for (int i = 0; i < verticalLabels.length; i++) {
            paint.setColor(Color.DKGRAY);
            float y = ((graphHeight / versLength) * i) + border;
            canvas.drawLine(horizontalStart, y, width, y, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText(verticalLabels[versLength-1-i], 0, y, paint);
        }
        int hors = values.length;
        for (int i = 0; i < str.length; i++) {
            paint.setColor(Color.DKGRAY);
            float x = ((graphWidth / hors) * i) + horizontalStart;
            canvas.drawLine(x, height - border, x, border, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            if (i==str.length)
                paint.setTextAlign(Paint.Align.RIGHT);
            if (i==0)
                paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
            canvas.drawText( str[i], x, height - 4, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, (graphWidth / 2) + horizontalStart, border - 4, paint);

            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);

                float datalength = values.length;
                float colwidth = (width - (2 * border)) / datalength;
                for (int i = 0; i < values.length; i++) {
                    float interval_h = graphHeight/versLength;
                    int multiplier = (values[i]/50);
                    float top = (interval_h*(versLength-multiplier));
                    float acc = interval_h/50;
                    acc = acc * (values[i]%50);

                    canvas.drawRect((i * colwidth) + horizontalStart, top+border-acc , ((i * colwidth) + horizontalStart) + (colwidth - 1), graphHeight+border, paint);
                }
    }

}
