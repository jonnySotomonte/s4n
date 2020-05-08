package com.s4n.test.model;

import java.util.List;

public class Drone {

  private int id;
  private Location location;
  private List<String> paths;

  public Drone(int id, List<String> paths) {
    Location initialLocation = new Location(0,0, Orientation.N);
    this.id = id;
    this.location = initialLocation;
    this.paths = paths;
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

  public List<String> getPaths() {
    return paths;
  }

  public void setPaths(List<String> paths) {
    this.paths = paths;
  }
}
