package app.service;

import app.model.*;

import server.Request;

import java.util.List;

public class CardService {

    public int printDeck(String username) {

        if (countIn_deckCards(username) == 0) {
            return 0;
        }
        List<Card> configuredCards = getConfiguredCards(username);
        for (Card card : configuredCards) {
            System.out.println("____________________________________________");
            System.out.println("Name: " + card.getName() );
            System.out.println("Damage: " + card.getDamage() );
            System.out.println(" -------------------------------------------");


        }

        return 1;
    }

    public List<Card> getConfiguredCards(String username) {
        List<Card> deck=new ArrayList<>();
        try ( PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT id, name, damage, username, cardtype, elementtype
                FROM cards
                WHERE username=? AND indeck=?
                """)
        ) {
            statement.setString(1,username);
            statement.setBoolean(2,true);

            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                Card card= new Card(
                        resultSet.getString(1),
                        resultSet.getString( 2 ),
                        resultSet.getInt( 3));
                card.setUsername(resultSet.getString(4));
                card.setStringtoCardType(resultSet.getString(5));
                card.setStringtoElementType(resultSet.getString(6));

                deck.add( card);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deck;
    }

    public int countIn_deckCards(String username) {

        try (PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT COUNT(id)
                FROM cards
                WHERE username=? AND indeck=?;
                """)
        ) {
            statement.setString(1, username);
            statement.setBoolean(2, true);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int configureDeck(List<String> card_ids, String username) {

        if(card_ids.size()!=4){
            return 4;
        }
        if(getNumOfAcquiredCards(username)<4){
            return -1;
        }

        for (String id:card_ids) {
            if(cardIdCount(id,username)==0){
                return -5;
            }else if(cardIdCount(id,username)==1) {

                addCardToDeck(id,username);
            }
        }
        return 0;
    }

    private boolean addCardToDeck(String card_id, String username) {
        try ( PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                UPDATE cards
                SET indeck = ?
                WHERE id=? AND username=?;
                """)
        ) {
            statement.setBoolean(1, true);
            statement.setString(2, card_id);
            statement.setString(3, username);

            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int cardIdCount(String id, String username) {
        try ( PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT COUNT(id)
                FROM cards
                WHERE username=? AND id=?;
                """)
        ) {
            statement.setString(1, username);
            statement.setString(2, id);

            ResultSet resultSet = statement.executeQuery();
            if( resultSet.next() ) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    private int getNumOfAcquiredCards(String username) {
        try ( PreparedStatement statement = DbAccess.DbConnection.getInstance().prepareStatement("""
                SELECT COUNT(id)
                FROM cards
                WHERE username=? AND indeck=?;
                """)
        ) {
            statement.setString(1, username);
            statement.setBoolean(2, false);
            ResultSet resultSet = statement.executeQuery();

            if( resultSet.next() ) {
                return   resultSet.getInt(1);
            }
            // statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public int printOtherFormatDeck(String username) {
        if( countIn_deckCards(username)==0){
            return 0;
        }
        List<Card> configuredCards = getConfiguredCards(username);
        for (Card card : configuredCards) {
            System.out.println("______________________________________________________________________________________");
            System.out.println(" Name: " + card.getName() + "   Damage: " + card.getDamage()+"  id: " + card.getId());

        }
        return 1;
    }
}





