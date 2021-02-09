package oophomework.architecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProcessorArmTest {
    private final double frequency = 53;
    private final double cache = 4;
    private final int bitCapacity = 64;
    private final ProcessorArm processorArm = new ProcessorArm(frequency, cache, bitCapacity);
    private static final String lowerCaseArchitecture = "используется процессор на архитектуре arm.";

    static Stream<Arguments> dataProcessTest_NOMINAL() {
        return Stream.of(
                arguments("", lowerCaseArchitecture + " ."),
                arguments("2 слова", lowerCaseArchitecture + " 2 слова."),
                arguments("two words", lowerCaseArchitecture + " two words."),
                arguments(null, lowerCaseArchitecture + " .")
        );
    }

    static Stream<Arguments> testDataProcess_NOMINAL() {
        return Stream.of(
                arguments(0, lowerCaseArchitecture + " 0."),
                arguments(-5, lowerCaseArchitecture + " -5."),
                arguments(Long.MAX_VALUE, lowerCaseArchitecture + " " + Long.MAX_VALUE + "."),
                arguments(Long.MIN_VALUE, lowerCaseArchitecture + " " + Long.MIN_VALUE + ".")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProcessTest_NOMINAL")
    void dataProcessTest(String data, String expected) {
        String actual = processorArm.dataProcess(data);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testDataProcess_NOMINAL")
    void testDataProcess(long data, String expected) {
        String actual = processorArm.dataProcess(data);
        Assertions.assertEquals(expected, actual);
    }
    // тесты на исключение на налл
}