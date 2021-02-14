package log4j.blogic;

import log4j.exception.FirstException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static log4j.util.Constants.*;

import java.util.Random;


public class RandomGenerator {

    Logger logger = LogManager.getLogger(RandomGenerator.class);

    public int generate(){
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
