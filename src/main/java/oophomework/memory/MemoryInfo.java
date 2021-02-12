package oophomework.memory;

import java.util.Objects;

import static oophomework.utils.Constants.Text.memoryInfo;

public class MemoryInfo {
    private final int numberOfAvailableCells;
    private final double occupiedMemorySpace;

    public MemoryInfo(int numberOfAvailableSlots, double occupiedMemorySpace) {
        this.numberOfAvailableCells = numberOfAvailableSlots;
        this.occupiedMemorySpace = occupiedMemorySpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemoryInfo that = (MemoryInfo) o;
        return numberOfAvailableCells == that.numberOfAvailableCells && Double.compare(that.occupiedMemorySpace, occupiedMemorySpace) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfAvailableCells, occupiedMemorySpace);
    }

    @Override
    public String toString() {
        return String.format(memoryInfo, numberOfAvailableCells, occupiedMemorySpace);
    }

    public double getOccupiedMemorySpace() {
        return occupiedMemorySpace;
    }

}
