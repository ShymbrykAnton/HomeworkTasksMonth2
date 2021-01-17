package oopHomework.Device;

import oopHomework.memory.Memory;
import oopHomework.processor.Processor;

import static oopHomework.Utils.Constants.Text.memoryChanged;
import static oopHomework.Utils.Constants.Text.systemInfo;


public class Device {
    Processor processor;
    Memory memory;

    public Device(Processor processor, Memory memory) {
        this.processor = processor;
        this.memory = memory;
    }

    public void save(String[] data) {
        for (int count = 0; count < Memory.memoryCell.length && count < data.length; count++) {
            Memory.memoryCell[count] = data[count];
        }
    }

    public String[] readAll() {
        String[] localMemory = Memory.memoryCell;
        for (int count = 0; count < Memory.memoryCell.length; count++) {
            if (Memory.memoryCell[count] != null) {
                Memory.memoryCell[count] = null;
            }
        }
        return localMemory;
    }

    public void dataProcessing() {
        for (int count = 0; count < Memory.memoryCell.length; count++) {
            if (Memory.memoryCell[count] != null) {
                Memory.memoryCell[count] += memoryChanged;
            } else {
                Memory.memoryCell[count] = memoryChanged;
            }
        }
    }

    public String getSystemInfo() {
        Memory.MemoryInfo memoryInfo = memory.getMemoryInfo();
        return String.format(systemInfo, processor.getDetails(), memoryInfo.toString());
    }
}
