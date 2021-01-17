package oopHomework.Utils;

import oopHomework.architecture.ProcessorArm;
import oopHomework.architecture.ProcessorX86;
import oopHomework.memory.Memory;
import oopHomework.processor.Processor;

public class Constants {
    public static class Text {
        public final static String useProcessorOnArchitecture = "Используется процессор на архитектуре %s. %s.";
        public final static String emptyMemoryCell = "Ячейка памяти пустая!";
        public final static String memoryChanged = "Changed";
        public final static String processorInfo = "Частота процессора: %s GHz, кеш: %s Mb, разрядность: %s-bit.";
        public final static String memoryInfo = "Количество свободных ячеек памяти: %s, Процент используемой памяти: %s%%.";
        public final static String systemInfo = "Информация о процессоре:\n%s\nИнформация о памяти:\n%s\n";
    }

    public static class Components {
        //Процессоры на архитектуре ARM
        public final static Processor appleA14Bionic = new ProcessorArm(2.99, 8, 64);
        public final static Processor samsungExynos3110 = new ProcessorArm(1, 0.5, 32);
        public final static Processor qualcommSnapdragon855 = new ProcessorArm(2.84, 4, 64);
        //Процессоры на архитектуре X86
        public final static Processor intelCoreI58300H = new ProcessorX86(2.3, 8, 64);
        public final static Processor intel8086 = new ProcessorX86(0.016, 0, 16);
        public final static Processor intelPentiumPro200 = new ProcessorX86(0.2, 1, 32);
        //Memory
        public final static String[] memory64 = {"1", "1", null, "1", null, "1", "1", "1"};
        public final static String[] memory32 = {"1", null, "1", "1"};
        public final static String[] memory16 = {null, "1"};
        public final static Memory memory1 = new Memory(memory64);
        public final static Memory memory2 = new Memory(memory32);
        public final static Memory memory3 = new Memory(memory16);
    }
}
