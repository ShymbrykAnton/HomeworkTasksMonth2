package oophomework.device;

import oophomework.memory.Memory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static oophomework.utils.Constants.Components.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DeviceTest {
    private static Device device = new Device(intelPentiumPro200, new Memory(new String[]{"1", "1", null, "1", null, null, null, null}));
    private static Device device1 = new Device(samsungExynos3110, new Memory(new String[]{"1", null, "5", null}));
    private static Device device2 = new Device(intelCoreI58300H, new Memory(new String[]{"1", "1", "1"}));
    private static Device device3 = new Device(qualcommSnapdragon855, new Memory(new String[]{null, null, null, null}));
    private static Device device4 = new Device(intelPentiumPro200, new Memory(new String[]{null, "8"}));

    @BeforeEach
    private void initDevice() {
        device = new Device(intelPentiumPro200, new Memory(new String[]{"1", "1", null, "1", null, null, null, null}));
        device1 = new Device(samsungExynos3110, new Memory(new String[]{"1", null, "5", null}));
        device2 = new Device(intelCoreI58300H, new Memory(new String[]{"1", "1", "1"}));
        device3 = new Device(qualcommSnapdragon855, new Memory(new String[]{null, null, null, null}));
        device4 = new Device(intelPentiumPro200, new Memory(new String[]{null, "8"}));
    }

    static Stream<Arguments> saveTest_NOMINAL() {
        return Stream.of(
                arguments(device, new String[]{"1", "2", "10", "4"}, new String[]{"1", "2", "10", "4", null, null, null, null}),
                arguments(device1, new String[]{"3", "4"}, new String[]{"3", "4", "5", null}),
                arguments(device2, new String[]{"1", "3"}, new String[]{"1", "3", "1"}),
                arguments(device3, new String[]{"1", "2", "3", "4"}, new String[]{"1", "2", "3", "4"}),
                arguments(device4, new String[]{"1", "2"}, new String[]{"1", "2"})
        );
    }

    @ParameterizedTest
    @MethodSource("saveTest_NOMINAL")
    void saveTest(Device device, String[] data, String[] expected) {
        device.save(data);
        String[] actual = device.memory.getMemoryCell();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> readAllTest_NOMINAL() {
        return Stream.of(
                arguments(device, new String[]{"1", "1", null, "1", null, null, null, null}, new String[]{null, null, null, null, null, null, null, null}),
                arguments(device1, new String[]{"1", null, "5", null}, new String[]{null, null, null, null}),
                arguments(device2, new String[]{"1", "1", "1"}, new String[]{null, null, null}),
                arguments(device3, new String[]{null, null, null, null}, new String[]{null, null, null, null}),
                arguments(device4, new String[]{null, "8"}, new String[]{null, null})
        );
    }

    @ParameterizedTest
    @MethodSource("readAllTest_NOMINAL")
    void readAllTest(Device device, String[] expected1, String[] expected) {
        String[] actual1 = device.readAll();
        String[] actual = device.memory.getMemoryCell();
        Assertions.assertArrayEquals(expected1, actual1);
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> dataProcessingTest_NOMINAL() {
        return Stream.of(
                arguments(device, new String[]{"Changed", "Changed", null, "Changed", null, null, null, null}),
                arguments(device1, new String[]{"Changed", null, "Changed", null}),
                arguments(device2, new String[]{"Changed", "Changed", "Changed"}),
                arguments(device3, new String[]{null, null, null, null}),
                arguments(device4, new String[]{null, "Changed"})
        );
    }

    @ParameterizedTest
    @MethodSource("dataProcessingTest_NOMINAL")
    void dataProcessingTest(Device device, String[] expected) {
        device.dataProcessing();
        String[] actual = device.memory.getMemoryCell();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> getSystemInfoTest_NOMINAL() {
        return Stream.of(
                arguments(device, "Информация о процессоре:\n" +
                        "Частота процессора: 0.2 GHz, кеш: 1.0 Mb, разрядность: 32-bit, архитектура: X86.\n" +
                        "Информация о памяти:\n" +
                        "Количество свободных ячеек памяти: 5, Процент используемой памяти: 37.5%.\n"),
                arguments(device1, "Информация о процессоре:\n" +
                        "Частота процессора: 1.0 GHz, кеш: 0.5 Mb, разрядность: 32-bit, архитектура: ARM.\n" +
                        "Информация о памяти:\n" +
                        "Количество свободных ячеек памяти: 2, Процент используемой памяти: 50.0%.\n"),
                arguments(device2, "Информация о процессоре:\n" +
                        "Частота процессора: 2.3 GHz, кеш: 8.0 Mb, разрядность: 64-bit, архитектура: X86.\n" +
                        "Информация о памяти:\n" +
                        "Количество свободных ячеек памяти: 0, Процент используемой памяти: 100.0%.\n"),
                arguments(device3, "Информация о процессоре:\n" +
                        "Частота процессора: 2.84 GHz, кеш: 4.0 Mb, разрядность: 64-bit, архитектура: ARM.\n" +
                        "Информация о памяти:\n" +
                        "Количество свободных ячеек памяти: 4, Процент используемой памяти: 0.0%.\n"),
                arguments(device4, "Информация о процессоре:\n" +
                        "Частота процессора: 0.2 GHz, кеш: 1.0 Mb, разрядность: 32-bit, архитектура: X86.\n" +
                        "Информация о памяти:\n" +
                        "Количество свободных ячеек памяти: 1, Процент используемой памяти: 50.0%.\n")
        );
    }

    @ParameterizedTest
    @MethodSource("getSystemInfoTest_NOMINAL")
    void getSystemInfoTest(Device device, String expected) {
        String actual = device.getSystemInfo();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> ExceptionTest_Nominal() {
        Executable executable;
        return Stream.of(
                arguments(executable = () -> device.save(null)),
                arguments(executable = () -> device.save(new String[20])),
                arguments(executable = () -> new Device(null,new Memory(null)))
        );
    }

    @ParameterizedTest
    @MethodSource("ExceptionTest_Nominal")
    void getException1Test(Executable executable) throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, executable);
        Assertions.assertNotNull(thrown.getMessage());
    }
}