package com.s4n.test.model;

public enum Orientation {
  N("Norte"),
  S("Sur"),
  E("Oriente"),
  W("Occidente");

  private String name;


  Orientation(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
