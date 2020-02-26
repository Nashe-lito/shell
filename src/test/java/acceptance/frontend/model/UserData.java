package acceptance.frontend.model;

public class UserData {
  private final String createName;
  private final String createSurname;
  private final String createRole;
  private final String createTel;
  private final String createPassword;


  public UserData(String createName, String createSurname, String createRole, String createTel, String createPassword) {
    this.createName = createName;
    this.createSurname = createSurname;
    this.createRole = createRole;
    this.createTel = createTel;
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

  public String getCreateTel() {
    return createTel;
  }

  public String getCreatePassword() {
    return createPassword;
  }
}
