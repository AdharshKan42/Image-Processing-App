package controller.commands;

import java.util.Scanner;

import model.IMEModel;

/**
 * Represents a command object to filter specific components of images.
 */
public class Filter implements IMECommand {
  private String imageName;
  private String newImageName;
  private String component;

  /**
   * Constructs a new Filter command object.
   * @param s the scanner from which the name of the image to filter and the name to refer to the
   *          newly generated image as are parsed
   * @param component the component to filter
   */
  public Filter(Scanner s, String component) {
    imageName = s.next();
    newImageName = s.next();
    this.component = component;
  }

  /**
   * Constructs a new Filter Command object.
   *
   * @param component the type of component that needs to be filtered (passed to the model)
   */
  public Filter(String component) {
    this.component = component;
    imageName = "";
    newImageName = "";
  }

  @Override
  public void doThing(IMEModel model) throws IllegalArgumentException {
    model.filter(component, imageName, newImageName);
  }

  @Override
  public String description() {
    return "Filtered + " + component + " component of image " + imageName
            + " and stored it in the model as " + newImageName + "\n";
  }
}
