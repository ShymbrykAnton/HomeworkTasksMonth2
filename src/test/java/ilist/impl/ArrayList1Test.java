package ilist.impl;

import ilist.IList;
import ilist.impl.ArrayList1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArrayList1Test {
    private static final IList collection0 = new ArrayList1();
    private static final IList collection1 = new ArrayList1();

    private static final IList collection9 = new ArrayList1(24);
    private static final IList collection10 = new ArrayList1(48);

    private static IList collection12 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});

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
        if (!Arrays.equals(collection12.toArray(), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})) {
            collection12 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
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
    void clearTest(IList collection) {
        collection.clear();
        int actual = collection.size();
        int expected = collection0.size();
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
    void sizeTest(int expected, IList collection) {
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
    void getTest(int expected, IList collection, int index) {
        int actual = collection.get(index);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> addTest_Nominal() {
        return Stream.of(
                arguments(1, collection0, new int[]{1}),
                arguments(5, collection1, new int[]{1, 5}),
                arguments(2, collection9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 2}),
                arguments(46, collection10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 46}),
                arguments(13, collection12, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
        );
    }

    @ParameterizedTest
    @MethodSource("addTest_Nominal")
    void addValueTest(int value, IList collection, int[] expected1) {
        collection.add(value);
        int[] actual1 = collection.toArray();
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> addValueByIndexTest_Nominal() {
        return Stream.of(
                arguments(false, 1, collection0, -5, new int[]{}),
                arguments(false, 5, collection1, 5, new int[]{1}),
                arguments(true, 15, collection0, 0, new int[]{15}),
                arguments(true, 5, collection1, 0, new int[]{5, 1}),
                arguments(true, 2, collection9, 5, new int[]{1, 2, 3, 4, 5, 2, 6, 7, 8, 9}),
                arguments(true, 46, collection10, 9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 46, 10}),
                arguments(true, 13, collection12, 7, new int[]{1, 2, 3, 4, 5, 6, 7, 13, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("addValueByIndexTest_Nominal")
    void addValueByIndexTest(boolean expected, int value, IList collection, int index, int[] expected1) {
        boolean actual = collection.add(index, value);
        int[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    static Stream<Arguments> removeTest_Nominal() {
        if (!Arrays.equals(collection12.toArray(), new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12})) {
            collection12 = new ArrayList1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        }
        return Stream.of(
                arguments(1, collection1, 1, new int[]{}),
                arguments(5, collection9, 5, new int[]{1, 2, 3, 4, 6, 7, 8, 9}),
                arguments(9, collection10, 9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10}),
                arguments(5, collection12, 5, new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeTest_Nominal")
    void removeTest(int number, IList collection, int expected, int[] expected1) {
        int actual = collection.remove(number);
        int[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }


    static Stream<Arguments> removeByIndexTest_Nominal() {
        return Stream.of(
                arguments(1, collection1, 0, new int[]{}),
                arguments(6, collection9, 5, new int[]{1, 2, 3, 4, 5, 7, 8, 9}),
                arguments(10, collection10, 9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                arguments(8, collection12, 7, new int[]{1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeByIndexTest_Nominal")
    void removeByIndexTest(int expected, IList collection, int index, int[] expected1) {
        int actual = collection.removeByIndex(index);
        int[] actual1 = collection.toArray();
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
    void containsTest(boolean expected, IList collection, int value) {
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
    void setTest(boolean expected, IList collection, int index, int value) {
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

    //
    static Stream<Arguments> toArrayTest_Nominal() {
        return Stream.of(
                arguments(collection0, new int[]{}),
                arguments(collection1, new int[]{1}),
                arguments(collection9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                arguments(collection10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                arguments(collection12, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("toArrayTest_Nominal")
    void toArrayTest(IList collection, int[] expected) {
        int[] actual = collection.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> removeAllTest_Nominal() {
        return Stream.of(
                arguments(false, collection1, null, new int[]{1}),
                arguments(true, collection1, new int[]{1}, new int[]{0}),
                arguments(true, collection9, new int[]{1, 4, 5}, new int[]{2, 3, 6, 7, 8, 9}),
                arguments(true, collection10, new int[]{9, 123, 2}, new int[]{1, 3, 4, 5, 6, 7, 8, 10}),
                arguments(false, collection12, new int[]{100, -5, 13}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("removeAllTest_Nominal")
    void removeAllTest(boolean expected, IList collection, int[] ints, int[] expected1) {
        boolean actual = collection.removeAll(ints);
        int[] actual1 = collection.toArray();
        Assertions.assertEquals(expected, actual);
        Assertions.assertArrayEquals(expected1, actual1);
    }


    @Test
    void getException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection1.get(-15));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void getException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection9.get(10));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void getException3Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection0.get(0));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void removeException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection9.remove(15));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void removeByIndexException1Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection9.removeByIndex(10));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void removeByIndexException2Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection12.removeByIndex(-2));
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void removeByIndexException3Test() throws IllegalArgumentException {
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> collection0.removeByIndex(0));
        Assertions.assertNotNull(thrown.getMessage());
    }
}