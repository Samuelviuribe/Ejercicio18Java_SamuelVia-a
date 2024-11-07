package Business.Exceptions;

/**
 *
 * @author HP
 */
public class DuplicateUserException extends Exception{
    public DuplicateUserException(String message){
        super(message);
    }
}
