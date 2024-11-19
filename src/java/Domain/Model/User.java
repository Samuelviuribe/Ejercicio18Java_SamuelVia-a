package Domain.Model;

/**
 *
 * @author HP
 */
public class User {
    private int id;
    private String cedula;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String username;
    private String rol;
    private String telefono;
    private String estado;
    private String fecha_registro;

     
    public User() {
    }

     
public User(int id, String cedula, String password, String nombre, String apellidos, String email, String username, String rol, String telefono, String estado, String fecha_registro) {
    this.id = id;
    this.cedula = cedula;
    this.password = password;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
    this.username = username;
    this.rol = rol;
    this.telefono = telefono;
    this.estado = estado;
    this.fecha_registro = fecha_registro;
}

    
    public User(String cedula, String password, String nombre, String email, String telefono, String estado, String fecha_registro) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
