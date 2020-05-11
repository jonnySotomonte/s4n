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
      int drones = Integer.parseInt(properties.getProperty("drones"));
      ExecutorService executorService = Executors.newFixedThreadPool(drones);
      for (int i=1; i<=drones; i++) {
        Runnable worker = new MyRunnable(i);
        executorService.execute(worker);
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

  public static class MyRunnable implements Runnable{

    private final int droneId;

    public MyRunnable(int droneId) {
      this.droneId = droneId;
    }

    @Override
    public void run() {
      try {
        Properties properties = initializeProperties();
        int deliveriesPerDrone = Integer.parseInt(properties.getProperty("deliveries"));
        int maxBlocksAround = Integer.parseInt(properties.getProperty("blocks.around"));

        DeliveryHandler handler = new DeliveryHandler();
        handler.doDelivery(droneId, deliveriesPerDrone, maxBlocksAround);
      }
      catch (IOException e) {
          logger.error(
              "There was an error reading the configuration file named application.properties, caused by: {}",
              e.getMessage());
        }
    }
  }

}
