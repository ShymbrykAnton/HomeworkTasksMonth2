package oophomework.device;

import oophomework.memory.Memory;
import oophomework.processors.base.ProcessorBase;

import java.util.Objects;

import static oophomework.utils.Constants.Text.*;


public class Device {
    public ProcessorBase processor;
    public Memory memory;

    public Device(ProcessorBase processor, Memory memory) {
        if (processor == null || memory == null) {
            throw new IllegalArgumentException(NULL_PROCESSOR);
        }
        this.processor = processor;
        this.memory = memory;
    }

    public void save(String[] data) {
        if (data == null || data.length > memory.getMemoryCell().length) {
            throw new IllegalArgumentException(NULL_DATA_OR_NO_AVAILABLE_SPACE_IN_MEMORY);
        }
        String[] memoryCell = memory.getMemoryCell();
        for (int count = 0; count < memoryCell.length && count < data.length; count++) {
            memoryCell[count] = data[count];
            memory.setMemoryCell(memoryCell);
        }
    }

    public String[] readAll() {
        final String[] saveMemoryCell = new String[memory.getMemoryCell().length];
        String[] memoryCell = memory.getMemoryCell();
        for (int count = 0; count < memoryCell.length; count++) {
            saveMemoryCell[count] = memoryCell[count];
            if (memoryCell[count] != null) {
                memoryCell[count] = null;
            }
        }
        memory.setMemoryCell(memoryCell);
        return saveMemoryCell;
    }

    public void dataProcessing() {
        String[] memoryCell = memory.getMemoryCell();
        for (int count = 0; count < memoryCell.length; count++) {
            if (memoryCell[count] != null) {
                memoryCell[count] = memoryChanged;
                memory.setMemoryCell(memoryCell);
            }
        }
    }

    public String getSystemInfo() {
        return String.format(systemInfo, processor.getDetails(), memory.getMemoryInfo().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(processor, device.processor) && Objects.equals(memory, device.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processor, memory);
    }
}