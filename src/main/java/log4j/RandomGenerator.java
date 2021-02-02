package log4j;

import java.util.Random;

import org.apache.log4j.Logger;


public class RandomGenerator {
    private final Logger logger = Logger.getLogger(RandomGenerator.class);

    public int generate() throws FirstException {
        Random random = new Random();
        int result = random.nextInt(10);
        if (result <= 5) {
            throw new FirstException("");
        }
        return random.nextInt(10);
    }
}
