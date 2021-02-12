package oophomework.filtration;

import oophomework.device.Device;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static oophomework.utils.Constants.Components.*;
import static oophomework.utils.Constants.Text.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FiltrationTest {
    private final static Filtration filtration = new Filtration();
    private final static List<Device> list1 = new ArrayList<>();
    private final static List<Device> list2 = new ArrayList<>();
    private final static List<Device> list3 = new ArrayList<>();
    private final static List<Device> list4 = new ArrayList<>();

    @BeforeEach
    private void initListsForFiltrationByProcessorParameters() {
        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();
    }

    static Stream<Arguments> filtrateByProcessorParametersTest_NOMINAL() {
        list1.add(new Device(appleA14Bionic, memory1));
        list1.add(new Device(appleA14Bionic, memory2));
        list2.add(new Device(intelCoreI58300H, memory1));
        list2.add(new Device(intelCoreI58300H, memory3));
        list3.add(new Device(intelPentiumPro200, memory2));
        list4.add(new Device(samsungExynos3110, memory2));
        list4.add(new Device(samsungExynos3110, memory3));
        return Stream.of(
                arguments(2.99, 8, 64, list1.toArray()),
                arguments(2.3, 8, 64, list2.toArray()),
                arguments(0.2, 1, 32, list3.toArray()),
                arguments(1, 0.5, 32, list4.toArray())
        );
    }

    @ParameterizedTest
    @MethodSource("filtrateByProcessorParametersTest_NOMINAL")
    void filtrateByProcessorParametersTest(double frequency, double cache, int bitCapacity, Object[] expected) {
        List<Device> listActual = filtration.filtrateByProcessorParameters(frequency, cache, bitCapacity, devices);
        Object[] actual = listActual.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> filtrateByArchitectureTest_NOMINAL() {
        list1.add(new Device(appleA14Bionic, memory1));
        list1.add(new Device(samsungExynos3110, memory2));
        list1.add(new Device(qualcommSnapdragon855, memory1));
        list1.add(new Device(appleA14Bionic, memory2));
        list1.add(new Device(qualcommSnapdragon855, memory2));
        list1.add(new Device(samsungExynos3110, memory3));
        list2.add(new Device(intelCoreI58300H, memory1));
        list2.add(new Device(intel8086, memory3));
        list2.add(new Device(intelPentiumPro200, memory2));
        list2.add(new Device(intelCoreI58300H, memory3));
        return Stream.of(
                arguments("ARM", list1.toArray()),
                arguments("X86", list2.toArray()),
                arguments("Something", list3.toArray()),
                arguments("Something", list4.toArray())
        );
    }

    @ParameterizedTest
    @MethodSource("filtrateByArchitectureTest_NOMINAL")
    void filtrateByArchitectureTest(String architecture, Object[] expected) {
        List<Device> listActual = filtration.filtrateByArchitecture(architecture, devices);
        Object[] actual = listActual.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> filtrateByMemoryVolumeTest_NOMINAL() {
        list1.add(new Device(appleA14Bionic, memory1));
        list1.add(new Device(qualcommSnapdragon855, memory1));
        list1.add(new Device(intelCoreI58300H, memory1));

        list2.add(new Device(intel8086, memory3));
        list2.add(new Device(intelCoreI58300H, memory3));
        list2.add(new Device(samsungExynos3110, memory3));

        list3.add(new Device(appleA14Bionic, memory1));
        list3.add(new Device(samsungExynos3110, memory2));
        list3.add(new Device(qualcommSnapdragon855, memory1));
        list3.add(new Device(intelCoreI58300H, memory1));
        list3.add(new Device(intelPentiumPro200, memory2));
        list3.add(new Device(appleA14Bionic, memory2));
        list3.add(new Device(qualcommSnapdragon855, memory2));

        list4.add(new Device(samsungExynos3110, memory2));
        list4.add(new Device(intel8086, memory3));
        list4.add(new Device(intelPentiumPro200, memory2));
        list4.add(new Device(appleA14Bionic, memory2));
        list4.add(new Device(intelCoreI58300H, memory3));
        list4.add(new Device(qualcommSnapdragon855, memory2));
        list4.add(new Device(samsungExynos3110, memory3));

        return Stream.of(
                arguments(7, more, list1.toArray()),
                arguments(3, less, list2.toArray()),
                arguments(3, more, list3.toArray()),
                arguments(5, less, list4.toArray())
        );
    }

    @ParameterizedTest
    @MethodSource("filtrateByMemoryVolumeTest_NOMINAL")
    void filtrateByMemoryVolumeTest(int memoryVolume, String moreLess, Object[] expected) {
        List<Device> listActual = filtration.filtrateByMemoryVolume(memoryVolume, moreLess, devices);
        Object[] actual = listActual.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> filtrateByOccupiedMemorySpaceTest_NOMINAL() {
        list2.add(new Device(appleA14Bionic, memory1));
        list2.add(new Device(qualcommSnapdragon855, memory1));
        list2.add(new Device(intelCoreI58300H, memory1));

        list3.add(new Device(samsungExynos3110, memory2));
        list3.add(new Device(intel8086, memory3));
        list3.add(new Device(intelPentiumPro200, memory2));
        list3.add(new Device(appleA14Bionic, memory2));
        list3.add(new Device(intelCoreI58300H, memory3));
        list3.add(new Device(qualcommSnapdragon855, memory2));
        list3.add(new Device(samsungExynos3110, memory3));

        return Stream.of(
                arguments(50, more, list1.toArray()),
                arguments(40, less, list2.toArray()),
                arguments(49, more, list3.toArray()),
                arguments(30, less, list4.toArray())
        );
    }

    @ParameterizedTest
    @MethodSource("filtrateByOccupiedMemorySpaceTest_NOMINAL")
    void filtrateByOccupiedMemorySpaceTest(int occupiedMemorySpace, String moreLess, Object[] expected) {
        List<Device> listActual = filtration.filtrateByOccupiedMemorySpace(occupiedMemorySpace, moreLess, devices);
        Object[] actual = listActual.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> ExceptionTest_Nominal() {
        Executable executable;
        return Stream.of(
                arguments(executable = () -> filtration.filtrateByProcessorParameters(2,2,2,null)),
                arguments(executable = () -> filtration.filtrateByArchitecture(null,devices)),
                arguments(executable = () -> filtration.filtrateByArchitecture("sdfs",null)),
                arguments(executable = () -> filtration.filtrateByMemoryVolume(1,"sdfs",null)),
                arguments(executable = () -> filtration.filtrateByMemoryVolume(1,null,devices)),
                arguments(executable = () -> filtration.filtrateByOccupiedMemorySpace(1,"sdfs",null)),
                arguments(executable = () -> filtration.filtrateByOccupiedMemorySpace(1,null,devices))
        );
    }

    @ParameterizedTest
    @MethodSource("ExceptionTest_Nominal")
    void getException1Test(Executable executable) throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, executable);
        Assertions.assertNotNull(thrown.getMessage());
    }
}