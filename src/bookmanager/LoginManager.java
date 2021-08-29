package bookmanager;

import java.sql.ResultSet;
import java.util.List;

import bookmanager.models.UserModel;

public class LoginManager {
    
    private UserModel userLogged;
    private MySQLConnection con;
    
    public LoginManager(MySQLConnection con) {
        this.con = con;
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username='" + username + "' and password='" + password + "';";
        ResultSet user = con.selectQuery(query);

        try {
            if(user.next()) {
                userLogged = new UserModel(
                    user.getInt("id"),
                    user.getString("fullName"),
                    user.getString("username"),
                    user.getString("password"),
                    user.getBoolean("admin") 
                );
                
                return true;
            }
        } catch (Exception e) { }

        return false;
    }

    public String getFullName() {
        return userLogged.fullName;
    }

    public boolean isAdmin() {
        return userLogged.admin;
    }

    public List<UserModel> getAllByName(String name) {
        String query = "SELECT * FROM users WHERE fullName LIKE '%" + name + "%' OR username LIKE '%" + name + "%'";

        List<UserModel> models = MySQLConnection.resultSetToList(con.selectQuery(query), "UserModel");
        return models;
    }

    public boolean insertUser(String name, String username, String password, boolean admin) {
        String fields = "", values = "";
        if(!name.isEmpty()) {
            fields += ", fullName"; values += ", '" + name + "'";
        }
        if(!username.isEmpty()) {
            fields += ", username"; values += ", '" + username + "'";
        }
        if(!password.isEmpty()) {
            fields += ", password"; values += ", '" + password + "'";
        }
        fields += ", admin"; values += ", " + admin;
        
        String query = "INSERT INTO users(id" + fields + ") VALUES (DEFAULT" + values + ")";
        
        int result = con.insertQuery(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

    public boolean deleteUserById(int id) {
        String query = "DELETE FROM users WHERE id=" + id;
        int result = con.insertQuery(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

    
}
