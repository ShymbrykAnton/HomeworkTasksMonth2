package log4j;

import org.apache.log4j.Logger;

import static log4j.util.Constants.*;

import java.util.Random;



public class RandomGenerator {
    Logger logger = Logger.getLogger(RandomGenerator.class);
    public int generate() throws FirstException {
        Random random = new Random();
        int result = random.nextInt(10);
        try {
            if (result <= 5) {
                throw new FirstException(GENERATED_NUMBER, result);
            }
        } catch (FirstException e) {
            logger.error(e.getMessage());
            System.exit(0);
        }
        logger.info(APPLICATION_IS_FINE);
        return result;
    }
}
