package com.s4n.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderUtils {

  private static final Logger logger = LoggerFactory.getLogger(FileReaderUtils.class);

  public List<String> getDeliveryPaths(int droneId, int maxDeliveries) throws IOException {
    File file = getFile(droneId);
    return readFile(file, maxDeliveries);
  }

  private File getFile(int droneId) {
    String fileName = buildFileName(droneId);
    String filePath = "in/" + fileName;
    logger.info("Leyendo archivo {}", filePath);
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(filePath);
    if (resource != null) {
      return new File(resource.getFile());
    } else {
      throw new IllegalArgumentException("file is not found!");
    }
  }

  private String buildFileName(int droneId) {
    String extensionFile = ".txt";
    String baseName = (droneId < 10) ? "in0" + droneId : "in" + droneId;
    return baseName + extensionFile;
  }

  private List<String> readFile(File file, int maxDeliveries) throws IOException {
    List<String> deliveryPaths = new ArrayList<>();
    try (FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader)) {
      String deliveryPath;
      while ((deliveryPath = br.readLine()) != null && deliveryPaths.size() < maxDeliveries) {
        deliveryPaths.add(deliveryPath);
      }
      return deliveryPaths;
    }
  }

}
