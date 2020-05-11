package com.s4n.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  /**
   * In this test you can verify that the 20 files are created according with the 20 drones,
   * In the console you can verify that every drone delivery is executed by a different thread.
   * To the first drone is verified the content of out file, to assert that the locations report are OK
   * To the second drone is verified the content of out file, to assert that the deliveries can't be done
   * because the path delivery is out of range
   * For each drone is validated that the out file only can have maximum 3 deliveries
   * At the final of the test each file is deleted in order to avoid store unnecessary information
   **/
  @Test
  public void testMain() throws InterruptedException, IOException {
    Main.main(null);
    Thread.sleep(20);
    for (int i = 1; i <= 20; i++) {
      String fileName = buildFileName(i);
      String filePath = "src/main/resources/out/" + fileName;
      File file = new File(filePath);
      Assert.assertTrue(file.exists());
      if (i == 1) {
        List<String> fileContent = buildFirstDroneFileContent();
        validateFileContent(file, fileContent);
      } else if (i == 2) {
        List<String> fileContent = buildSecondDroneFileContent();
        validateFileContent(file, fileContent);
      }
      validateDeliveriesQuantity(file);
      file.delete();
    }
  }

  private String buildFileName(int droneId) {
    String extensionFile = ".txt";
    String baseName = (droneId < 10) ? "out0" + droneId : "out" + droneId;
    return baseName + extensionFile;
  }

  private void validateFileContent(File file, List<String> fileContent) throws IOException {
    try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {
      int index = 0;
      String finalLocation;
      while ((finalLocation = br.readLine()) != null) {
        Assert.assertEquals(fileContent.get(index), finalLocation);
        index++;
      }
    }
  }

  private void validateDeliveriesQuantity(File file) throws IOException {
    try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {
      int headerLines = 1;
      int maxDeliveries = 3;
      int maxFileLines = headerLines + maxDeliveries;
      int lines = 0;
      while (br.readLine() != null) {
        lines++;
      }
      Assert.assertTrue(lines <= maxFileLines);
    }
  }

  private List<String> buildFirstDroneFileContent() {
    return Arrays.asList("== Reporte de entregas ==", "(-2, 4) dirección Occidente", "(-1, 3) dirección Sur", "(0, 0) dirección Occidente");
  }

  private List<String> buildSecondDroneFileContent() {
    return Arrays.asList("== Reporte de entregas ==", "No se pudo realizar la entrega ya que la ruta esta por fuera del rango de cobertura");
  }

}
