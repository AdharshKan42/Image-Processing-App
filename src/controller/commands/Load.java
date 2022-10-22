package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IMEImage;
import model.IMEModel;
import model.IMEPixel;
import model.Image;
import model.Pixel;

/**
 * Constructs a command object which loads the image stored at the given file name into the
 * model as the given image name.
 */
public class Load implements IMECommand {
  private final String fileName;
  private final String imageName;
  private final IMEModel model;

  /**
   * Creates a new Load command object.
   *
   * @param s the Scanner from which the file name to load the image from
   *          and the name to represent it as in the model are parsed
   */
  public Load(Scanner s, IMEModel model) {
    fileName = s.next();
    imageName = s.next();
    this.model = model;
  }

  /**
   * Creates a new Load command object.
   *
   * @param fileName the file name of the image being loaded into the application
   * @param model the model the image is now being stored in and operations are performed on it
   */
  public Load(String fileName, IMEModel model) {
    this.fileName = fileName;
    this.imageName = "";
    this.model = model;
  }

  /**
   * Loads a PPM image.
   *
   * @param fileName  the file name to load from
   * @param imageName the name to store the image under in the model
   * @throws IllegalArgumentException if a file does not exist under the given file name
   */
  private void loadImageOld(String fileName, String imageName) throws IllegalArgumentException {
    Scanner sc;
    IMEPixel[][] pixels;
    String token;
    int width;
    int height;
    int maxValue;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + fileName + "not found!");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    width = sc.nextInt();
    height = sc.nextInt();
    maxValue = sc.nextInt();

    pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        if (r > maxValue || g > maxValue || b > maxValue) {
          throw new IllegalArgumentException("Pixel(" + i + ", " + j + ") has R, G, or B value" +
                  "greater than maximum allowed color value.");
        }

        pixels[i][j] = new Pixel(r, g, b, 255);
      }
    }

    IMEImage image = new Image(pixels, maxValue);
    model.putImage(imageName, image);
  }

  @Override
  public void doThing(IMEModel model) throws IllegalArgumentException {
    BufferedImage img;

    try {
      img = ImageIO.read(new File(fileName));
    } catch (IOException e) {
      throw new IllegalArgumentException("input file not found");
    }

    if (img == null) {
      loadImageOld(fileName, imageName);
      return;
    }

    IMEPixel[][] pixels = new Pixel[img.getWidth()][img.getHeight()];

    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        //inspired by users icza and laplasz on stackoverflow
        int rgb = img.getRGB(i, j);
        int blue = rgb & 0xff;
        int green = (rgb & 0xff00) >> 8;
        int red = (rgb & 0xff0000) >> 16;
        int alpha = (rgb & 0xff000000) >>> 24;
        //if file format doesn't have alpha, default to completely opaque
        if (alpha == -1) {
          alpha = 255;
        }
        pixels[i][j] = new Pixel(red, green, blue, alpha);
      }
    }
    model.putImage(imageName, new Image(pixels, 255));
  }

  @Override
  public String description() {
    return "Loaded image from " + fileName + " and stored it in the model as " + imageName + "\n";
  }
}
