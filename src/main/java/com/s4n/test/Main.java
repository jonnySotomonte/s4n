package com.s4n.test;

import com.s4n.test.core.DeliveryHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    try {
      Properties properties = initializeProperties();
      DeliveryHandler handler = new DeliveryHandler();
      int drones = Integer.parseInt(properties.getProperty("drones"));
      int deliveriesPerDrone = Integer.parseInt(properties.getProperty("deliveries"));
      int maxBlocksAround = Integer.parseInt(properties.getProperty("blocks.around"));
      ExecutorService executorService = Executors.newFixedThreadPool(drones);
      for (int i=1; i<=drones; i++) {
        handler.doDelivery(i, deliveriesPerDrone, maxBlocksAround);
      }
      executorService.shutdown();
    } catch (IOException e) {
      logger.error(
          "There was an error reading the configuration file named application.properties, caused by: {}",
          e.getMessage());
    }
  }

  private static Properties initializeProperties() throws IOException {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties properties = new Properties();
    try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
      assert resourceStream != null;
      properties.load(resourceStream);
      return properties;
    }
  }

}
