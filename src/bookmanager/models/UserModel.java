package bookmanager.models;

public class UserModel {
    
    public int id;
    public String fullName;
    public String username;
    public String password;
    public boolean admin;
    
    public UserModel() { }

    public UserModel(int id, String fullName, String username, String password, boolean admin) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public UserModel(String fullName, String username, String password, boolean admin) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public UserModel(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public void makeAdmin() {
        admin = true;
    }

    public void revokeAdmin() {
        admin = false;
    }
}
