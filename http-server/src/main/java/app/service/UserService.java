package app.service;

import app.model.User;

import java.sql.*;

public class UserService {
    public void addUser(User user){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mtcg",
                    "postgres",
                    "xgrtm625");

            PreparedStatement addUser = connection.prepareStatement(
                    "INSERT INTO users (username, password, token) " +
                            "VALUES (?,?,?);"
            );
            addUser.setString(1,user.getUsername());
            addUser.setString(2, user.getPassword());
            addUser.setString(3, user.getToken());
            addUser.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public User getUserdb(String username) {
       try {
           Connection connection = DriverManager.getConnection(
                   "jdbc:postgresql://localhost:5432/mtcg",
                   "postgres",
                   "xgrtm625");
           PreparedStatement statement = connection.prepareStatement("""
                SELECT loggedin, password
                FROM users
                WHERE username=?
                """);
           statement.setString( 1, username );
           ResultSet resultSet = statement.executeQuery();
           statement.close();

           if( resultSet.next() ) {
               return new User(username, resultSet.getString(2),resultSet.getBoolean(1)) ;

       }

   }
       catch (SQLException e) {
           e.printStackTrace();
       }
    return null;

   }

   public void login(String username){
       try {
           Connection connection = DriverManager.getConnection(
                   "jdbc:postgresql://localhost:5432/mtcg",
                   "postgres",
                   "xgrtm625");
           PreparedStatement statement = connection.prepareStatement("""
                UPDATE users
                SET loggedin =?
                WHERE username=?;
                """);
           statement.setBoolean( 1, true );
           statement.setString( 2, username );
           statement.execute();
           connection.close();


       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }



}
