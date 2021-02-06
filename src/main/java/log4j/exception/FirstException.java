package log4j;

public class FirstException extends Throwable{
    private final int number;
    FirstException(String message, int number) {
        super(message+number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
