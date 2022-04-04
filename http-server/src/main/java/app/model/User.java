package app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    @JsonAlias({"id"})
    private int id;
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

}
