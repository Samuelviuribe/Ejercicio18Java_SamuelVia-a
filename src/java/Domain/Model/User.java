package Domain.Model;

/**
 *
 * @author HP
 */
public class User {

    private String cedula;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String username;
    private String rol;

     
    public User() {
    }

     
    public User(String cedula, String password, String nombre, String apellidos, String email, String username, String rol) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.username = username;
        this.rol = rol;
    }

    
    public User(String cedula, String password, String nombre, String email) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
