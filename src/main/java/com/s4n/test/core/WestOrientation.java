package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Location;
import com.s4n.test.model.Orientation;

public class WestOrientation implements OrientationState {

  private static OrientationState instance;

  private WestOrientation() {
  }

  public static OrientationState getInstance() {
    if(instance == null)
      instance =  new WestOrientation();
    return instance;
  }

  @Override
  public void advance(Location location, int maxBlocksAround) {
    int X = location.getX();
    if (X > maxBlocksAround * -1) {
      X -= 1;
      location.setX(X);
    }

    else
      throw new OutOfDeliveryRangeException("Can't be possible advance because the location is out of range");
  }

  @Override
  public void turnToLeft(Location location, DroneLocationHandler handler) {
    location.setOrientation(Orientation.S);
    handler.setOrientationState(SouthOrientation.getInstance());
  }

  @Override
  public void turnToRight(Location location, DroneLocationHandler handler) {
    location.setOrientation(Orientation.N);
    handler.setOrientationState(NorthOrientation.getInstance());
  }
}
