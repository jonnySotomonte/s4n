package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Drone;
import com.s4n.test.model.Location;
import com.s4n.test.utils.FileReaderUtils;
import com.s4n.test.utils.FileWriterUtils;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryHandler {

  private static final Logger logger = LoggerFactory.getLogger(DeliveryHandler.class);
  private FileReaderUtils fileReaderUtils = new FileReaderUtils();
  private FileWriterUtils fileWriterUtils = new FileWriterUtils();
  private DroneLocationHandler locationHandler;

  public void doDelivery(int droneId, int maxDeliveries, int maxBlocksAround) {
    try {
      String threadName = Thread.currentThread().getName();
      logger.info("Executing thread {} for drone {}", threadName, droneId);
      List<String> paths = fileReaderUtils.getDeliveryPaths(droneId, maxDeliveries);
      Drone drone = new Drone(droneId, paths);
      locationHandler = new DroneLocationHandler();
      deliveryLunches(drone, maxBlocksAround);
    } catch (IllegalArgumentException | IOException e) {
      logger.error("Can't read the file for drone {}", droneId);
    }
  }

  private void deliveryLunches(Drone drone, int maxBlocksAround) {
    for (String path : drone.getPaths()) {
      try {
        Location originalLocation = drone.getLocation();
        Location newLocation = locationHandler.getNewLocation(originalLocation, maxBlocksAround, path);
        drone.setLocation(newLocation);
        fileWriterUtils.write(drone);
      } catch (OutOfDeliveryRangeException e) {
        logger.error(e.getMessage());
        reportOnFile(drone.getId());
        break;
      } catch (IOException e) {
        logger.error("There was an error while writing the file for drone {}", drone.getId());
      }
    }

  }

  private void reportOnFile(int droneId) {
    try {
      String message = "No se pudo realizar la entrega ya que la ruta esta por fuera del rango de cobertura";
      fileWriterUtils.write(droneId, message);
    } catch (IOException e) {
      logger.error("There was an error while writing the file for drone {}", droneId);
    }
  }

}
