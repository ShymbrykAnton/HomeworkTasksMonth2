import log4j.blogic.RandomGenerator;
import simplepaintswing.view.PaintView;
import trees.ITree;
import trees.impl.BinaryTreeRecursive;

public class Main {
    public static void main(String[] args){
        RandomGenerator randomGenerator = new RandomGenerator();
        System.out.println(randomGenerator.generate());
    }
}
