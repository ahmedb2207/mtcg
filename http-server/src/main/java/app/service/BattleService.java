package app.service;

import app.model.Battle;
import app.model.DbAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BattleService {
    public boolean createOrJoin(String username) {
        Battle battle = null;
        try {
            PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement(
                    "SELECT * FROM battle WHERE player2 IS NULL LIMIT 1"
            );
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                battle = getBattle(result.getString(1));
            } else {
                battle = createBattle();
            }

            battle = addPlayer(battle, username);
            if (battle == null) {
                System.out.println("Already got an open battle");
                return false;
            }

            System.out.println(battle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    private Battle getBattle (String battleId) throws SQLException {
        System.out.println(battleId);
        Battle battle = null;
        try {
            PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement(
                    "SELECT * FROM battle WHERE id = ?"
            );
            statement.setString(1, battleId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                battle = new Battle(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return battle;
    }
    private Battle createBattle () throws SQLException {
        Battle battle = null;
        try {
            PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement(
                    "INSERT INTO battle (id, player1, player2, winner) VALUES (?,?,?,?)"
            );
            String id = UUID.randomUUID().toString();
            statement.setString(1, id);
            statement.setString(2, null);
            statement.setString(3, null);
            statement.setString(4, null);
            statement.execute();

            battle = new Battle(id, null, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return battle;
    }
    private Battle addPlayer (Battle battle, String username) throws SQLException {
        try {
            String userId = new UserService().getUserdata(username).getId();
            PreparedStatement statement;
            if (battle.getPlayer1() == null) {
                System.out.println("PLAYER1");
                statement = DbAccess.DbConnection.getInstance().prepareStatement(
                        "UPDATE battle SET player1 = ? WHERE id = ?"
                );
                battle.setPlayer1(userId);
            } else if (battle.getPlayer2() == null && !battle.getPlayer1().equals(userId)){
                System.out.println("PLAYER2");
                statement = DbAccess.DbConnection.getInstance().prepareStatement(
                        "UPDATE battle SET player2 = ? WHERE id = ?"
                );
                battle.setPlayer2(userId);
            } else {
                System.out.println("Already got an open battle");
                return null;
            }
            statement.setString(1, userId);
            statement.setString(2, battle.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return battle;
    }

}
