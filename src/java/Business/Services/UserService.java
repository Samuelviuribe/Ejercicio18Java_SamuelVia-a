package Business.Services;

/**
 *
 * @author HP
 */
import Domain.Model.User;
import Business.Exceptions.UserNotFoundException;
import Business.Exceptions.DuplicateUserException;
import Infraestructure.Persistence.UserCRUD;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserCRUD userCrud;

    // Constructor
    public UserService() {
        this.userCrud = new UserCRUD();
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() throws SQLException {
        return userCrud.getAllUsers();
    }

    // Método para agregar un nuevo usuario
    public void createUser(String cedula, String nombre, String apellidos, String username, String email, String password, String rol)
            throws DuplicateUserException, SQLException {
        User user = new User(cedula, password, nombre, apellidos, username, email, rol);
        userCrud.addUser(user);
    }

    // Método para actualizar un usuario
    public void updateUser(int id, String cedula, String nombre, String email, String password, 
                       String apellidos, String username, String rol, String telefono, 
                       String estado, String fecha_registro)
        throws UserNotFoundException, SQLException {
       User user = new User(
        id,
        cedula, 
        password, 
        nombre, 
        apellidos, 
        email, 
        username, 
        rol, 
        telefono, 
        estado, 
        fecha_registro
    );
        userCrud.updateUser(user);
    }

    // Método para eliminar un usuario
    public void deleteUser(String cedula) throws UserNotFoundException, SQLException {
        userCrud.deleteUser(cedula);
    }

    // Método para obtener un usuario por código
    public User getUserByCedula(String cedula) throws UserNotFoundException, SQLException {
        return userCrud.getUserByCedula(cedula);
    }

    // Método para autenticar un usuario (login)
    public User loginUser(String email, String password) throws UserNotFoundException, SQLException {
        // Usamos el nuevo método getUserByEmail en lugar de obtener todos los usuarios
        User user = userCrud.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new UserNotFoundException("Credenciales incorrectas. No se encontró el usuario o la contraseña es incorrecta.");
        }
    }

    // Método para buscar usuarios por nombre o email
    public List<User> searchUsers(String searchTerm) {
        return userCrud.searchUsers(searchTerm);
    }
}
