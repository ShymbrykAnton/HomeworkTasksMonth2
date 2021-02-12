package oophomework.processors;

import oophomework.processors.base.ProcessorBase;

import java.util.Locale;

import static oophomework.utils.Constants.Text.useProcessorOnArchitecture;

public class ProcessorX86 extends ProcessorBase {
    private final String architecture = "X86";

    public ProcessorX86(double frequency, double cache, int bitCapacity) {
        super(frequency, cache, bitCapacity);
    }
    @Override
    public String getArchitecture() {
        return architecture;
    }

    @Override
    public String dataProcess(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Информация отсутствует!");
        }
        return String.format(useProcessorOnArchitecture, architecture, data).toUpperCase(Locale.ROOT);
    }


    @Override
    public String dataProcess(long data) {
        return String.format(useProcessorOnArchitecture, architecture, data).toUpperCase(Locale.ROOT);
    }
}
