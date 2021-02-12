package oophomework.filtration;

import oophomework.device.Device;
import oophomework.memory.MemoryInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static oophomework.utils.Constants.Components.*;
import static oophomework.utils.Constants.Components.fullMemory;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FiltrationTest {
    Filtration filtration = new Filtration();
    private final static List<Device> list1 = new ArrayList<>();
    private final static List<Device> list2 = new ArrayList<>();
    private final static List<Device> list3 = new ArrayList<>();
    private final static List<Device> list4 = new ArrayList<>();

    @BeforeAll
    private static void initListsForFiltrationByProcessorParameters() {
        list1.add(new Device(appleA14Bionic, memory1));
        list1.add(new Device(appleA14Bionic, memory2));
        list2.add(new Device(intelCoreI58300H, memory1));
        list2.add(new Device(intelCoreI58300H, memory3));
        list3.add(new Device(intelPentiumPro200, memory2));
        list4.add(new Device(samsungExynos3110, memory2));
        list4.add(new Device(samsungExynos3110, memory3));
    }

    // public static class Components {
    //        //Процессоры на архитектуре ARM
    //        public final static ProcessorBase appleA14Bionic = new ProcessorArm(2.99, 8, 64);
    //        public final static ProcessorBase samsungExynos3110 = new ProcessorArm(1, 0.5, 32);
    //        public final static ProcessorBase qualcommSnapdragon855 = new ProcessorArm(2.84, 4, 64);
    //        //Процессоры на архитектуре X86
    //        public final static ProcessorBase intelCoreI58300H = new ProcessorX86(2.3, 8, 64);
    //        public final static ProcessorBase intel8086 = new ProcessorX86(0.016, 0, 16);
    //        public final static ProcessorBase intelPentiumPro200 = new ProcessorX86(0.2, 1, 32);
    //        //Memory
    //        public final static String[] memory64 = {"1", "1", null, "1", null, null, null, null};
    //        public final static String[] memory32 = {"1", null, "5", null};
    //        public final static String[] memory16 = {null, "8"};
    //        public static Memory memory1 = new Memory(memory64);
    //        public static Memory memory2 = new Memory(memory32);
    //        public static Memory memory3 = new Memory(memory16);
    //        public static Memory emptyMemory = new Memory(new String[]{null, null, null, null});
    //        public static Memory fullMemory = new Memory(new String[]{"1", "1", "1"});
    //        //Devices
    //        public final static Device[] devices = {new Device(appleA14Bionic, memory1), new Device(samsungExynos3110, memory2),
    //                new Device(qualcommSnapdragon855, memory1), new Device(intelCoreI58300H, memory1),
    //                new Device(intel8086, memory3), new Device(intelPentiumPro200, memory2),
    //                new Device(appleA14Bionic, memory2), new Device(intelCoreI58300H, memory3),
    //                new Device(qualcommSnapdragon855, memory2), new Device(samsungExynos3110, memory3)};
    //    }
    static Stream<Arguments> filtrateByProcessorParametersTest_NOMINAL() {
        return Stream.of(
                arguments(2.99, 8, 64, list1.toString()),
                arguments(2.3, 8, 64, list2.toString()),
                arguments(0.2, 1, 32, list3.toString()),
                arguments(1, 0.5, 32, list4.toString())
        );
    }

    @ParameterizedTest
    @MethodSource("filtrateByProcessorParametersTest_NOMINAL")
    void filtrateByProcessorParametersTest(double frequency, double cache, int bitCapacity, String expected) {
        List<Device> actual = filtration.filtrateByProcessorParameters(frequency, cache, bitCapacity, devices);
        String aaa = actual.toString();
        Assertions.assertEquals(expected, aaa);
    }

    @Test
    void filtrateByArchitecture() {
    }

    @Test
    void filtrateByMemoryVolume() {
    }

    @Test
    void filtrateByOccupiedMemorySpace() {
    }
}