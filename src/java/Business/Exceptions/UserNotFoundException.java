package Business.Exceptions;

/**
 *
 * @author HP
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message){
        super(message);
    }
}
