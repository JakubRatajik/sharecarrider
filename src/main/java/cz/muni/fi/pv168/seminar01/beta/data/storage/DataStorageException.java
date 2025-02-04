package cz.muni.fi.pv168.seminar01.beta.data.storage;


import cz.muni.fi.pv168.seminar01.beta.error.RuntimeApplicationException;

/**
 * Exception that is thrown if there is some problem with data storage
 */
public class DataStorageException extends RuntimeApplicationException {

    private static final long serialVersionUID = 0L;

    public DataStorageException(String message) {
        this(message, null);
    }

    public DataStorageException(String message, Throwable cause) {
        this("Problem while interacting with database" , message, cause);
    }

    public DataStorageException(String userMessage, String message, Throwable cause) {
        super(userMessage, "Storage error: " +  message, cause);
    }
}
