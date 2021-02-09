import log4j.blogic.RandomGenerator;
import trees.ITree;
import trees.impl.BinaryTreeRecursive;

public class Main {
    public static void main(String[] args){
        ITree iTree = new BinaryTreeRecursive();
        iTree.init(new int[]{2000,1500,3000,700,1700,-500,2500});
        iTree.print();
    }
}
