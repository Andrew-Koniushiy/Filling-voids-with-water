package com.example.fillingvoidswithwater;

import androidx.annotation.NonNull;
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
    private ObservableArrayList<Block> waterBlocks = new ObservableArrayList<>();
    
    public DataViewModel() {
        generateBlocks();
        generateWaterBlocks();
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
    
    public ObservableArrayList<Block> getWaterBlocks() {
        return waterBlocks;
    }
    
    void generateBlocks() {
        int xMax = this.xMax.get();
        int yMax = this.yMax.get();
        List<List<Block>> blocks = new ArrayList<>();
        int x0 = 0;
        while (x0 < xMax) {
            List<Block> innerBlocks = new ArrayList<>();
            int x1 = x0 + getRandomIntInRange(1, 3);
            if (yMax != 0) {
                int y0 = 0;
                while (y0 < yMax) {
                    final int max = yMax / getRandomIntInRange(1, 2);
                    int y1 = y0 + getRandomIntInRange(1, max > 1 ? max : 2);
                    if (y1 <= yMax && x1 <= xMax) {
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
        waterBlocks.clear();
        lists.clear();
        lists.addAll(blocks);
    }
    
    void generateWaterBlocks() {
        List<Block> mountainBlocks = getColumns();
        List<Block> waterBlocks = new ArrayList<>();
        
        for (int i = 0; i < mountainBlocks.size(); i++) {
            Block column = mountainBlocks.get(i);
            int waterY = column.getYMax();
            int nextBiggerColumnIndex;
            while ((nextBiggerColumnIndex = findNextBiggerColumnIndex(mountainBlocks, i, waterY)) == -1) {
                waterY--;
                if (waterY == 0) {
                    break;
                }
            }
            if (nextBiggerColumnIndex > 0) {
                for (int j = i + 1; j < nextBiggerColumnIndex; j++) {
                    Block block = mountainBlocks.get(j);
                    waterBlocks.add(new Block(block.getXMin(), block.getYMax(), block.getXMax(), waterY));
                }
                i = nextBiggerColumnIndex - 1;
            }
        }
        this.waterBlocks.addAll(waterBlocks);
    }
    
    private int findNextBiggerColumnIndex(@NonNull List<Block> mountainBlocks, int i, int columnYMax) {
        for (int j = i + 1; j < mountainBlocks.size(); j++) {
            Block nextColumn = mountainBlocks.get(j);
            if (columnYMax <= nextColumn.getYMax()) {
                return j;
            }
        }
        return -1;
    }
    
    private List<Block> getColumns() {
        List<Block> columns = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            List<Block> yBlocks = lists.get(i);
            int YMaxHeight = getYMaxInColumn(yBlocks);
            if (YMaxHeight > 0 && !yBlocks.isEmpty()) {
                Block block = yBlocks.get(0);
                columns.add(new Block(block.getXMin(), 0, block.getXMax(), YMaxHeight));
            }
        }
        return columns;
    }
    
    private int getYMaxInColumn(@NonNull List<Block> yBlocks) {
        int max = 0;
        for (int j = 0; j < yBlocks.size(); j++) {
            Block block = yBlocks.get(j);
            if (max < block.getYMax()) {
                max = block.getYMax();
            }
        }
        return max;
    }
}
