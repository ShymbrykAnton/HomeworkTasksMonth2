package oophomework.processors;

import oophomework.processors.base.ProcessorBase;

import java.util.Locale;

import static oophomework.utils.Constants.Text.*;

public class ProcessorArm extends ProcessorBase {
    private final String architecture = ARM;

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
            throw new IllegalArgumentException(NULL_DATA);
        }
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }

    @Override
    public String dataProcess(long data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toLowerCase(Locale.ROOT);
    }
}
