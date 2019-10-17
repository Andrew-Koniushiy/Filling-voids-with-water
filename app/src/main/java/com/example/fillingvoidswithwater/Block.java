package com.example.fillingvoidswithwater;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Block {
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    
    Block(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }
    
    int getXMin() {
        return xMin;
    }
    
    int getYMin() {
        return yMin;
    }
    
    int getXMax() {
        return xMax;
    }
    
    int getYMax() {
        return yMax;
    }
    
    void draw(@NonNull Canvas canvas, int unitStepX, int unitStepY, Paint paint, Paint borderPaint) {
        Rect rect = new Rect(xMin * unitStepX, yMin * unitStepY, xMax * unitStepX, yMax * unitStepY);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect, borderPaint);
    }
}