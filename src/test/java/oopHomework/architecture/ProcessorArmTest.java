package oopHomework.architecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProcessorArmTest {
    double frequency = 3.4;
    double cache = 4;
    int bitCapacity = 64;
    ProcessorArm processorArm = new ProcessorArm(frequency,cache,bitCapacity);
    static Stream<Arguments> dataProcessTest_NOMINAL() {
        return Stream.of(
                arguments("", ""),
                arguments("Ваших душ безлиственную осень", "Ваших душ безлиственную"),
                arguments("Летят в меня, как град рыгающей грозы,", "Летят в меня, как град рыгающей"),
                arguments("aaa dffff ,,,, sssaf, вот тАк", "aaa dffff ,,,, sssaf, вот"),
                arguments(" 123456789  12345689    12345689", "123456789  12345689   "),
                arguments("От!Цокота?Копыт.Пыль)Летит.", "")
        );
    }
    static Stream<Arguments> testDataProcess_NOMINAL() {
        return Stream.of(
                arguments("", ""),
                arguments("Ваших душ безлиственную осень", "Ваших душ безлиственную"),
                arguments("Летят в меня, как град рыгающей грозы,", "Летят в меня, как град рыгающей"),
                arguments("aaa dffff ,,,, sssaf, вот тАк", "aaa dffff ,,,, sssaf, вот"),
                arguments(" 123456789  12345689    12345689", "123456789  12345689   "),
                arguments("От!Цокота?Копыт.Пыль)Летит.", "")
        );
    }

    @ParameterizedTest
    @MethodSource ("dataProcessTest_NOMINAL")
    void dataProcessTest() {
        String actual = processorArm.dataProcess(2635556);
        String expected = "используется процессор на архитектуре arm. 2635556.";
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource ("testDataProcess_NOMINAL")
    void testDataProcess() {
    }
}