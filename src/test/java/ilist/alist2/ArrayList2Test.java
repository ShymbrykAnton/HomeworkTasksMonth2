package ilist.alist2;

import ilist.IList;
import ilist.impl.ArrayList1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArrayList2Test {
    private static final IGeneric<Integer> collection0 = new ArrayList2<>();
    private static final IGeneric<Integer> collection1 = new ArrayList2<>();
    private static final IGeneric<Integer> collection9 = new ArrayList2<>(24);
    private static final IGeneric<Integer> collection10 = new ArrayList2<>(48);
    private static ArrayList2<Integer> collection12 = new ArrayList2<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});

    @BeforeEach
    private void initializeCollection() {
        collection1.add(1);

        collection9.add(1);
        collection9.add(2);
        collection9.add(3);
        collection9.add(4);
        collection9.add(5);
        collection9.add(6);
        collection9.add(7);
        collection9.add(8);
        collection9.add(9);

        collection10.add(1);
        collection10.add(2);
        collection10.add(3);
        collection10.add(4);
        collection10.add(5);
        collection10.add(6);
        collection10.add(7);
        collection10.add(8);
        collection10.add(9);
        collection10.add(10);
    }

    @AfterEach
    private void clearCollection() {
        collection0.clear();
        collection1.clear();
        collection9.clear();
        collection10.clear();
        if (!Arrays.equals(collection12.toArray(), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})) {
            collection12 = new ArrayList2<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        }
    }

    static Stream<Arguments> clearTest_Nominal() {
        return Stream.of(
                arguments(collection0),
                arguments(collection1),
                arguments(collection9),
                arguments(collection10),
                arguments(collection12)
        );
    }

    @ParameterizedTest
    @MethodSource("clearTest_Nominal")
    void clearTest(IGeneric<Integer> collection) {
        collection.clear();
        int actual = collection.size();
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> sizeTest_Nominal() {
        return Stream.of(
                arguments(0, collection0),
                arguments(1, collection1),
                arguments(9, collection9),
                arguments(10, collection10),
                arguments(12, collection12)
        );
    }

    @ParameterizedTest
    @MethodSource("sizeTest_Nominal")
    void sizeTest(int expected, IGeneric<Integer> collection) {
        int actual = collection.size();
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> getTest_Nominal() {
        return Stream.of(
                arguments(1, collection1, 0),
                arguments(2, collection9, 1),
                arguments(10, collection10, 9),
                arguments(7, collection12, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("getTest_Nominal")
    void getTest(int expected, IGeneric<Integer> collection, int index) {
        int actual = collection.get(index);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> addTest_Nominal() {
        return Stream.of(
                arguments(1, collection0, new Object[]{1}),
                arguments(5, collection1, new Object[]{1, 5}),
                arguments(2, collection9, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 2}),
                arguments(46, collection10, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 46}),
                arguments(13, collection12, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
        );
    }

    @ParameterizedTest
    @MethodSource("addTest_Nominal")
    void addValueTest(int value, IGeneric<Integer> collection, Object[] expected) {
        collection.add(value);
        Object[] actual = collection.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> addValueByIndexTest_Nominal() {
        return Stream.of(
                arguments(true, 1, collection0, 0, new Object[]{1}),
                arguments(false, 1, collection0, -5, new Object[]{}),
                arguments(false, 5, collection1, 5, new Object[]{1}),
                arguments(true, 15, collection0, 0, new Object[]{15}),
                arguments(true, 5, collection1, 0, new Object[]{5, 1}),
                arguments(true, 2, collection9, 5, new Object[]{1, 2, 3, 4, 5, 2, 6, 7, 8, 9}),
                arguments(true, 46, collection10, 9, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 46, 10}),
                arguments(true, 13, collection12, 7, new Object[]{1, 2, 3, 4, 5, 6, 7, 13, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("addValueByIndexTest_Nominal")
    void addValueByIndexTest(boolean expected, int value, IGeneric<Integer> collection, int index, Object[] expected1) {
        boolean actual = collection.add(index, value);
        Object[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> removeTest_Nominal() {
        if (!Arrays.equals(collection12.toArray(), new Integer[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12})) {
            collection12 = new ArrayList2<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        }
        return Stream.of(
                arguments(1, collection1, 1, new Object[]{}),
                arguments(5, collection9, 5, new Object[]{1, 2, 3, 4, 6, 7, 8, 9}),
                arguments(9, collection10, 9, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 10}),
                arguments(5, collection12, 5, new Object[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeTest_Nominal")
    void removeTest(int number, IGeneric<Integer> collection, int expected, Object[] expected1) {
        int actual = collection.remove(number);
        Object[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> removeByIndexTest_Nominal() {
        return Stream.of(
                arguments(1, collection1, 0, new Object[]{}),
                arguments(6, collection9, 5, new Object[]{1, 2, 3, 4, 5, 7, 8, 9}),
                arguments(10, collection10, 9, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                arguments(8, collection12, 7, new Object[]{1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeByIndexTest_Nominal")
    void removeByIndexTest(int expected, IGeneric<Integer> collection, int index, Object[] expected1) {
        int actual = collection.removeByIndex(index);
        Object[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> containsTest_Nominal() {
        return Stream.of(
                arguments(false, collection0, 0),
                arguments(false, collection1, 0),
                arguments(true, collection9, 5),
                arguments(true, collection10, 9),
                arguments(false, collection12, 78)
        );
    }

    @ParameterizedTest
    @MethodSource("containsTest_Nominal")
    void containsTest(boolean expected, IGeneric<Integer> collection, int value) {
        boolean actual = collection.contains(value);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> setTest_Nominal() {
        return Stream.of(
                arguments(false, collection0, 0, 5),
                arguments(false, collection1, -5, 10),
                arguments(true, collection9, 5, 30),
                arguments(true, collection10, 9, 50),
                arguments(false, collection12, -55, 32)
        );
    }

    @ParameterizedTest
    @MethodSource("setTest_Nominal")
    void setTest(boolean expected, IGeneric<Integer> collection, int index, int value) {
        boolean actual = collection.set(index, value);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printTest() {
        collection0.print();
        collection1.print();
        collection9.print();
        collection10.print();
        collection12.print();
    }

    static Stream<Arguments> toArrayTest_Nominal() {
        return Stream.of(
                arguments(collection0, new Object[]{}),
                arguments(collection1, new Object[]{1}),
                arguments(collection9, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                arguments(collection10, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                arguments(collection12, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("toArrayTest_Nominal")
    void toArrayTest(IGeneric<Integer> collection, Object[] expected) {
        Object[] actual = collection.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> removeAllTest_Nominal() {
        return Stream.of(
                arguments(false, collection1, null, new Object[]{1}),
                arguments(true, collection1, new Integer[]{1}, new Object[]{}),
                arguments(true, collection9, new Integer[]{1, 4, 5}, new Object[]{2, 3, 6, 7, 8, 9}),
                arguments(true, collection10, new Integer[]{9, 123, 2}, new Object[]{1, 3, 4, 5, 6, 7, 8, 10}),
                arguments(false, collection12, new Integer[]{100, -5, 13}, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeAllTest_Nominal")
    void removeAllTest(boolean expected, IGeneric<Integer> collection, Integer[] ints, Object[] expected1) {
        boolean actual = collection.removeAll(ints);
        Object[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> ExceptionTest_Nominal() {
        Executable executable;
        return Stream.of(
                arguments(executable = () -> collection1.get(-15)),
                arguments(executable = () -> collection9.get(10)),
                arguments(executable = () -> collection0.get(0)),
                arguments(executable = () -> collection9.remove(15)),
                arguments(executable = () -> collection0.removeByIndex(0)),
                arguments(executable = () -> collection12.removeByIndex(-2)),
                arguments(executable = () -> collection9.removeByIndex(10))
        );
    }

    @ParameterizedTest
    @MethodSource("ExceptionTest_Nominal")
    void getException1Test(Executable executable) throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, executable);
        Assertions.assertNotNull(thrown.getMessage());
    }
}