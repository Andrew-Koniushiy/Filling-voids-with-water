package com.example.fillingvoidswithwater;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;

public class Block {
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    
    Block(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }
    
    void draw(@NonNull Canvas canvas, int unitStepX, int unitStepY, Paint paint, Paint borderPaint) {
        Rect rect = new Rect(x0 * unitStepX, y0 * unitStepY, x1 * unitStepX, y1 * unitStepY);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect, borderPaint);
    }
}