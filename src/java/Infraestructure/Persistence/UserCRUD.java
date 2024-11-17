package Infraestructure.Persistence;

/**
 *
 * @author HP
 */
import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UserNotFoundException;
import Domain.Model.User;
import Infraestructure.Database.ConnectionDbMySql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD {

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";
        try {
            Connection con = ConnectionDbMySql.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userList.add(
                        new User(
                                rs.getString("cedula"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("email")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Método para agregar un nuevo usuario
//    public void addUser(User user) throws SQLException, DuplicateUserException {
//        String query = "INSERT INTO estudiantes (cedula, password, nombre, email) VALUES (?, ?, ?, ?)";
//        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
//
//            stmt.setString(1, user.getCedula());
//            stmt.setString(2, user.getPassword());
//            stmt.setString(3, user.getNombre());
//            stmt.setString(4, user.getEmail());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            // Aquí manejamos una posible excepción de clave duplicada
//            if (e.getErrorCode() == 1062) { // Código de error de clave duplicada en MySQL
//                throw new DuplicateUserException("El usuario con el código o email ya existe.");
//            } else {
//                throw e; // Propagamos la excepción SQLException para que la maneje el servicio
//            }
//        }
//    }
    public void addUser(User user) throws SQLException, DuplicateUserException {
        String query = "INSERT INTO estudiantes (cedula, Username, password, nombre, apellidos, rol, email, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(user.getCedula())); // Cedula como entero
            stmt.setString(2, user.getUsername());             // Username
            stmt.setString(3, user.getPassword());             // Contraseña
            stmt.setString(4, user.getNombre());               // Nombre
            stmt.setString(5, user.getApellidos());            // Apellidos
            stmt.setString(6, user.getRol());                  // Rol
            stmt.setString(7, user.getEmail());                // Email
            stmt.setString(8, "activo");                       // Estado por defecto

            stmt.executeUpdate();
        }
    }
        // Método para actualizar un usuario
public void updateUser(User user) throws SQLException, UserNotFoundException {
        String query = "UPDATE estudiantes SET password=?, nombre=?, email=? WHERE cedula=?";

        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getNombre());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getCedula());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("El usuario con el código " + user.getCedula() + " no existe.");
            }
        } catch (SQLException e) {
            throw e; // Propagamos la excepción SQLException para que la maneje el servicio
        }
    }

// Método para eliminar un usuario
    public void deleteUser(String cedula) throws SQLException, UserNotFoundException {
        String query = "DELETE FROM estudiantes WHERE cedula=?";

        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cedula);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("El usuario con el código " + cedula + " no existe.");
            }
        } catch (SQLException e) {
            throw e; // Propagamos la excepción SQLException para que la maneje el servicio
        }
    }

// Método para obtener un usuario por cedula
    public User getUserByCedula(String cedula) throws SQLException, UserNotFoundException {
        String query = "SELECT * FROM estudiantes WHERE cedula=?";
        User user = null;

        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("cedula"), rs.getString("password"), rs.getString("nombre"), rs.getString("email"));
            } else {
                throw new UserNotFoundException("El usuario con la cedula " + cedula + " no existe.");
            }
        } catch (SQLException e) {
            throw e; // Propagamos la excepción SQLException para que la maneje el servicio
        }
        return user;
    }

// Método para autenticar un usuario por email y contraseña (Login)
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = null;
        String query = "SELECT * FROM estudiantes WHERE email=? AND password=?";

        try {
            Connection con = ConnectionDbMySql.getConnection();

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("cedula"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
            } else {
                String message = "Credenciales incorrectas. No se encontró el usuario.";
                throw new UserNotFoundException(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

// Método para obtener un usuario por email
    public User getUserByEmail(String email) throws SQLException, UserNotFoundException {
        User user = null;
        String query = "SELECT * FROM estudiantes WHERE email=?";
        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("cedula"), rs.getString("password"), rs.getString("nombre"), rs.getString("email"));
            } else {
                throw new UserNotFoundException("El usuario con el email " + email + " no existe.");
            }
        }
        return user;
    }

// Método para buscar usuarios por nombre o email
    public List<User> searchUsers(String searchTerm) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM estudiantes WHERE nombre LIKE ? OR email LIKE ?";
        try ( Connection con = ConnectionDbMySql.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(
                        new User(
                                rs.getString("cedula"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("email")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
