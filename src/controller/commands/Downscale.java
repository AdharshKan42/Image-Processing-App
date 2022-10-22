package controller.commands;

import java.util.Scanner;

import model.IMEModel;

/**
 * Represents a command object to change the brightness of an image.
 */
public class Downscale implements IMECommand {
  private int width;
  private int height;
  private final String imageName;
  private final String newImageName;

  /**
   * Constructs a new Downscale command object.
   * @param s the Scanner from which the width/height for downscaling are parsed, the name of the
   *          image to downscale, and the name for the new image generated by downscaling the
   *          original image are parsed
   */
  public Downscale(Scanner s) {
    height = s.nextInt();
    width = s.nextInt();
    imageName = s.next();
    newImageName = s.next();
  }

  /**
   * Constructor for GUI purposes.
   * @param width new width
   * @param height new height
   */
  public Downscale(int width, int height) {
    this.width = width;
    this.height = height;
    imageName = "";
    newImageName = "";
  }

  @Override
  public void doThing(IMEModel model) throws IllegalArgumentException {
    model.downscale(width, height, imageName, newImageName);
  }

  @Override
  public String description() {
    return "Downscale an image " + imageName + " to a size of " + width + ", " + height
            + " and stored it in the model as " + newImageName + "\n";
  }
}