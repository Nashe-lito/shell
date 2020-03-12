package acceptance.frontend.model;

public class DriverData {
  private final String surname;
  private final String name;
  private final String middlename;
  private final String email;
  private final String status;
  private final String tel;
  private final String carNum;
  private final String note;

  public DriverData(String surname, String name, String middlename, String email, String status, String tel, String carNum, String note) {
    this.surname = surname;
    this.name = name;
    this.middlename = middlename;
    this.email = email;
    this.status = status;
    this.tel = tel;
    this.carNum = carNum;
    this.note = note;
  }

  public String getSurname() {
    return surname;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getEmail() {
    return email;
  }

  public String getStatus() {
    return status;
  }

  public String getTel() {
    return tel;
  }

  public String getCarNum() {
    return carNum;
  }

  public String getNote() {
    return note;
  }

  @Override
  public String toString() {
    return "DriverData{" +
            "surname='" + surname + '\'' +
            '}';
  }
}
