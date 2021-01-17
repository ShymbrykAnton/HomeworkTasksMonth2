package oopHomework.processor;

import oopHomework.architecture.ProcessorArm;

import static oopHomework.Utils.Constants.Text.processorInfo;

public abstract class Processor {
    double frequency;
    double cache;
    int bitCapacity;

    public Processor(double frequency, double cache, int bitCapacity) {
        this.frequency = frequency;
        this.cache = cache;
        this.bitCapacity = bitCapacity;
    }

    public String getDetails() {

        return String.format(processorInfo, frequency, cache, bitCapacity);
    }

    public abstract String dataProcess(String data);

    public abstract String dataProcess(long data);

}
