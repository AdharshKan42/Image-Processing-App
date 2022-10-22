package controller.commands;

import java.util.Scanner;

import model.IMEModel;

/**
 * Represents a Command object to transform images using a specified component.
 */
public class Transform implements IMECommand {
  private String imageName;
  private String newImageName;
  private String component;

  /**
   * Constructs a new Transform command object.
   * @param s the scanner from which the name of the image to transform and the name to refer to the
   *          newly generated image as are parsed
   * @param component the component to transform
   */
  public Transform(Scanner s, String component) {
    imageName = s.next();
    newImageName = s.next();
    this.component = component;
  }

  /**
   * Constructs a new Transform command object.
   *
   * @param component the component to transform
   */
  public Transform(String component) {
    imageName = "";
    newImageName = "";
    this.component = component;
  }

  @Override
  public void doThing(IMEModel model) throws IllegalArgumentException {
    model.transform(component, imageName, newImageName);
  }

  @Override
  public String description() {
    return "Transformed image " + imageName + " to " + component
            + " and stored it in the model as " + newImageName + "\n";
  }
}
