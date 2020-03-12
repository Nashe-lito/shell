package acceptance.frontend.generators;

import acceptance.frontend.model.DriverData;
import acceptance.frontend.model.UserData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataGenerator {
  @Parameter(names = "-c", description = "Driver count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;


  public static void main(String [] args) throws IOException {
    /*int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);*/

    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  public void run() throws IOException {
    List<UserData> users = generateUsers(count);
  /*  if (format.equals("csv")) {
      saveAsCsv(users, new File(file));
    } else */if (format.equals("xml")) {
      saveAsXml(users, new File(file));
    }else if (format.equals("json")) {
      saveAsJson(users, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<UserData> users, File file) throws IOException {
    XStream xstream = new XStream();
    String xml = xstream.toXML(users);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  /*private void saveAsCsv(List<UserData> users, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (UserData user : users){
      writer.write(String.format("%s;%s;%s;%s\n",  user.getCreateName(), user.getCreateSurname(), user.getCreateTel(), user.getCreatePassword();
      // writer.write(String.format("\n", driver.getSurname(), driver.getName(), driver.getMiddlename(), driver.getEmail(), driver.getCarNum(), driver.getNote()));
    }
    writer.close();
  }*/

  private List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++){
      users.add(new UserData("Surname" , "Name", "accountant", "test@test.test" + new Random().nextInt(10000) + i, "+38098716531" + i,  "test" + new Random().nextInt(10000) + i,   "12345678"+ i));
      //  drivers.add(new DriverData("Surname %s" + i, "Name %s"+ i, "Middlename %s"+ i, "test@test.test %s"+ i, "active" + i, "+380987165391"+ i,   "AA1234BB %s"+ i, "Ea eos %s"+ i));
    }
    return users;
  }
}
