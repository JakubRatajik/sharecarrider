package cz.muni.fi.pv168.seminar01.beta.data.validation;

/**
 * @author Jakub Ratajik
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    private ValidationException() {
    }
}
