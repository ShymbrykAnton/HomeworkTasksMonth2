package oophomework.memory;

import java.util.Arrays;
import java.util.Objects;

import static oophomework.utils.Constants.Text.emptyMemoryCell;
import static oophomework.utils.Constants.Text.memoryInfo;


public class Memory {
    private String[] memoryCell;

    public Memory(String[] memoryCell) {
        if (memoryCell == null) {
            throw new IllegalArgumentException("Память не может быть null!");
        }
        this.memoryCell = memoryCell;
    }

    @Override
    public String toString() {
        return Arrays.toString(memoryCell);
    }

    public String readLast() {
        for (int count = memoryCell.length - 1; count > 0; count--) {
            if (memoryCell[count] != null) {
                return memoryCell[count];
            }
        }
        return emptyMemoryCell;
    }

    public String removeLast() {
        for (int count = memoryCell.length - 1; count > 0; count--) {
            if (memoryCell[count] != null) {
                memoryCell[count] = null;
                return toString();
            }
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

    public MemoryInfo getMemoryInfo() {
        int availableCells = 0;
        for (String cell : memoryCell) {
            if (cell == null) {
                availableCells++;
            }
        }
        double occupiedMemoryCells = (((memoryCell.length - (double) (availableCells)) / memoryCell.length) * 100);
        return new MemoryInfo(availableCells, occupiedMemoryCells);
    }

    public String[] getMemoryCell() {
        return memoryCell;
    }

    public void setMemoryCell(String[] memoryCell) {
        this.memoryCell = memoryCell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Memory memory = (Memory) o;
        return Arrays.equals(memoryCell, memory.memoryCell);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(memoryCell);
    }
}
