package Models;

public class User {

    private String email;
    private String password;
    private String userFirstName;
    private String userLastName;

    public User(String email, String password, String userFirstName, String userLastName){
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserFirstName(){
        return userFirstName;
    }
    public String getUserLastName(){
        return userLastName;
    }
}
