package oophomework.memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static oophomework.utils.Constants.Components.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MemoryTest {

    @BeforeEach
    private void initMemory() {
        emptyMemory = new Memory(new String[]{null, null, null, null});
        fullMemory = new Memory(new String[]{"1", "1", "1"});
        memory1 = new Memory(new String[]{"1", "1", null, "1", null, null, null, null});
        memory2 = new Memory(new String[] {"1", null, "5", null});
        memory3 = new Memory(new String[] {null, "8"});
    }

    static Stream<Arguments> readLastTest_NOMINAL() {
        return Stream.of(
                arguments("1", memory1),
                arguments("5", memory2),
                arguments("8", memory3),
                arguments("Ячейка памяти пустая!", emptyMemory)
        );
    }

    @ParameterizedTest
    @MethodSource("readLastTest_NOMINAL")
    void readLastTest(String expected, Memory memory) {
        String actual = memory.readLast();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> removeLastTest_NOMINAL() {
        return Stream.of(
                arguments("[1, 1, null, null, null, null, null, null]", memory1),
                arguments("[1, null, null, null]", memory2),
                arguments("[null, null]", memory3),
                arguments("Ячейка памяти пустая!", emptyMemory)
        );
    }

    @ParameterizedTest
    @MethodSource("removeLastTest_NOMINAL")
    void removeLast(String expected, Memory memory) {
        String actual = memory.removeLast();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> saveTest_NOMINAL() {
        return Stream.of(
                arguments("[1, 1, null, 1, null, null, null, 1]", memory1, true),
                arguments("[1, null, 5, 1]", memory2, true),
                arguments("[1, 8]", memory3, true),
                arguments("[null, null, null, 1]", emptyMemory, true),
                arguments("[1, 1, 1]", fullMemory, false)
        );
    }

    @ParameterizedTest
    @MethodSource("saveTest_NOMINAL")
    void saveTest(String expected1, Memory memory, boolean expected) {
        boolean actual = memory.save();
        Assertions.assertEquals(actual, expected);
        String actual1 = memory.toString();
        Assertions.assertEquals(expected1, actual1);
    }

    static Stream<Arguments> getMemoryInfoTest_NOMINAL() {
        return Stream.of(
                arguments(new MemoryInfo(5, 37.5), memory1),
                arguments(new MemoryInfo(2, 50), memory2),
                arguments(new MemoryInfo(1, 50), memory3),
                arguments(new MemoryInfo(4, 0), emptyMemory),
                arguments(new MemoryInfo(0, 100), fullMemory)
        );
    }

    @ParameterizedTest
    @MethodSource("getMemoryInfoTest_NOMINAL")
    void getMemoryInfoTest(MemoryInfo expected, Memory memory) {
        MemoryInfo actual = memory.getMemoryInfo();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void constructorExceptionTest() {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, ()->new Memory(null));
        Assertions.assertNotNull(thrown.getMessage());
    }
}