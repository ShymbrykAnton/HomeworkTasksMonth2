package log4j.exception;

public class FirstException extends Throwable {
    private final int number;

    public FirstException(String message, int number) {
        super(message + number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
