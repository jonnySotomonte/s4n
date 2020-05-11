package com.s4n.test.core;

import com.s4n.test.exceptions.OutOfDeliveryRangeException;
import com.s4n.test.model.Location;
import com.s4n.test.model.Orientation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DroneLocationHandlerTest {

  private DroneLocationHandler locationHandler;
  private Location initialLocation;
  private int maxBlocksAround = 10;

  @Before
  public void setup() {
    locationHandler = new DroneLocationHandler();
    initialLocation = new Location(0,0, Orientation.N);
  }

  /*
   * In this case i want to demonstrate that the example of Java test sent to me via email
   * is wrong for this path: AAAAIAA, because if the initial location is (0, 0, N) after
   * apply this path the final location must be (-2, 4, W) and not (-2, 4, N) as the problem
   * description says
   */
  @Test
  public void AAAAIAA() {
    String path = "AAAAIAA";
    Location newLocation = locationHandler.getNewLocation(initialLocation, maxBlocksAround, path);
    Assert.assertEquals(-2, newLocation.getX());
    Assert.assertEquals(4, newLocation.getY());
    Assert.assertEquals(Orientation.W, newLocation.getOrientation());
  }

  /*
   * The drones has a constrain about the blocks around where they can do the deliveries. If the delivery
   * path is out of this blocks around the application throws an exception indicating this problem.
   * In this case de max blocks around to delivery the lunches is 10 block, so if i want advance 11
   * blocks an exception is thrown. The next 4 test verify this behavior for the 4 cardinal points
   */
  @Test(expected = OutOfDeliveryRangeException.class)
  public void pathOutOfRangeToNorth() {
    String path = "AAAAAAAAAAA";
    locationHandler.getNewLocation(initialLocation, maxBlocksAround, path);
  }

  @Test(expected = OutOfDeliveryRangeException.class)
  public void pathOutOfRangeToSouth() {
    String path = "IIAAAAAAAAAAA";
    locationHandler.getNewLocation(initialLocation, maxBlocksAround, path);
  }

  @Test(expected = OutOfDeliveryRangeException.class)
  public void pathOutOfRangeToEast() {
    String path = "DAAAAAAAAAAA";
    locationHandler.getNewLocation(initialLocation, maxBlocksAround, path);
  }

  @Test(expected = OutOfDeliveryRangeException.class)
  public void pathOutOfRangeToWest() {
    String path = "IAAAAAAAAAAA";
    locationHandler.getNewLocation(initialLocation, maxBlocksAround, path);
  }

}
