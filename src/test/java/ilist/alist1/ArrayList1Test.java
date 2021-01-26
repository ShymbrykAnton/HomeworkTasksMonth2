package ilist.alist1;

import ilist.IList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArrayList1Test {
    private static final IList collection1 = new ArrayList1();
    private static final IList collection2 = new ArrayList1();
    private static final IList collection3 = new ArrayList1();
    private static final IList collection4 = new ArrayList1();
    private static final IList collection5 = new ArrayList1();

    private static final IList collection11 = new ArrayList1(6);
    private static final IList collection22 = new ArrayList1(12);
    private static final IList collection33 = new ArrayList1(24);
    private static final IList collection44 = new ArrayList1(48);
    private static final IList collection55 = new ArrayList1(96);

    private static final IList collection111 = new ArrayList1(new int[]{});
    private static final IList collection222 = new ArrayList1(new int[]{1});
    private static final IList collection333 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    private static final IList collection444 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    private static final IList collection555 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});

    private static void addSomethingToCollections(IList collection2, IList collection3,
                                                  IList collection4, IList collection5) {
        collection2.add(1);

        collection3.add(1);
        collection3.add(2);
        collection3.add(3);
        collection3.add(4);
        collection3.add(5);
        collection3.add(6);
        collection3.add(7);
        collection3.add(8);
        collection3.add(9);

        collection4.add(1);
        collection4.add(2);
        collection4.add(3);
        collection4.add(4);
        collection4.add(5);
        collection4.add(6);
        collection4.add(7);
        collection4.add(8);
        collection4.add(9);
        collection4.add(10);

        collection5.add(1);
        collection5.add(2);
        collection5.add(3);
        collection5.add(4);
        collection5.add(5);
        collection5.add(6);
        collection5.add(7);
        collection5.add(8);
        collection5.add(9);
        collection5.add(10);
        collection5.add(11);
        collection5.add(12);
    }

    static Stream<Arguments> clearTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        return Stream.of(
                arguments(collection1),
                arguments(collection2),
                arguments(collection3),
                arguments(collection4),
                arguments(collection5)
        );
    }

    @ParameterizedTest
    @MethodSource("clearTest_Nominal")
    void clearTest(IList collection) {
        collection.clear();
        int actual = collection.size();
        int expected = collection1.size();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> sizeTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        return Stream.of(
                arguments(0, collection1),
                arguments(1, collection2),
                arguments(9, collection3),
                arguments(10, collection4),
                arguments(12, collection5)
        );
    }

    @ParameterizedTest
    @MethodSource("sizeTest_Nominal")
    void sizeTest(int expected, IList collection) {
        int actual = collection.size();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> getTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(1, collection2, 0),
                arguments(7, collection33, 6),
                arguments(9, collection444, 8),
                arguments(12, collection55, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("getTest_Nominal")
    void getTest(int expected, IList collection, int index) {
        int actual = collection.get(index);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.get(-15));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void getException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.get(10));
        Assertions.assertNotNull(thrown.getMessage());
    }

    static Stream<Arguments> addTest_Nominal() {
        return Stream.of(
                arguments(true, 0, collection1, 0),
                arguments(true, 1, collection2, 0),
                arguments(true, 9, collection3, 4),
                arguments(true, 0, collection11, 0),
                arguments(true, 10, collection44, 7),
                arguments(true, 12, collection55, 8),
                arguments(true, 0, collection111, 0),
                arguments(true, 1, collection222, 0),
                arguments(true, 9, collection555, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("addTest_Nominal")
    void addValueTest(boolean expected, int value, IList collection) {
        boolean actual = collection.add(value);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> addValueByIndexTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(true, 1, collection2, 0),
                arguments(true, 9, collection3, 4),
                arguments(true, 10, collection44, 7),
                arguments(true, 12, collection55, 8),
                arguments(true, 1, collection222, 0),
                arguments(true, 9, collection555, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("addValueByIndexTest_Nominal")
    void addValueByIndexTest(boolean expected, int value, IList collection, int index) {
        boolean actual = collection.add(index, value);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addValueException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.add(-15, 43));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void addValueException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.add(10, 50));
        Assertions.assertNotNull(thrown.getMessage());
    }

    static Stream<Arguments> removeTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(0, collection1, -2147483648),
                arguments(1, collection2, 1),
                arguments(9, collection3, 9),
                arguments(0, collection11, -2147483648),
                arguments(10, collection44, 10),
                arguments(12, collection55, 12),
                arguments(0, collection111, -2147483648),
                arguments(1, collection222, 1),
                arguments(9, collection555, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("removeTest_Nominal")
    void removeTest(int number, IList collection, int expected) {
        int actual = collection.remove(number);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> removeByIndexTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(1, 0, collection2),
                arguments(6, 5, collection3),
                arguments(10, 9, collection44),
                arguments(12, 11, collection55),
                arguments(5, 4, collection333),
                arguments(3, 2, collection555)
        );
    }

    @ParameterizedTest
    @MethodSource("removeByIndexTest_Nominal")
    void removeByIndexTest(int expected, int index, IList collection) {
        int actual = collection.removeByIndex(index);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void removeByIndexException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.removeByIndex(-15));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void removeByIndexException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.removeByIndex(50));
        Assertions.assertNotNull(thrown.getMessage());
    }

    static Stream<Arguments> containsTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(false, collection1, 0),
                arguments(true, collection2, 1),
                arguments(true, collection3, 4),
                arguments(false, collection11, 0),
                arguments(false, collection44, 50),
                arguments(true, collection55, 8),
                arguments(false, collection111, 0),
                arguments(false, collection222, 12),
                arguments(true, collection555, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("containsTest_Nominal")
    void containsTest(boolean expected, IList collection, int value) {
        boolean actual = collection.contains(value);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> setTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(1, collection2, 0, 1),
                arguments(4, collection3, 3, 4),
                arguments(7, collection44, 6, 7),
                arguments(8, collection55, 7, 8),
                arguments(1, collection222, 0, 1),
                arguments(11, collection555, 10, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("setTest_Nominal")
    void setTest(int expected, IList collection, int index, int value) {
        collection.set(index, value);
        int actual = collection.get(index);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setTestException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.set(-15, 25));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void setTestException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection444.set(50, 12));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void printTest() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        collection1.print();
        collection2.print();
        collection11.print();
        collection33.print();
        collection111.print();
        collection444.print();
    }

    static Stream<Arguments> toArrayTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(collection1, new int []{}),
                arguments(collection2, new int []{1}),
                arguments(collection3, new int []{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                arguments(collection11, new int []{}),
                arguments(collection44, new int []{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                arguments(collection55, new int []{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}),
                arguments(collection111, new int []{}),
                arguments(collection222, new int []{1}),
                arguments(collection555, new int []{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("toArrayTest_Nominal")
    void toArrayTest(IList collection, int[] expected) {
        int[] actual = collection.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> removeAllTest_Nominal() {
        addSomethingToCollections(collection2, collection3, collection4, collection5);
        addSomethingToCollections(collection22, collection33, collection44, collection55);
        return Stream.of(
                arguments(false, new int[]{}, collection1),
                arguments(true, new int[]{1}, collection2),
                arguments(false, null, collection2),
                arguments(true, new int[]{2,4,6}, collection3),
                arguments(false, new int[]{3}, collection11),
                arguments(true, new int[]{15,24,3,4,5}, collection44),
                arguments(true, new int[]{1,12,3,41,45}, collection55)
//                arguments(false, new int[]{}, collection111)
        );
    }

    @ParameterizedTest
    @MethodSource("removeAllTest_Nominal")
    void removeAllTest(boolean expected, int[] ints, IList collection) {
        boolean actual = collection.removeAll(ints);
        Assertions.assertEquals(expected,actual);
    }
}