package oopHomework.memory;

import static oopHomework.Utils.Constants.Text.emptyMemoryCell;
import static oopHomework.Utils.Constants.Text.memoryInfo;


public class Memory {
    public static String[] memoryCell;

    public Memory(String[] memoryCell) {
        Memory.memoryCell = memoryCell;
    }

    public String readLast() {
        if (memoryCell[memoryCell.length - 1] != null) {
            return memoryCell[memoryCell.length - 1];
        } else {
            return emptyMemoryCell;
        }
    }

    public String removeLast() {
        if (memoryCell[memoryCell.length - 1] != null) {
            memoryCell[memoryCell.length - 1] = null;
        }
        return emptyMemoryCell;
    }

    public boolean save() {
        for (int i = memoryCell.length - 1; i >= 0; i--) {
            if (memoryCell[i] == null) {
                memoryCell[i] = "1";
                return true;
            }
        }
        return false;
    }

    public static class MemoryInfo{
        int numberOfAvailableCells;
        double occupiedMemorySpace;

        public MemoryInfo(int numberOfAvailableSlots, double occupiedMemorySpace) {
            this.numberOfAvailableCells = numberOfAvailableSlots;
            this.occupiedMemorySpace = occupiedMemorySpace;
        }

        @Override
        public String toString() {
            return String.format(memoryInfo, numberOfAvailableCells, occupiedMemorySpace);
        }
    }

    public MemoryInfo getMemoryInfo() {
        int availableCells = 0;
        for (String cell : memoryCell) {
            if (cell != null) {
                availableCells++;
            }
        }
        double occupiedMemoryCells = (((memoryCell.length - (double)(availableCells)) / memoryCell.length)*100);
        return new MemoryInfo(availableCells, occupiedMemoryCells);
    }
}
