package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Location;
import com.s4n.test.model.Orientation;

public class SouthOrientation implements OrientationState {

  private static OrientationState instance;

  private SouthOrientation() {
  }

  public static OrientationState getInstance() {
    if(instance == null)
      instance =  new SouthOrientation();
    return instance;
  }

  @Override
  public void advance(Location location, int maxBlocksAround) {
    int Y = location.getY();
    if (Y > maxBlocksAround * -1) {
      Y -= 1;
      location.setY(Y);
    }

    else
      throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
  }

  @Override
  public void turnToLeft(Location location, DroneLocationHandler handler) {
    location.setOrientation(Orientation.E);
    handler.setOrientationState(EastOrientation.getInstance());
  }

  @Override
  public void turnToRight(Location location, DroneLocationHandler handler) {
    location.setOrientation(Orientation.W);
    handler.setOrientationState(WestOrientation.getInstance());
  }
}
