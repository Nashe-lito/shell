package acceptance.backend.model;

public class FiltersData {
  private final String managerId;

  public FiltersData(String managerId) {
    this.managerId = managerId;
  }

  public String getManagerId() {
    return managerId;
  }
}
