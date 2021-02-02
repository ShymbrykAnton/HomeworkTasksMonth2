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
        System.out.println(binaryTreeRecursive.size());
        binaryTreeRecursive.add(5);
        System.out.println(binaryTreeRecursive.size());
        binaryTreeRecursive.add(4);
        System.out.println(binaryTreeRecursive.size());
        binaryTreeRecursive.add(1);
        binaryTreeRecursive.add(2);
        binaryTreeRecursive.add(3);
        binaryTreeRecursive.add(7);
        binaryTreeRecursive.add(8);

      /*
                             5
                          4     7
                        1          8
                          2
                            3
       */

        System.out.println(binaryTreeRecursive.size());
        binaryTreeRecursive.print();
        System.out.println(binaryTreeRecursive.size());

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