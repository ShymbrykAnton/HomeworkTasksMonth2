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

    //отрабатывает всего один раз? или при ините если в дереве что-то было то оно затирается?
    @Override
    public void init(int[] ar) {

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
        if (root == null) {
            return;
        }
        clear(root);
        root = null;
        // проверить какой элемент остается
    }

    private void clear(Node startElement) {
        if (startElement.right == null && startElement.left == null) {
            return;
        }
        clear(startElement.left);
        startElement.left = null;
        clear(startElement.right);
        startElement.right = null;
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

//    private List<Node> toList(Node startElement) {
//        List<Node> result = new ArrayList<>();
//        if (startElement == null) {
//            return result;
//        }
//        if (startElement.left != null) {
//            result.addAll(toList(startElement.left));
//        }
//        if (startElement.right != null) {
//            result.addAll(toList(startElement.right));
//        }
//        return result;
//    }

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
        return 0;
    }

    @Override
    public int nodes() {
        return 0;
    }

    @Override
    public int leaves() {
        return 0;
    }

    @Override
    public void reverse() {

    }
}
