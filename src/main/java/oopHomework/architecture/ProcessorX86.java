package oopHomework.architecture;

import oopHomework.processor.Processor;

import java.util.Locale;

import static oopHomework.Utils.Constants.Text.processorArchitecture;
import static oopHomework.Utils.Constants.Text.useProcessorOnArchitecture;

public class ProcessorX86 extends Processor {
    private final String architecture = "X86";

    //todo показать архитектуру в гет дитейлс процессора, разобратся почему берет последнюю память
    public ProcessorX86(double frequency, double cache, int bitCapacity) {
        super(frequency, cache, bitCapacity);
    }

    @Override
    public String dataProcess(String data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toUpperCase(Locale.ROOT);
    }

    @Override
    public String dataProcess(long data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toUpperCase(Locale.ROOT);
    }
}
