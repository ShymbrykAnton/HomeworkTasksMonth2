package oophomework.utils;

import oophomework.device.Device;
import oophomework.processors.ProcessorArm;
import oophomework.processors.ProcessorX86;
import oophomework.memory.Memory;
import oophomework.processors.base.ProcessorBase;

public class Constants {
    public static class Text {
        public final static String useProcessorOnArchitecture = "Используется процессор на архитектуре %s. %s.";
        public final static String processorArchitecture = "Архитектура процессора: %s.\n";
        public final static String emptyMemoryCell = "Ячейка памяти пустая!";
        public final static String memoryChanged = "Changed";
        public final static String processorInfo = "Частота процессора: %s GHz, кеш: %s Mb, разрядность: %s-bit, архитектура: %s.";
        public final static String memoryInfo = "Количество свободных ячеек памяти: %s, Процент используемой памяти: %s%%.";
        public final static String systemInfo = "Информация о процессоре:\n%s\nИнформация о памяти:\n%s\n";
        public final static String more = "more";
        public final static String less = "less";
    }

    public static class Components {
        //Процессоры на архитектуре ARM
        public final static ProcessorBase appleA14Bionic = new ProcessorArm(2.99, 8, 64);
        public final static ProcessorBase samsungExynos3110 = new ProcessorArm(1, 0.5, 32);
        public final static ProcessorBase qualcommSnapdragon855 = new ProcessorArm(2.84, 4, 64);
        //Процессоры на архитектуре X86
        public final static ProcessorBase intelCoreI58300H = new ProcessorX86(2.3, 8, 64);
        public final static ProcessorBase intel8086 = new ProcessorX86(0.016, 0, 16);
        public final static ProcessorBase intelPentiumPro200 = new ProcessorX86(0.2, 1, 32);
        //Memory
        public final static String[] memory64 = {"1", "1", null, "1", null, null, null, null};
        public final static String[] memory32 = {"1", null, "5", null};
        public final static String[] memory16 = {null, "8"};
        public static Memory memory1 = new Memory(memory64);
        public static Memory memory2 = new Memory(memory32);
        public static Memory memory3 = new Memory(memory16);
        public static Memory emptyMemory = new Memory(new String[]{null, null, null, null});
        public static Memory fullMemory = new Memory(new String[]{"1", "1", "1"});
        //Devices
        public final static Device[] devices = {new Device(appleA14Bionic, memory1), new Device(samsungExynos3110, memory2),
                new Device(qualcommSnapdragon855, memory1), new Device(intelCoreI58300H, memory1),
                new Device(intel8086, memory3), new Device(intelPentiumPro200, memory2),
                new Device(appleA14Bionic, memory2), new Device(intelCoreI58300H, memory3),
                new Device(qualcommSnapdragon855, memory2), new Device(samsungExynos3110, memory3)};
    }
}
