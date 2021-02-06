import log4j.blogic.RandomGenerator;

public class Main {
    public static void main(String[] args){
        RandomGenerator randomGenerator = new RandomGenerator();
        System.out.println(randomGenerator.generate());
    }
}
