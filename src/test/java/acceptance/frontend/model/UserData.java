package acceptance.frontend.model;

public class UserData {
  private final String createName;
  private final String createSurname;
  private final String createRole;
  private final String createEmail;
  private final String createTel;
  private final String createUsername;
  private final String createPassword;


  public UserData(String createName, String createSurname, String createRole, String createEmail, String createTel, String createUsername, String createPassword) {
    this.createName = createName;
    this.createSurname = createSurname;
    this.createRole = createRole;
    this.createEmail = createEmail;
    this.createTel = createTel;
    this.createUsername = createUsername;
    this.createPassword = createPassword;
  }

  public String getCreateName() {
    return createName;
  }

  public String getCreateSurname() {
    return createSurname;
  }

  public String getCreateRole() {
    return createRole;
  }

  public String getCreateEmail() {
    return createEmail;
  }

  public String getCreateTel() {
    return createTel;
  }

  public String getCreateUsername() {
    return createUsername;
  }

  public String getCreatePassword() {
    return createPassword;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "createSurname='" + createSurname + '\'' +
            '}';
  }

}
