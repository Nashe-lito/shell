package acceptance.backend.model;

import java.io.File;
import java.util.Objects;

public class UsersOfAdminData {
  private String id;
  private String managerId;
  private String name;
  private String login;
  private String email;
  private String password;
  private String phone;
  private File photo;


  public UsersOfAdminData(File photo, String managerId, String name, String login, String email, String password, String phone) {
    this.id = null;
    this.photo = photo;
    this.managerId = managerId;
    this.name = name;
    this.login = login;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }

  public UsersOfAdminData(String id, File photo, String managerId, String name, String login, String email, String password, String phone) {
    this.id = id;
    this.photo = photo;
    this.managerId = managerId;
    this.name = name;
    this.login = login;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public File getPhoto() {
    return photo;
  }

  public String getManagerId() {
    return managerId;
  }

  public String getName() {
    return name;
  }

  public String getLogin() {
    return login;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public String toString() {
    return "UsersOfAdminData{" +
            "id='" + id + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsersOfAdminData that = (UsersOfAdminData) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
