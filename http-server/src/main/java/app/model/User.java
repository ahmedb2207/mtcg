package app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    @JsonAlias({"id"})
    private String id;
    @Getter
    @Setter
    @JsonAlias({"Username"})
    private String username;
    @Getter
    @Setter
    @JsonAlias({"Password"})
    private String password;
    @Getter
    @Setter
    @JsonAlias({"token"})
    private String token;
    @Getter
    @Setter
    @JsonAlias({"coins"})
    private int coins;
    @Getter
    @Setter
    @JsonAlias({"loggedin"})
    private boolean loggedin;
    @Getter
    @Setter
    @JsonAlias({"Wins"})
    private int wins;
    @Getter
    @Setter
    @JsonAlias({"Losses"})
    private int losses;
    @Getter
    @Setter
    @JsonAlias({"Draws"})
    private int draws;
    @Getter
    @Setter
    @JsonAlias({"ELO"})
    private int elo;
    @Getter
    @Setter
    @JsonAlias({"Bio"})
    private String bio;
    @Getter
    @Setter
    @JsonAlias({"Image"})
    private String image;
    @Getter
    @Setter
    @JsonAlias({"Name"})
    private String name;




    // Jackson needs the default constructor
    public User() {

    }

    public User(String username, String password, Boolean loggedin) {
        this.id= 0;
        this.username = username;
        this.password = password;
        this.token = "Basic "+ username +"-mtcgToken";
        this.coins= 20;
        this.loggedin= loggedin;
    }



    public User(String username, String id, String password, int coins, String token, boolean loggedin, int wins, int losses, int draws, int elo, String name, String bio, String image) {
        this.username=username;
        this.id= id;
        this.password=password;
        this.coins=coins;
        this.token=token;
        this.loggedin=loggedin;
        this.wins=wins;
        this.losses=losses;
        this.draws=draws;
        this.elo=elo;
        this.name=name;
        this.bio=bio;
        this.image=image;


    }
}
