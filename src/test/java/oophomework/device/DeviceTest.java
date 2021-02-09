package oophomework.device;

import oophomework.memory.Memory;
import oophomework.processor.Processor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class DeviceTest {
    Processor processor = Mockito.mock(Processor.class);
    Memory memory = Mockito.mock(Memory.class);
    Device cut = new Device(processor,memory);

    @Test
    void saveTest() {
    }

    @Test
    void readAll() {
    }

    @Test
    void dataProcessing() {
    }

    @Test
    void getSystemInfo() {
    }
}