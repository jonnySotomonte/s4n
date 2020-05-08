package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Drone;
import com.s4n.test.model.Location;
import com.s4n.test.utils.FileReaderUtils;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryHandler {

  private static final Logger logger = LoggerFactory.getLogger(DeliveryHandler.class);
  private FileReaderUtils fileReaderUtils = new FileReaderUtils();
  private DroneLocationHandler droneLocationHandler = new DroneLocationHandler();

  public void doDelivery(int droneId, int maxDeliveries, int maxBlocksAround) {
    try {
      List<String> deliveryPaths = fileReaderUtils.getDeliveryPaths(droneId, maxDeliveries);
      System.out.println(deliveryPaths.size());
      Drone drone = new Drone(droneId, deliveryPaths);
      doDelivery(drone, maxBlocksAround);
      logger.info("X: {} , Y: {}, O: {}", drone.getLocation().getX(), drone.getLocation().getY(),
          drone.getLocation().getOrientation());
    } catch (IllegalArgumentException | IOException e) {
      logger.error("Can't read the file for drone {}", droneId);
    }
  }

  private void doDelivery(Drone drone, int maxBlocksAround) {
    for (String path : drone.getDeliveryPaths()) {
      try {
        Location newLocation = droneLocationHandler.getNewLocation(drone, maxBlocksAround, path);
        drone.setLocation(newLocation);
      } catch (OutOfDeliveryRangeException e) {
        logger.error(e.getMessage());
      }
    }

  }

}
