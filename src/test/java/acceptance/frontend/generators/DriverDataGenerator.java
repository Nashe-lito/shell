package acceptance.frontend.generators;

import acceptance.frontend.model.DriverData;
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

public class DriverDataGenerator {
  @Parameter(names = "-c", description = "Driver count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;


  public static void main(String [] args) throws IOException {
    /*int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);*/

    DriverDataGenerator generator = new DriverDataGenerator();
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
    List<DriverData> drivers = generateDrivers(count);
    if (format.equals("csv")) {
      saveAsCsv(drivers, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(drivers, new File(file));
    }else if (format.equals("json")) {
        saveAsJson(drivers, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<DriverData> drivers, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(drivers);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<DriverData> drivers, File file) throws IOException {
    XStream xstream = new XStream();
    String xml = xstream.toXML(drivers);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<DriverData> drivers, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (DriverData driver : drivers){
    writer.write(String.format("%s;%s;%s;%s\n",  driver.getEmail(), driver.getTel(), driver.getCarNum(), driver.getNote()));
    // writer.write(String.format("\n", driver.getSurname(), driver.getName(), driver.getMiddlename(), driver.getEmail(), driver.getCarNum(), driver.getNote()));
    }
    writer.close();
  }

  private List<DriverData> generateDrivers(int count) {
    List<DriverData> drivers = new ArrayList<DriverData>();
    for (int i = 0; i < count; i++){
      drivers.add(new DriverData("Surname" , "Name", "Middlename", "test@test.test"+ i, "active", "+38098716539"+ i,   "AA1234BB"+ i, "Ea eoss"+ i));
    //  drivers.add(new DriverData("Surname %s" + i, "Name %s"+ i, "Middlename %s"+ i, "test@test.test %s"+ i, "active" + i, "+380987165391"+ i,   "AA1234BB %s"+ i, "Ea eos %s"+ i));
    }
    return drivers;
  }
}
