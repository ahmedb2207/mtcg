package app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class EditUser {
    @Getter
    @Setter
    @JsonAlias({"Name"})
    private String name;

    @Getter
    @Setter
    @JsonAlias({"Bio"})
    private String bio;
    @Getter
    @Setter
    @JsonAlias({"Image"})
    private String image;

    public EditUser(){}
    public EditUser(String name, String bio, String image ){
        this.name=name;
        this.bio = bio;
        this.image = image;
    }
}
