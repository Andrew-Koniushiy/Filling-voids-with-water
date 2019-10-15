package com.example.fillingvoidswithwater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;

import java.util.List;

public class DrawingArea extends View {
    
    private Paint pBorder;
    private Paint pBlock;
    private Paint pBlockBorder;
    private Paint pGrid;
    private Paint pWater;
    
    @ColorInt
    private int blockColor;
    @ColorInt
    private int blockBorderColor;
    @ColorInt
    private int waterColor;
    
    private int xMax;
    private int yMax;
    private Rect drawRect;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int unitStepX;
    private int unitStepY;
    private List<List<Block>> blocks;
    
    
    public DrawingArea(Context context) {
        super(context);
        init(null, 0);
    }
    
    public DrawingArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    
    public DrawingArea(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    
    private void init(AttributeSet attrs, @AttrRes int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DrawingArea, defStyle, 0);
        blockColor = a.getColor(R.styleable.DrawingArea_blockColor, Color.YELLOW);
        blockBorderColor = a.getColor(R.styleable.DrawingArea_blockBorderColor, Color.YELLOW);
        waterColor = a.getColor(R.styleable.DrawingArea_waterColor, Color.BLUE);
        
        xMax = a.getInt(R.styleable.DrawingArea_maxX, 0);
        yMax = a.getInt(R.styleable.DrawingArea_maxY, 0);
        
        a.recycle();
        
        pBorder = newPaint(Color.BLACK, Paint.Style.STROKE);
        pBorder.setStrokeWidth(4f);
        
        pGrid = newPaint(Color.GRAY, Paint.Style.STROKE);
        pGrid.setStrokeWidth(1f);
        
        pBlock = newPaint(blockColor, Paint.Style.FILL);
        
        pBlockBorder = newPaint(blockBorderColor, Paint.Style.STROKE);
        pBlockBorder.setStrokeWidth(2f);
        
        pWater = newPaint(waterColor, Paint.Style.FILL);
        
    }
    
    private Paint newPaint(@ColorInt int color, Paint.Style style) {
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(style);
        paint.setColor(color);
        return paint;
    }
    
    public void setBlocks(List<List<Block>> blocks) {
        this.blocks = blocks;
        refresh();
    }
    
    public void setXMax(int xMax) {
        this.xMax = xMax;
        refresh();
    }
    
    public void setYMax(int yMax) {
        this.yMax = yMax;
        refresh();
    }
    
    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            paddingLeft = getPaddingLeft();
            paddingTop = getPaddingTop();
            paddingRight = getPaddingRight();
            paddingBottom = getPaddingBottom();
            int width = right - left;
            int height = bottom - top;
            refreshInner(width, height);
        }
    }
    
    private void refreshInner(int width, int height) {
        drawRect = new Rect(paddingLeft, paddingTop, width - paddingRight, height - paddingBottom);
        unitStepX = (int) (drawRect.width() * 1.0f / xMax);
        unitStepY = (int) (drawRect.height() * 1.0f / yMax);
    }
    
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.drawRect(drawRect, pBorder);
        canvas.translate(paddingLeft, paddingTop);
        canvas.scale(1, -1, drawRect.width() / 2, drawRect.height() / 2);
        drawGrid(canvas);
        
        for (List<Block> innerBlocks : blocks) {
            for (Block block : innerBlocks) {
                block.draw(canvas, unitStepX, unitStepY, pBlock, pBlockBorder);
            }
        }
        
    }
    
    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < drawRect.width(); i += unitStepX) {
            canvas.drawLine(i, 0, i, drawRect.height(), pGrid);
        }
        for (int i = 0; i < drawRect.height(); i += unitStepY) {
            canvas.drawLine(0, i, drawRect.width(), i, pGrid);
        }
        
    }
    
    public void refresh() {
        refreshInner(getWidth(), getHeight());
        postInvalidate();
    }
    
}
