package cz.muni.fi.pv168.seminar01.beta.Data.Manipulation;

/**
 * Exception thrown if there is some problem with data import/export.
 */
public final class DataManipulationException extends RuntimeException {

    public DataManipulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
