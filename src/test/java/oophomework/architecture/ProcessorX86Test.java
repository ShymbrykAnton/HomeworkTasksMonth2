package oophomework.architecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProcessorX86Test {
    //TODO константы капсом
    private final double frequency = 53;
    private final double cache = 4;
    private final int bitCapacity = 64;
    private final ProcessorX86 processorX86 = new ProcessorX86(frequency, cache, bitCapacity);
    private static final String upperCaseArchitecture = "ИСПОЛЬЗУЕТСЯ ПРОЦЕССОР НА АРХИТЕКТУРЕ X86.";

    static Stream<Arguments> dataProcessTest_NOMINAL() {
        return Stream.of(
                arguments("", upperCaseArchitecture + " ."),
                arguments("2 слова", upperCaseArchitecture + " 2 СЛОВА."),
                arguments("two words", upperCaseArchitecture + " TWO WORDS."),
                arguments(null, upperCaseArchitecture + " .")
        );
    }

    static Stream<Arguments> testDataProcess_NOMINAL() {
        return Stream.of(
                arguments(0, upperCaseArchitecture + " 0."),
                arguments(-5, upperCaseArchitecture + " -5."),
                arguments(Long.MAX_VALUE, upperCaseArchitecture + " " + Long.MAX_VALUE + "."),
                arguments(Long.MIN_VALUE, upperCaseArchitecture + " " + Long.MIN_VALUE + ".")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProcessTest_NOMINAL")
    void dataProcessTest(String data, String expected) {
        String actual = processorX86.dataProcess(data);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testDataProcess_NOMINAL")
    void testDataProcess(long data, String expected) {
        String actual = processorX86.dataProcess(data);
        Assertions.assertEquals(expected, actual);
    }
}