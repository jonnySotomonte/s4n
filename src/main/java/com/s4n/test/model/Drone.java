package com.s4n.test.model;

import java.util.List;

public class Drone {

  private int id;
  private Location location;
  private List<String> deliveryPaths;

  public Drone(int id, List<String> deliveryPaths) {
    Location initialLocation = new Location(0,0, Orientation.N);
    this.id = id;
    this.location = initialLocation;
    this.deliveryPaths = deliveryPaths;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public List<String> getDeliveryPaths() {
    return deliveryPaths;
  }

  public void setDeliveryPaths(List<String> deliveryPaths) {
    this.deliveryPaths = deliveryPaths;
  }
}
