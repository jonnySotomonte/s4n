package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Drone;
import com.s4n.test.model.Location;
import com.s4n.test.model.Orientation;

public class DroneLocationHandler {

  private static final String ADVANCE = "A";
  private static final String TURN_TO_LEFT = "I";
  private static final String TURN_TO_RIGHT = "D";

  public Location getNewLocation(Drone drone, int maxBlocksAround, String path) {
    Location location = drone.getLocation();
    String [] pathSteps = path.split("");
    for (String step: pathSteps) {
      executeStep(location, maxBlocksAround, step);
    }
    return location;
  }

  private void executeStep(Location location, int maxBlocksAround, String step) {
    if (step.equalsIgnoreCase(ADVANCE)) {
      advance(location, maxBlocksAround);
    } else if (step.equalsIgnoreCase(TURN_TO_LEFT)) {
      turnToLeft(location);
    } else if (step.equalsIgnoreCase(TURN_TO_RIGHT)) {
      turnToRight(location);
    }
  }

  private void advance(Location location, int maxBlocksAround) {
    int X = location.getX();
    int Y = location.getY();
    Orientation orientation = location.getOrientation();
    switch (orientation) {
      case N:
        if (Y < maxBlocksAround)
          Y += 1;
        else
          throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
        break;
      case S:
        if (Y > maxBlocksAround * -1)
          Y -= 1;
        else
          throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
        break;
      case E:
        if (X < maxBlocksAround)
          X += 1;
        else
          throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
        break;
      case W:
        if (X > maxBlocksAround * -1)
          X -= 1;
        else
          throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
        break;
    }
    location.setX(X);
    location.setY(Y);
  }

  private void turnToLeft(Location currentLocation) {
    Orientation orientation = currentLocation.getOrientation();
    switch (orientation) {
      case N:
        orientation = Orientation.W;
        break;
      case S:
        orientation = Orientation.E;
        break;
      case E:
        orientation = Orientation.N;
        break;
      case W:
        orientation = Orientation.S;
        break;
    }
    currentLocation.setOrientation(orientation);
  }

  private void turnToRight(Location currentLocation) {
    Orientation orientation = currentLocation.getOrientation();
    switch (orientation) {
      case N:
        orientation = Orientation.E;
        break;
      case S:
        orientation = Orientation.W;
        break;
      case E:
        orientation = Orientation.S;
        break;
      case W:
        orientation = Orientation.N;
        break;
    }
    currentLocation.setOrientation(orientation);
  }

}
