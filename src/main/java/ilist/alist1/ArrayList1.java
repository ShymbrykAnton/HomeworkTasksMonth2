package ilist.alist1;

import ilist.IList;

import java.util.Arrays;

public class ArrayList1 implements IList {
    private int[] array;
    private final static int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public ArrayList1() {
        array = new int[DEFAULT_CAPACITY];
    }

    public ArrayList1(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            array = new int[DEFAULT_CAPACITY];
        } else {
            array = new int[capacity];
            this.capacity = capacity;
        }
    }

    public ArrayList1(int[] array) {
        this.array = array;
        this.size = array.length;
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
        if (size == array.length) {
            increaseOrDecreaseArrayCapacity(capacity += 10);
        }
        if (size < array.length) {
            array[size++] = value;
            return true;
        }
        return false;
    }

    private void increaseOrDecreaseArrayCapacity(int capacity) {
        this.capacity = capacity;
        int[] saveArray = new int[array.length];
        if (capacity == 0) {
            this.capacity = 10;
        }
        for (int count = 0; count < size; count++) {
            saveArray[count] = array[count];
        }
        array = new int[this.capacity];
        for (int count2 = 0; count2 < saveArray.length; count2++) {
            array[count2] = saveArray[count2];
        }
    }

    @Override
    public boolean add(int index, int value) {
        isIndexSuitable(index);
        if (size == array.length) {
            increaseOrDecreaseArrayCapacity(capacity += 10);
        }
        if (size < array.length) {
            if (size + 1 == capacity) {
                increaseOrDecreaseArrayCapacity(capacity += 10);
            }
            for (int count = size; count > index; count--) {
                array[count + 1] = array[count];
            }
            array[index] = value;
            size++;
            return true;
        }
        return false;
    }


    @Override
    public int remove(int number) {
        int result = Integer.MIN_VALUE;
        int savedCount = 0;
        boolean flag = false;
        if (!contains(number)) {
            return result;
        }
        size--;
        if (size == capacity) {
            increaseOrDecreaseArrayCapacity(capacity += 10);
        }
        for (int count = 0; count < size + 1; count++) {
            if (array[count] == number) {
                savedCount = count;
                result = array[count];
                array[count] = 0;
                flag = true;
            }
            if (flag && count >= savedCount && count + 1 < array.length) {
                array[count] = array[count + 1];
            }
        }
        return result;
    }

    @Override
    public int removeByIndex(int index) {
        isIndexSuitable(index);
        int result = array[index];
        array[index] = 0;
        size--;
        for (int count = index; count < size; count++) {
            array[count] = array[count + 1];
        }
        if (size == capacity) {
            increaseOrDecreaseArrayCapacity(capacity -= 10);
        }
        return result;
    }

    @Override
    public boolean contains(int value) {
        for (int count = 0; count < size; count++) {
            if (array[count] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, int value) {
        isIndexSuitable(index);
        array[index] = value;
        return true;
    }

    private void isIndexSuitable(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Индекс не может быть больше количества элементов" +
                    " или меньше нуля");
        }
    }

    @Override
    public void print() {
        if (size == 0) {
            System.out.println("[ ]");
            return;
        }
        StringBuilder result = new StringBuilder("[ ");
        for (int count = 0; count < size; count++) {
            if (count != size - 1) {
                result.append(array[count]).append(", ");
            } else {
                result.append(array[count]).append(" ]");
            }
        }
        System.out.println(result);
    }

    @Override
    public int[] toArray() {
        int[] collectionToArray = new int[size];
        for (int count = 0; count < collectionToArray.length; count++) {
            collectionToArray[count] = array[count];
        }
        return collectionToArray;
    }

    @Override
    public boolean removeAll(int[] ints) {
        if (ints == null) {
            return false;
        }
        int[] arrayCompare = toArray();
        if (Arrays.equals(arrayCompare, new int[]{})) {
            return false;
        }
        if (Arrays.equals(arrayCompare, ints)) {
            array = new int[DEFAULT_CAPACITY];
            return true;
        }
        final int[] savedArray = array;
        for (int count = 0; count < ints.length; count++) {
            for (int count2 = 0; count2 < size; count2++) {
                if (ints[count] == array[count2]) {
                    removeByIndex(count2);
                }
            }
        }
        //remove by index - неправильный. ыейвд эрей такой же как и эррей
        return !Arrays.equals(savedArray, array);
    }
}
