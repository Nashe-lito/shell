package acceptance.frontend.model;

public class ApplyNewCardData {
  private final String numberOfCards;
  private final String fullName;
  private final String tel;

  public ApplyNewCardData(String numberOfCards, String fullName, String tel) {
    this.numberOfCards = numberOfCards;
    this.fullName = fullName;
    this.tel = tel;
  }

  public String getNumberOfCards() {
    return numberOfCards;
  }

  public String getFullName() {
    return fullName;
  }

  public String getTel() {
    return tel;
  }
}
