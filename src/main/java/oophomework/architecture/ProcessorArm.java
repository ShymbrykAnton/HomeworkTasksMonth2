package oophomework.architecture;

import oophomework.processor.Processor;

import java.util.Locale;

import static oophomework.utils.Constants.Text.useProcessorOnArchitecture;

public class ProcessorArm extends Processor {
    private final String architecture = "ARM";

    public ProcessorArm(double frequency, double cache, int bitCapacity) {
        super(frequency, cache, bitCapacity);
    }

    @Override
    public String getArchitecture() {
        return architecture;
    }

    @Override
    public String dataProcess(String data) {
        if (data == null) {
            return String.format(useProcessorOnArchitecture, architecture, "").toLowerCase(Locale.ROOT);
        }
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }

    @Override
    public String dataProcess(long data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }
}
