package app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    @JsonAlias({"username"})
    private String username;
    @Getter
    @Setter
    @JsonAlias({"password"})
    private String password
    // Jackson needs the default constructor
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
