package com.example.fillingvoidswithwater;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import java.util.List;
import java.util.Random;

public class CommonUtils {
    
    static int getRandomIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    public static int getBlockCount(@NonNull ObservableArrayList<List<Block>> blocks) {
        int count = 0;
        for (List<Block> innerBlocks : blocks) {
            count += innerBlocks.size();
        }
        return count;
    }
}
