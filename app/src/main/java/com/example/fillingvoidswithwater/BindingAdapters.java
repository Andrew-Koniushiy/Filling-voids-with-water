package com.example.fillingvoidswithwater;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class BindingAdapters {
    @SuppressLint("CheckResult")
    @BindingAdapter("app:maxX")
    public static void setXMax(@NonNull DrawingArea drawingArea, @NonNull ObservableInt value) {
        drawingArea.setXMax(value.get());
    }
    
    @SuppressLint("CheckResult")
    @BindingAdapter("app:maxY")
    public static void setYMax(@NonNull DrawingArea drawingArea, @NonNull ObservableInt value) {
        drawingArea.setYMax(value.get());
    }
    
    @SuppressWarnings("UseBulkOperation")
    @SuppressLint("CheckResult")
    @BindingAdapter("app:blocks")
    public static void setBlocks(@NonNull DrawingArea drawingArea, @NonNull ObservableArrayList<List<Block>> value) {
        List<List<Block>> lists = new ArrayList<>();
        for (List<Block> list : value) {
            lists.add(list);
        }
        drawingArea.setBlocks(lists);
    }
    
    @SuppressWarnings("UseBulkOperation")
    @SuppressLint("CheckResult")
    @BindingAdapter("app:waterBlocks")
    public static void setWaterBlocks(@NonNull DrawingArea drawingArea, @NonNull ObservableArrayList<Block> value) {
        List<Block> list = new ArrayList<>();
        for (Block block : value) {
            list.add(block);
        }
        drawingArea.setWaterBlocks(list);
    }
    
    @SuppressLint("CheckResult")
    @BindingAdapter("android:text")
    public static void setTextString(@NonNull TextInputEditText view, int value) {
        final String text = String.valueOf(value);
        view.setText(text);
        view.setSelection(text.length());
    }
    
    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static int getTextString(@NonNull TextInputEditText view) {
        final String value = String.valueOf(view.getText());
        return TextUtils.isEmpty(value) ? 0 : Integer.parseInt(value);
    }
}
