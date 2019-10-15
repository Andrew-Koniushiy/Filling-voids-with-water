package com.example.fillingvoidswithwater;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.fillingvoidswithwater.CommonUtils.getRandomIntInRange;

public class DataViewModel extends ViewModel {
    private ObservableInt xMax = new ObservableInt(25);
    private ObservableInt yMax = new ObservableInt(15);
    private ObservableArrayList<List<Block>> lists = new ObservableArrayList<>();
    
    public DataViewModel() {
        generateBlocks();
    }
    
    public ObservableInt getXMax() {
        return xMax;
    }
    
    public ObservableInt getYMax() {
        return yMax;
    }
    
    public ObservableArrayList<List<Block>> getBlocks() {
        return lists;
    }
    
    void generateBlocks() {
        int xMax = this.xMax.get();
        int yMax = this.yMax.get();
        List<List<Block>> blocks = new ArrayList<>();
        int x0 = 0;
        while (x0 < xMax) {
            List<Block> innerBlocks = new ArrayList<>();
            int x1 = x0 + getRandomIntInRange(1, 3);
            if (yMax == 0) {
                innerBlocks.add(new Block(x0, 0, x1, 0));
            } else {
                int y0 = 0;
                while (y0 < yMax) {
                    final int max = yMax / getRandomIntInRange(1, 2);
                    int y1 = y0 + getRandomIntInRange(1, max > 1 ? max : 2);
                    if (y1 < yMax && x1 <= xMax) {
                        innerBlocks.add(new Block(x0, y0, x1, y1));
                    }
                    y0 = y1;
                }
                
            }
            if (!innerBlocks.isEmpty()) {
                blocks.add(innerBlocks);
            }
            
            
            x0 = x1;
            
        }
        lists.clear();
        lists.addAll(blocks);
    }
}
