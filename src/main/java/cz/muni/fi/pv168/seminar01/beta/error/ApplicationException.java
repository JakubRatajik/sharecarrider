package cz.muni.fi.pv168.seminar01.beta.error;

/**
 * Interface for exceptions with error message displayable to user
 */
public interface ApplicationException {

    /**
     * @return error message displayable to user
     */
    String getUserMessage();
}
