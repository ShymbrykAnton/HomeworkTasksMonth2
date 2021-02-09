package trees.impl;

import ilist.IList;
import ilist.impl.LinkedList1;
import trees.ITree;

import static ilist.utils.Constants.*;

public class BinaryTreeRecursive implements ITree {
    Node root;

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    @Override
    public void init(int[] ar) {
        for (int i : ar) {
            add(i);
        }
    }

    @Override
    public void print() {
        if (size() == 0) {
            System.out.println(EMPTY_ARRAY);
            return;
        }
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(LEFT_BRACKET);
        int size = size();
        int[] array = toArray();
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
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        int size = 1;
        return size(root, size);
    }

    private int size(Node startElement, int size) {
        if (startElement.left != null) {
            size = size(startElement.left, ++size);
        }
        if (startElement.right != null) {
            size = size(startElement.right, ++size);
        }
        return size;
    }

    @Override
    public int[] toArray() {
        if (root == null) {
            return new int[]{};
        }
        IList list = new LinkedList1();
        return toArray(root, list);
    }

    private int[] toArray(Node startElement, IList list) {
        if (startElement == null) {
            return list.toArray();
        }
        toArray(startElement.left, list);
        list.add(startElement.value);
        toArray(startElement.right, list);
        return list.toArray();
    }

    @Override
    public void add(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        add(root, val);
    }

    private void add(Node startElement, int value) {
        if (value < startElement.value) {
            if (startElement.left == null) {
                startElement.left = new Node(value);
                return;
            }
            add(startElement.left, value);
        } else if (value > startElement.value) {
            if (startElement.right == null) {
                startElement.right = new Node(value);
                return;
            }
            add(startElement.right, value);
        }
    }

    @Override
    public void del(int val) {
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node startElement) {
        if (startElement == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(startElement.left), getHeight(startElement.right));
    }

    @Override
    public int nodes() {
        return size() - leaves();
    }


    @Override
    public int leaves() {
        return leaves(root, 0);
    }

    private int leaves(Node startElement, int leaves) {
        if (startElement == null) {
            return leaves;
        }
        leaves = leaves(startElement.left, leaves);
        leaves = leaves(startElement.right, leaves);
        if (startElement.right == null && startElement.left == null) {
            ++leaves;
        }
        return leaves;
    }

    @Override
    public void reverse() {

    }
}
