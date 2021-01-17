package oopHomework.Device;

import oopHomework.memory.Memory;
import oopHomework.processor.Processor;

import static oopHomework.Utils.Constants.Text.memoryChanged;
import static oopHomework.Utils.Constants.Text.systemInfo;


public class Device {
    public Processor processor;
    public Memory memory;

    public Device(Processor processor, Memory memory) {
        this.processor = processor;
        this.memory = memory;
    }

    public void save(String[] data) {
        for (int count = 0; count < this.memory.memoryCell.length && count < data.length; count++) {
            this.memory.memoryCell[count] = data[count];
        }
    }

    public String[] readAll() {
        String[] localMemory = this.memory.memoryCell;
        for (int count = 0; count < this.memory.memoryCell.length; count++) {
            if (this.memory.memoryCell[count] != null) {
                this.memory.memoryCell[count] = null;
            }
        }
        return localMemory;
    }

    public void dataProcessing() {
        for (int count = 0; count < this.memory.memoryCell.length; count++) {
            if (this.memory.memoryCell[count] != null) {
                this.memory.memoryCell[count] += memoryChanged;
            } else {
                this.memory.memoryCell[count] = memoryChanged;
            }
        }
    }

    public String getSystemInfo() {
        Memory.MemoryInfo memoryInfo = memory.getMemoryInfo();
        return String.format(systemInfo, processor.getDetails(), memoryInfo.toString());
    }
}
