package com.s4n.test.core;

import com.s4n.test.model.Location;

public class DroneLocationHandler {

  private static final String ADVANCE = "A";
  private static final String TURN_TO_LEFT = "I";
  private static final String TURN_TO_RIGHT = "D";

  private OrientationState orientationState = new NorthOrientation();

  public Location getNewLocation(Location location, int maxBlocksAround, String path) {
    Location newLocation = new Location(location.getX(), location.getY(), location.getOrientation());
    String [] pathSteps = path.split("");
    for (String step: pathSteps) {
      executeStep(newLocation, maxBlocksAround, step);
    }
    return newLocation;
  }

  private void executeStep(Location location, int maxBlocksAround, String step) {
    if (step.equalsIgnoreCase(ADVANCE)) {
      orientationState.advance(location, maxBlocksAround);
    } else if (step.equalsIgnoreCase(TURN_TO_LEFT)) {
      orientationState.turnToLeft(location, this);
    } else if (step.equalsIgnoreCase(TURN_TO_RIGHT)) {
      orientationState.turnToRight(location, this);
    }
  }

  public void setOrientationState(OrientationState orientationState) {
    this.orientationState = orientationState;
  }
}
