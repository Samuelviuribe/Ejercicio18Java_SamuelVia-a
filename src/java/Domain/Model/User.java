package Domain.Model;

/**
 *
 * @author HP
 */
public class User {

    private String code;
    private String password;
    private String name;
    private String email;

    public User() {
    }

    public User(String code, String password, String name, String email) {
        this.code = code;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setname(String name){
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setemail(String email){
        this.email = email;
    }
    
}
