package app.service;

import app.model.DbAccess;
import app.model.EditUser;
import app.model.User;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public void addUser(User user){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mtcg",
                    "postgres",
                    "xgrtm625");

            PreparedStatement addUser = connection.prepareStatement(
                    "INSERT INTO users (id,username, password, token) " +
                            "VALUES (?,?,?,?);"
            );

            //Password hashen : https://github.com/patrickfav/bcrypt
            user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));

            addUser.setString(1, user.getId());
            addUser.setString(2,user.getUsername());
            addUser.setString(3, user.getPassword());
            addUser.setString(4, user.getToken());
            addUser.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void updateCoins(int coins, String username) {

        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                UPDATE users
                SET coins =?
                WHERE username=?;
                """)
        ) {
            statement.setInt(1, coins);
            statement.setString(2, username);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean LoggedInState(String username, String token) {
        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT loggedin
                FROM users
                WHERE username=? AND token=?
                """)
        ) {
            statement.setString(1, username);
            statement.setString(2, token);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public User getUserdata( String username){
        try ( PreparedStatement statement =DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT id, password, coins, token, loggedin, wins, losses, draws, elo, name, bio, image
                FROM users
                WHERE username=?
                """)
        ) {

            statement.setString( 1, username );
            ResultSet resultSet = statement.executeQuery();

            if( resultSet.next() ) {
                return  new User(username,
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public int getCoins(String username) {

        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT coins
                FROM users
                WHERE username=?
                """)
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return -1;
    }


    public String getid(String username) {
        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT id
                FROM users
                WHERE username=?
                """)
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editUserProfile(String username, EditUser editUser){
        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                UPDATE users
                SET name =?, bio =?, image =?
                WHERE username=?;
                """)
        ) {
            statement.setString(1, editUser.getName());
            statement.setString(2, editUser.getBio());
            statement.setString(3, editUser.getImage());
            statement.setString(4, username);


            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printUserProfile(String username) {
        System.out.println("___________________________________________");
        System.out.println("Name: | "+getUserdata(username).getName());
        System.out.println("Bio:  | "+getUserdata(username).getBio());
        System.out.println("Image:| "+getUserdata(username).getImage());
        System.out.println("___________________________________________");
    }

    public void printStats(String username) {
        System.out.println("____________________________________________");
        System.out.println("User: "+username);
        System.out.println("Wins: "+getUserdata(username).getWins());
        System.out.println("Losses: "+getUserdata(username).getLosses());
        System.out.println("Draws: "+getUserdata(username).getDraws());
        System.out.println("_____________________________________________");
    }
    public List<User> getAllUsersData(){
        List<User> users=new ArrayList<>();
        try ( PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT username, id,  password, coins, token, loggedin, wins, losses, draws, elo, name, bio, image
                FROM users 
                ORDER BY elo DESC, wins DESC, losses ASC
                """)
        ){
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                users.add(new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13)
                        )
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();}

        return users;
    }

    public void printScoreboard() {
        List<User> users = getAllUsersData();
        for (User user:
                users) {
            System.out.println("_____________________________________________________________________________");
            System.out.println("User: "+user.getUsername()+"   |"+"ELO: "+user.getElo()+"   |"+"WINS: "+user.getWins());

            System.out.println("_____________________________________________________________________________");

        }
    }
}
