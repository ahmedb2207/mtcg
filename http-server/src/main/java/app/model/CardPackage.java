package app.model;
import lombok.Getter;
import lombok.Setter;

public class CardPackage {
    @Getter
    @Setter
    private String packageId;
    @Getter
    @Setter
    private String userId;

  public CardPackage(String packageId, String userId){
      this.packageId = packageId;
      this.userId = userId;

  }

}
