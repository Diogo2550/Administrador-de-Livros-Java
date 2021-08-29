package bookmanager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bookmanager.enums.GenreEnum;


public class MySQLConnection {

    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String urlConnection = "jdbc:mysql://";

    private Connection connection;

    public MySQLConnection(String server, String username, String password, String database) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driverName);
            
            String connectionString = urlConnection + server + "/" + database;
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Classe " + driverName + " não encontrada!");
        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet selectAll(String table) {
        String query = "SELECT * FROM " + table + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            return result;
        } catch(SQLException e) {
            return null;
        }
    }

    public ResultSet selectQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            return null;
        }
    }

    public int insertQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            
            return statement.executeUpdate(query);
        } catch(Exception e) {
            return -1;
        }

    }

    public static <T> List<T> resultSetToList(ResultSet result, String className) {
        T model;
        List<T> list = null;
        
        try {
            list = new ArrayList<T>();

            while(result.next()) {
                model = (T)(Class.forName("bookmanager.models." + className).getDeclaredConstructor().newInstance());
                
                for(Field f : model.getClass().getDeclaredFields()) {
                    String type = f.getGenericType().getTypeName();
                    
                    if(type.equals("java.lang.String")) {
                        f.set(model, result.getString(f.getName()));
                    } else {
                        if(type.equals("int")) {
                            f.set(model, result.getInt(f.getName()));
                        } else {
                            if(type.equals("float")) {
                                f.set(model, result.getFloat(f.getName()));
                            } else {
                                if(type.equals("boolean")) {
                                    f.set(model, result.getBoolean(f.getName()));
                                } else {
                                    if(type.equals("java.util.Date")) {
                                        f.set(model, result.getDate(f.getName()));
                                    } else {
                                        if(type.equals("bookmanager.enums.GenreEnum")) {
                                            f.set(model, GenreEnum.fromInteger(result.getInt("genre")));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                list.add(model);
            }
        } catch (Exception e) { e.printStackTrace(); }
        

        return list;
    }

}
