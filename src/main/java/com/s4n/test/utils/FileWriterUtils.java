package com.s4n.test.utils;

import com.s4n.test.model.Drone;
import com.s4n.test.model.Location;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtils {

  private String filePath;

  public void write(Drone drone) throws IOException {
    writeAtFirstTime(drone.getId());
    Location location = drone.getLocation();
    String message = String.format("(%s, %s) dirección %s", location.getX(), location.getY(),
        location.getOrientation().getName());
    writeNewLine(filePath, message);
  }

  public void write(int droneId, String message) throws IOException {
    writeAtFirstTime(droneId);
    writeNewLine(filePath, message);
  }

  private void writeAtFirstTime(int id) throws IOException {
    String fileName = buildFileName(id);
    filePath = "src/main/resources/out/" + fileName;
    boolean isNewFile = isNewFile(filePath);
    if (isNewFile) {
      writeFirstLine(filePath);
    }
  }

  private String buildFileName(int droneId) {
    String extensionFile = ".txt";
    String baseName = (droneId < 10) ? "out0" + droneId : "out" + droneId;
    return baseName + extensionFile;
  }

  private boolean isNewFile(String filePath) throws IOException {
    File file = new File(filePath);
    return file.createNewFile();
  }

  private void writeFirstLine(String filePath) throws IOException {
    FileWriter fr = new FileWriter(filePath);
    BufferedWriter br = new BufferedWriter(fr);
    br.write("== Reporte de entregas ==");
    br.newLine();
    br.close();
  }

  private void writeNewLine(String filePath, String message) throws IOException {
    FileWriter fr = new FileWriter(filePath, true);
    BufferedWriter br = new BufferedWriter(fr);
    br.write(message);
    br.newLine();
    br.close();
  }

}
