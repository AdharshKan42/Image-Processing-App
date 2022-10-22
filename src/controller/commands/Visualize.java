package controller.commands;

import java.util.Scanner;

import model.IMEModel;

/**
 * Represents a command object which generates a greyscale version of an input image name
 * using the given type of value, storing it as the input new image name.
 */
public class Visualize implements IMECommand {
  private final String imageName;
  private final String newImageName;
  private final String type;

  /**
   * Determines if the given String is a valid visualize type.
   * @param type the type
   * @return true if valid, false if invalid
   */
  private boolean isValidType(String type) {
    return (type.equals("red") || type.equals("green") || type.equals("blue")
            || type.equals("luma") || type.equals("value") || type.equals("intensity"));
  }

  /**
   * Constructs a new Visualize command object.
   * @param s the Scanner from which the image name to be greyscaled and the new image name to which
   *          the new version of the image is to be stored
   * @param type the value to be visualized
   * @throws IllegalArgumentException if an invalid type is provided
   */
  public Visualize(Scanner s, String type) throws IllegalArgumentException {
    imageName = s.next();
    newImageName = s.next();
    if (! isValidType(type)) {
      throw new IllegalArgumentException("invalid type provided");
    }
    this.type = type;
  }

  /**
   * Constructs a new Visualize command object.
   *
   * @param type the value to be visualized
   */
  public Visualize(String type) {
    imageName = "";
    newImageName = "";
    this.type = type;
  }

  @Override
  public void doThing(IMEModel model) throws IllegalArgumentException {
    model.visualize(type, imageName, newImageName);
  }

  @Override
  public String description() {
    return "Created a greyscale version of image " + imageName + " by visualizing its "
            + type + " component and stored it in the model as " + newImageName + "\n";
  }
}
