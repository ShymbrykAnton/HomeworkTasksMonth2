package oophomework.device;

import oophomework.memory.Memory;
import oophomework.processors.base.ProcessorBase;

import static oophomework.utils.Constants.Text.memoryChanged;
import static oophomework.utils.Constants.Text.systemInfo;


public class Device {
    public ProcessorBase processor;
    public Memory memory;

    public Device(ProcessorBase processor, Memory memory) {
        if (processor == null || memory == null) {
            throw new IllegalArgumentException("Процессор не может быть null");
        }
        this.processor = processor;
        this.memory = memory;
    }

    public void save(String[] data) {
        if (data == null || data.length > memory.getMemoryCell().length) {
            throw new IllegalArgumentException("Исходная дата имеет значение налл или отсутствует место в памяти");
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
}
