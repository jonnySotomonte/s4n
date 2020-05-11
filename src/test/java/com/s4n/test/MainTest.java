package com.s4n.test;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  /**
   * In this test you can verify that the 20 files are created according with the 20 drones,
   * In the console you can verify that every drone delivery is executed by a different thread.
   * At the final of the test each file is deleted in order to avoid store unnecessary information*
   **/
  @Test
  public void testMain() throws InterruptedException {
    Main.main(null);
    Thread.sleep(20);
    for (int i = 1; i <= 20; i++) {
      String fileName = buildFileName(i);
      String filePath = "src/main/resources/out/" + fileName;
      File file = new File(filePath);
      Assert.assertTrue(file.exists());
      file.delete();
    }
  }

  private String buildFileName(int droneId) {
    String extensionFile = ".txt";
    String baseName = (droneId < 10) ? "out0" + droneId : "out" + droneId;
    return baseName + extensionFile;
  }

}
