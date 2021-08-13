package com.kplo.scheduler.decorators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;

public class CustomMultipleDotSpan implements LineBackgroundSpan {
    private float radius;
    private int color;

    public CustomMultipleDotSpan() {
        this.radius = 3.0F;
        this.color = 0;
    }

    public CustomMultipleDotSpan(int color) {
        this.radius = 3.0F;
        this.color = 0;
    }

    public CustomMultipleDotSpan(float radius) {
        this.radius = radius;
        this.color = 0;
    }

    public CustomMultipleDotSpan(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }


    @Override
    public void drawBackground(@NonNull Canvas canvas, @NonNull Paint paint, int left, int right, int top, int baseline, int bottom,
                               @NonNull CharSequence text,
                               int start, int end, int lineNumber) {
        int total = 12 > 2 ? 3 : 12;
        int leftMost = (total - 1) * -12;

        for (int i = 0; i < total; i++) {
            int oldColor = paint.getColor();
            if (this.color != 0) {
                paint.setColor(this.color);

                canvas.drawCircle((float) ((left + right) / 2 - leftMost), (float) bottom + this.radius, this.radius, paint);
                paint.setColor(oldColor);
                leftMost += 24;
            }
        }
    }
}