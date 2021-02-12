package oophomework.processors.base;

import java.util.Objects;

import static oophomework.utils.Constants.Text.processorInfo;

public abstract class ProcessorBase {
    private final double frequency;
    private final double cache;
    private final int bitCapacity;

    public double getFrequency() {
        return frequency;
    }

    public double getCache() {
        return cache;
    }

    public int getBitCapacity() {
        return bitCapacity;
    }

    public ProcessorBase(double frequency, double cache, int bitCapacity) {
        this.frequency = frequency;
        this.cache = cache;
        this.bitCapacity = bitCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency,cache,bitCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorBase processor = (ProcessorBase) o;
        return frequency == processor.frequency &&
                cache == processor.cache &&
                bitCapacity == processor.bitCapacity;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public String getDetails() {
        return String.format(processorInfo, frequency, cache, bitCapacity, getArchitecture());
    }

    public abstract String getArchitecture();

    public abstract String dataProcess(String data);

    public abstract String dataProcess(long data);

}
