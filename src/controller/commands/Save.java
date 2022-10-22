package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IMEImage;
import model.IMEModel;
import model.IMEPixel;

/**
 * Represents a command object which saves (or overwrites, depending on the given String)
 * the image stored as the input image name to the input directory.
 */
public class Save implements IMECommand {
  private String fileName;
  private String imageName;
  private IMEImage image;

  /**
   * Constructs a new Save command object.
   * @param s the Scanner from which the file name to save to and the image name to save are parsed.
   */
  public Save(Scanner s, IMEModel model) {
    fileName = s.next();
    imageName = s.next();
    image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("image not found");
    }
    File file = new File(fileName);
  }

  /**
   * Constructs a new Save command object.
   *
   * @param fileName  the file name of the image being saved and exported from the application
   * @param model the model the image is now being stored in and operations are performed on it
   */
  public Save(String fileName, IMEModel model) {
    this.fileName = fileName;
    this.imageName = "";
    this.image = model.getImage("");
  }

  /**
   * Saves an image as a PPM file.
   * @param fileName the name of the directory to save to
   * @throws IllegalArgumentException if the given directory cannot be written to
   */
  private void saveImageOld(String fileName) throws IllegalArgumentException {
    String toPPM = image.toPPM();

    try {
      FileWriter writer = new FileWriter(fileName);
      writer.append(toPPM);
      writer.close();
    }
    catch (IOException e) {
      throw new IllegalArgumentException("IO exception when writing to file");
    }
  }

  /**
   * Saves an image without an alpha component.
   * @param fileName the name of the directory to save the image to
   * @throws IllegalArgumentException if the given directory cannot be written to
   */
  private void save24Bit(String fileName) throws IllegalArgumentException {
    String[] fileNameArray = fileName.split("\\.");
    String fileExt = fileNameArray[fileNameArray.length - 1];
    BufferedImage img =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        IMEPixel currPixel = image.getPixelAt(i, j);
        int red = currPixel.getRed();
        int green = currPixel.getGreen();
        int blue = currPixel.getBlue();
        int rgb = ((red & 0x0ff) << 16)
                | ((green & 0x0ff) << 8)
                | (blue & 0xff);
        img.setRGB(i, j, rgb);
      }
    }
    try {
      ImageIO.write(img, fileExt, new File(fileName));
    }
    catch (IOException e) {
      throw new IllegalArgumentException("could not write given image to given file");
    }
  }

  @Override
  public void doThing(IMEModel model) {
    String[] fileNameArray = fileName.split("\\.");
    String fileExt = fileNameArray[fileNameArray.length - 1];

    // save as ppm
    if (fileExt.equals("ppm")) {
      saveImageOld(fileName);
    }

    // save without alpha (java 11 does not support 32-bit bmp images)
    else if (fileExt.equals("jpg") || fileExt.equals("bmp")) {
      save24Bit(fileName);
    }

    // save with alpha
    else {
      BufferedImage img =
              new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

      for (int i = 0; i < image.getWidth(); i++) {
        for (int j = 0; j < image.getHeight(); j++) {
          IMEPixel currPixel = image.getPixelAt(i, j);
          int red = currPixel.getRed();
          int green = currPixel.getGreen();
          int blue = currPixel.getBlue();
          int alpha = currPixel.getAlpha();
          int rgb = ((alpha & 0x0ff) << 24)
                  | ((red & 0x0ff) << 16)
                  | ((green & 0x0ff) << 8)
                  | (blue & 0xff);
          img.setRGB(i, j, rgb);
        }
      }
      try {
        ImageIO.write(img, fileExt, new File(fileName));
      }
      catch (IOException e) {
        throw new IllegalArgumentException("could not write given image to given file");
      }
    }
  }

  @Override
  public String description() {
    return "Saved image " + imageName + " to directory " + fileName + "\n";
  }
}
