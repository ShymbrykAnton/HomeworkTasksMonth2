package ilist.alist2;

import java.util.Arrays;

import static ilist.utils.Constants.*;
import static ilist.utils.Constants.INDEX_EXCEPTION;

public class ArrayList2<E> implements IGeneric<E> {
    private Object[] array;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public ArrayList2() {
        size = 0;
        array = new Object[capacity];
    }

    public ArrayList2(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(LOW_CAPACITY);
        } else {
            size = 0;
            array = new Object[capacity];
            this.capacity = capacity;
        }
    }

    public ArrayList2(E[] array) {
        capacity = array.length;
        size = capacity;
        this.array = array;
    }

    public void clear() {
        array = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        isIndexSuitable(index);
        return (E) array[index];
    }

    public boolean add(E value) {
        if (value == null) {
            return false;
        }
        increaseArrayCapacityIfRequired();
        array[size] = value;
        size++;
        return true;
    }

    public boolean add(int index, E value) {
        if (value == null || index < 0 || index > size) {
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

    public E remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        int index = getIndexByValue(value);
        if (index == -1) {
            throw new IllegalArgumentException(NO_NUMBER_IN_COLLECTION);
        }
        return removeByIndex(index);
    }

    public E removeByIndex(int index) {
        isIndexSuitable(index);
        Object result = array[index];
        if (size == 1) {
            size--;
            array = new Object[capacity];
            return (E) result;
        }
        size--;
        for (int count = index; count < size; count++) {
            array[count] = array[count + 1];
        }
        return (E) result;
    }

    public boolean contains(E value) {
        if (value == null) {
            return false;
        }
        return getIndexByValue(value) >= 0;
    }

    public boolean set(int index, E value) {
        if (value == null || index < 0 || index > size || Arrays.equals(toArray(), new Object[]{})) {
            return false;
        }
        array[index] = value;
        return true;
    }

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

    public E[] toArray() {
        if (size == 0) {
            array = new Object[DEFAULT_CAPACITY];
            return (E[]) new Object[]{};
        }
        Object[] collectionToArray = new Object[size];
        for (int count = 0; count < size; count++) {
            collectionToArray[count] = array[count];
        }
        return (E[])collectionToArray;
    }

    public boolean removeAll(E[] objects) {
        if (objects == null) {
            return false;
        }
        if (Arrays.equals(toArray(), objects)) {
            array = new Object[DEFAULT_CAPACITY];
            size = 0;
            return true;
        }
        int removeCount = 0;
        for (Object anObject : objects) {
            int index = getIndexByValue((E)(anObject));
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
        Object[] saveArray = array;
        int changedCapacity = (int) (capacity * COEFFICIENT);
        this.capacity = changedCapacity <= capacity ? capacity + 1 : changedCapacity;
        array = new Object[capacity];
        for (int count = 0; count < size; count++) {
            array[count] = saveArray[count];
        }
    }

    private int getIndexByValue(E value) {
        for (int count = 0; count < size; count++) {
            if (array[count].equals(value)) {
                return count;
            }
        }
        return -1;
    }

    // там где велью сравнения просмотреть
    private void isIndexSuitable(int index) {
        if (Arrays.equals(toArray(), new Object[]{})) {
            throw new IllegalArgumentException(EMPTY_COLLECTION_EXCEPTION);
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(INDEX_EXCEPTION);
        }
    }
}
