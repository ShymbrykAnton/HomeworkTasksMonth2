package ilist.impl;

import ilist.IList;

import java.util.Arrays;

import static ilist.utils.Constants.*;

public class ArrayList1 implements IList {
    private int[] array;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public ArrayList1() {
        size = 0;
        array = new int[capacity];
    }

    public ArrayList1(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Вместимость коллекции не может быть меньше одного");
        } else {
            size = 0;
            array = new int[capacity];
            this.capacity = capacity;
        }
    }

    public ArrayList1(int[] array) {
        capacity = array.length;
        size = capacity;
        this.array = array;
    }

    @Override
    public void clear() {
        array = new int[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        isIndexSuitable(index);
        return array[index];
    }

    @Override
    public boolean add(int value) {
        increaseArrayCapacityIfRequired();
        array[size] = value;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        increaseArrayCapacityIfRequired();
        for (int count = size; count > index; count--) {
            array[count] = array[count - 1];
        }
        array[index] = value;
        size++;
        return true;
    }


    @Override
    public int remove(int number) {
        int index = getIndexByValue(number);
        if (index == -1) {
            throw new IllegalArgumentException("Данного числа нет в коллекции");
        }
        return removeByIndex(index);
    }

    @Override
    public int removeByIndex(int index) {
        isIndexSuitable(index);
        int result = array[index];
        if (size == 1) {
            array = new int[]{};
        }
        size--;
        for (int count = index; count < size; count++) {
            array[count] = array[count + 1];
        }
        return result;
    }

    @Override
    public boolean contains(int value) {
        return getIndexByValue(value) >= 0;
    }

    @Override
    public boolean set(int index, int value) {
        if (index < 0 || index > size || Arrays.equals(toArray(), new int[]{})) {
            return false;
        }
        array[index] = value;
        return true;
    }

    @Override
    public void print() {
        if (size == 0) {
            System.out.println(EMPTY_ARRAY);
            return;
        }
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(LEFT_BRACKET);
        for (int count = 0; count < size; count++) {
            if (count != size - 1) {
                result.append(array[count]).append(COMMA_AND_SPACE);
            } else {
                result.append(array[count]).append(RIGHT_BRACKET);
            }
        }
        return result.toString();
    }

    @Override
    public int[] toArray() {
        if (size == 0) {
            array = new int[DEFAULT_CAPACITY];
            return new int[]{};
        }
        int[] collectionToArray = new int[size];
        for (int count = 0; count < size; count++) {
            collectionToArray[count] = array[count];
        }
        return collectionToArray;
    }

    @Override
    public boolean removeAll(int[] ints) {
        if (ints == null) {
            return false;
        }
        if (Arrays.equals(toArray(), ints)) {
            array = new int[DEFAULT_CAPACITY];
            return true;
        }
        int removeCount = 0;
        for (int anInt : ints) {
            int index = getIndexByValue(anInt);
            if (index != -1) {
                removeByIndex(index);
                removeCount++;
            }
        }
        return removeCount != 0;
    }

    private void increaseArrayCapacityIfRequired() {
        if (size < array.length - 1) {
            return;
        }
        int[] saveArray = array;
        int changedCapacity = (int) (capacity * COEFFICIENT);
        this.capacity = changedCapacity <= capacity ? capacity + 1 : changedCapacity;
        array = new int[capacity];
        for (int count = 0; count < size; count++) {
            array[count] = saveArray[count];
        }
    }

    private int getIndexByValue(int value) {
        for (int count = 0; count < size; count++) {
            if (array[count] == value) {
                return count;
            }
        }
        return -1;
    }

    private void isIndexSuitable(int index) {
        if (Arrays.equals(toArray(), new int[]{})) {
            throw new IllegalArgumentException("Пустой массив");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Индекс не может быть больше (или равным?) количества элементов" +
                    " или меньше нуля");
        }
    }
}
