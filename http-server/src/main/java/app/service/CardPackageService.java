package app.service;

import app.model.CardPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardPackageService {
    public void save(CardPackage cardPackage){
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/mtcg",
                        "postgres",
                        "xgrtm625");

                PreparedStatement createPackage =connection.prepareStatement("""
                INSERT INTO card_packages
                (packageid, userid)
                VALUES (?,?);
                """
                )) {
            createPackage.setString(1, cardPackage.getPackageId());
            createPackage.setString(2, cardPackage.getUserId());


            createPackage.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
