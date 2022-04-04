package app.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
public class Card {
    public enum ElementType {
        Fire,
        Normal,
        Water
    }

    public enum CardType {
        Goblin,
        Dragon,
        Wizzard,
        Ork,
        Knight,
        Spell,
        Kraken,
        Elf,
        Troll
    }

    @Getter
    @Setter
    @JsonAlias({"Id"})
    private String id;
    @Getter
    @Setter
    @JsonAlias({"Name"})
    private String name;
    @Getter
    @Setter
    @JsonAlias({"Damage"})
    private double damage;
    @Getter
    @Setter
    private ElementType elementtype;
    @Getter
    @Setter
    private CardType cardtype;
    @Getter
    @Setter
    @JsonAlias({"Package_Id"})
    private int package_id;
    @Getter
    @Setter
    @JsonAlias({"Type"})
    private String Type;

    public Card(String id, String name, int damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }
    public void setStringtoCardType(String type){

        if(type.equals("Elf"))
            setCardtype(CardType.Elf);
        else if(type.equals("Spell"))
            this.setCardtype(CardType.Spell);
        else if(type.equals("Dragon"))
            this.setCardtype(CardType.Dragon);
        else if(type.equals("Goblin"))
            this.setCardtype(CardType.Goblin);
        else if(type.equals("Ork"))
            this.setCardtype(CardType.Ork);
        else if(type.equals("Knight"))
            this.setCardtype(CardType.Knight);
        else if(type.equals("Wizzard"))
            this.setCardtype(CardType.Wizzard);
        else if(type.equals("Troll"))
            this.setCardtype(CardType.Troll);


    }


}