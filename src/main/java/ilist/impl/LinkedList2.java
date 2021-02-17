package ilist.impl;

import ilist.IList;

import java.util.Arrays;

import static ilist.utils.Constants.*;

public class LinkedList2 implements IList {
    private Node root = null;
    private Node tail = null;
    private int size = 0;

    static class Node {
        int value;
        Node next;
        Node prev;

        public Node(Node prev, int value, Node next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        if (isIndexCorrect(index) == -1) {
            throw new IllegalArgumentException(INDEX_EXCEPTION);
        }
        boolean isStartFromRoot = index < size / 2 + size % 2;
        return getNodeByIndex(isStartFromRoot ? root : tail, isStartFromRoot ? 0 : size - 1, index).value;
    }

    private Node getNodeByIndex(Node startElement, int startIndex, int index) {
        if (startIndex == size / 2 + size % 2 && startIndex != index) {
            throw new IllegalArgumentException(NO_NUMBER_IN_COLLECTION);
        } else if (index == startIndex) {
            return startElement;
        } else if (index < size / 2 + size % 2) {
            return getNodeByIndex(startElement.next, startIndex + 1, index);
        } else {
            return getNodeByIndex(startElement.prev, startIndex - 1, index);
        }
    }

    @Override
    public boolean add(int value) {
        Node newElement = new Node(null, value, null);
        size++;
        if (root == null) {
            root = newElement;
        } else {
            newElement.prev = tail;
            tail.next = newElement;
        }
        tail = newElement;
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        if (root == null) {
            add(value);
            return true;
        } else if (index == 0) {
            Node newNode = new Node(null, value, root);
            root.prev = newNode;
            root = newNode;
        } else if (index == size - 1) {
            Node newNode = new Node(tail.prev, value, tail);
            tail.prev.next = newNode;
            tail = newNode.next;
        } else {
            boolean isStartFromRoot = index < size / 2 + size % 2;
            Node ourNode = getNodeByIndex(isStartFromRoot ? root : tail, isStartFromRoot ? 0 : size - 1, index);
            Node newNode = new Node(ourNode.prev, value, ourNode);
            ourNode.prev.next = newNode;
            ourNode.prev = newNode;
        }
        size++;
        return true;
    }

    @Override
    public int remove(int number) {
        removeByIndex(getIndexByValue(root, tail, 0, size - 1, number));
        return number;
    }

    @Override
    public int removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(INDEX_EXCEPTION);
        }
        int ourValue;
        if (root.next == null) {
            ourValue = root.value;
            root = null;
        } else if (index == 0) {
            ourValue = root.value;
            root = root.next;
            root.prev = null;
        } else if (index == size - 1) {
            ourValue = tail.value;
            tail = tail.prev;
            tail.next = null;
        } else {
            boolean isStartFromRoot = index < size / 2 + size % 2;
            Node ourNode = getNodeByIndex(isStartFromRoot ? root : tail, isStartFromRoot ? 0 : size - 1, index);
            ourValue = ourNode.value;
            ourNode.next.prev = ourNode.prev;
            ourNode.prev.next = ourNode.next;
        }
        size--;
        return ourValue;
    }

    @Override
    public boolean contains(int value) {
        return root != null && getIndexByValue(root, tail, 0, size - 1, value) >= 0;
    }

    private int getIndexByValue(Node startElement, Node endElement, int startIndex, int endIndex, int value) {
        if (root!=null&&root.value == value) {
            return startIndex;
        }
        if (startIndex == size / 2 + size % 2) {
            return -1;
        }
        if (startElement.value == value) {
            return startIndex;
        }
        if (endElement.value == value) {
            return endIndex;
        }
        return getIndexByValue(startElement.next, endElement.prev, startIndex + 1, endIndex - 1, value);
    }

    @Override
    public boolean set(int index, int value) {
        if (isIndexCorrect(index) == -1 || root == null) {
            return false;
        }
        boolean isStartFromRoot = index < size / 2 + size % 2;
        set(isStartFromRoot ? root : tail, isStartFromRoot ? 0 : size - 1, index, value);
        return true;
    }

    private void set(Node startElement, int startIndex, int index, int value) {
        if (startIndex == index) {
            startElement.value = value;
            return;
        }
        if (index < size / 2 + size % 2) {
            set(startElement.next, startIndex + 1, index, value);
        } else {
            set(startElement.prev, startIndex - 1, index, value);
        }
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
        int[] array = toArray();
        for (int value : array) {
            if (value == array[array.length - 1]) {
                result.append(value);
                break;
            }
            result.append(value).append(COMMA_AND_SPACE);
        }
        result.append(RIGHT_BRACKET);
        return result.toString();
    }

    @Override
    public int[] toArray() {
        if (root == null) {
            return new int[]{};
        }
        int[] array = new int[size()];
        return toArray(root, 0, array);
    }

    private int[] toArray(Node element, int currentIndex, int[] array) {
        if (currentIndex == size - 1) {
            array[currentIndex] = element.value;
            return array;
        }
        array[currentIndex] = element.value;
        return toArray(element.next, currentIndex + 1, array);
    }

    @Override
    public boolean removeAll(int[] array) {
        if (array == null || Arrays.equals(array, new int[]{})) {
            return true;
        }
        boolean flag = false;
        for (int value : array) {
            if (contains(value)) {
                remove(value);
                flag = true;
            }
        }
        return flag;
    }

    private int isIndexCorrect(int index) {
        if (index >= size || index < 0) {
            return -1;
        }
        return index;
    }
}