package com.s4n.test.exceptions;

public class OutOfDeliveryRangeException extends RuntimeException {

  public OutOfDeliveryRangeException(String message) {
    super(message);
  }
}
