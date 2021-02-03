package trees.impl;

import org.junit.jupiter.api.Test;
import trees.ITree;

class BinaryTreeRecursiveTest {
    ITree binaryTreeRecursive = new BinaryTreeRecursive();

    @Test
    void init() {
    }

    @Test
    void print() {
    }

    @Test
    void clear() {
    }

    @Test
    void size() {
    }

    @Test
    void toArray() {
    }

    @Test
    void addTest() {
        binaryTreeRecursive.add(5);
        binaryTreeRecursive.add(4);
        binaryTreeRecursive.add(2);
        binaryTreeRecursive.add(1);
        binaryTreeRecursive.add(3);
        binaryTreeRecursive.add(7);
        binaryTreeRecursive.add(8);
        binaryTreeRecursive.add(9);

      /*
                             5
                          4     7
                        2          8
                      1   3          9

       */
        binaryTreeRecursive.print();
        System.out.println(binaryTreeRecursive.size());
        System.out.println(binaryTreeRecursive.getHeight());
//        binaryTreeRecursive.print();
//        System.out.println(binaryTreeRecursive.size());

    }

    @Test
    void del() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void nodes() {
    }

    @Test
    void leaves() {
    }

    @Test
    void reverse() {
    }
}