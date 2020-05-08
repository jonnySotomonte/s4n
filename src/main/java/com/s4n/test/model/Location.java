package com.s4n.test.model;

public class Location {

  private int X;
  private int Y;
  private Orientation orientation;

  public Location(int x, int y, Orientation orientation) {
    X = x;
    Y = y;
    this.orientation = orientation;
  }

  public int getX() {
    return X;
  }

  public void setX(int x) {
    X = x;
  }

  public int getY() {
    return Y;
  }

  public void setY(int y) {
    Y = y;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }
}
