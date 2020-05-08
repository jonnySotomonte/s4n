package com.s4n.test.core;

import com.s4n.test.model.Location;

public interface OrientationState {

  void advance(Location location, int maxBlocksAround);

  void turnToLeft(Location location, DroneLocationHandler handler);

  void turnToRight(Location location, DroneLocationHandler handler);

}
