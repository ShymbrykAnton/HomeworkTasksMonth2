package oopHomework.architecture;

import oopHomework.processor.Processor;

import java.util.Locale;

import static oopHomework.Utils.Constants.Text.useProcessorOnArchitecture;

public class ProcessorArm extends Processor {
    final String architecture = "ARM";

    public ProcessorArm(double frequency, double cache, int bitCapacity) {
        super(frequency, cache, bitCapacity);
    }

    @Override
    public String dataProcess(String data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }

    @Override
    public String dataProcess(long data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }
}
