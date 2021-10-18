package gpn.util;
import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username, lastName, telephone;;

    //need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone () {
        return telephone;
    }

    public void setTelephone (String telephone) {
        this.telephone = telephone;
    }
}