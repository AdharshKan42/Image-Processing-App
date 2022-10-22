package model.functions;

import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Represents a Downscale function object.
 */
public class Downscale implements Function<IMEPixel[][], IMEPixel[][]> {
  private int newWidth;
  private int newHeight;
  private IMEPixel[][] pixels;

  /**
   * Constructor for a Downscale function object.
   * @param width width
   * @param height height
   */
  public Downscale(int width, int height) {
    this.newWidth = width;
    this.newHeight = height;
  }

  @Override
  public IMEPixel[][] apply(IMEPixel[][] pixels) {
    int width = pixels[0].length;
    int height = pixels.length;
    if (newWidth == 0) {
      newWidth = pixels[0].length;
    }
    if (newHeight == 0) {
      newHeight = pixels.length;
    }
    this.pixels = pixels;
    IMEPixel[][] newPixels = new IMEPixel[newHeight][newWidth];

    for (int r = 0; r < newHeight; r++) {
      for (int c = 0; c < newWidth; c++) {
        double oldR = ((double) r / width) * newWidth;
        double oldC = ((double) c / height) * newHeight;

        int redValue;
        int greenValue;
        int blueValue;
        int alphaValue;

        alphaValue = 255;

        IMEPixel retPixel;
        if (isNotInt(oldR) && isNotInt(oldC)) {
          redValue = computeColorAt(oldR, oldC, "red");
          greenValue = computeColorAt(oldR, oldC, "green");
          blueValue = computeColorAt(oldR, oldC, "blue");
        } else {
          redValue = pixels[r][c].getRed();
          greenValue = pixels[r][c].getGreen();
          blueValue = pixels[r][c].getBlue();
        }

        newPixels[r][c] = new Pixel(redValue, greenValue, blueValue, alphaValue);
      }
    }

    return newPixels;
  }

  private boolean isNotInt(double val) {
    return val == Math.rint(val);
  }

  private int computeColorAt(double r, double c, String component) {
    int x = 0;
    int y = 0;

    x = (int) Math.floor(r);
    y = (int) Math.floor(c);
    IMEPixel aPix = new Pixel(pixels[x][y].getRed(),
            pixels[x][y].getGreen(),
            pixels[x][y].getBlue());

    x = (int) Math.ceil(r);
    y = (int) Math.floor(c);
    IMEPixel bPix = new Pixel(pixels[x][y].getRed(),
            pixels[x][y].getGreen(),
            pixels[x][y].getBlue());

    x = (int) Math.floor(r);
    y = (int) Math.ceil(c);
    IMEPixel cPix = new Pixel(pixels[x][y].getRed(),
            pixels[x][y].getGreen(),
            pixels[x][y].getBlue());

    x = (int) Math.ceil(r);
    y = (int) Math.ceil(c);
    IMEPixel dPix = new Pixel(pixels[x][y].getRed(),
            pixels[x][y].getGreen(),
            pixels[x][y].getBlue());


    int color_a;
    int color_b;
    int color_c;
    int color_d;

    switch (component) {
      case "red":
        color_a = aPix.getRed();
        color_b = bPix.getRed();
        color_c = cPix.getRed();
        color_d = dPix.getRed();
        break;
      case "green":
        color_a = aPix.getGreen();
        color_b = bPix.getGreen();
        color_c = cPix.getGreen();
        color_d = dPix.getGreen();
        break;
      case "blue":
        color_a = aPix.getBlue();
        color_b = bPix.getBlue();
        color_c = cPix.getBlue();
        color_d = dPix.getBlue();
        break;
      case "alpha":
        color_a = aPix.getAlpha();
        color_b = bPix.getAlpha();
        color_c = cPix.getAlpha();
        color_d = dPix.getAlpha();
        break;
      default:
        color_a = 0;
        color_b = 0;
        color_c = 0;
        color_d = 0;
    }

    double color = 0.0;
    double m = color_b * (r - Math.floor(r)) + color_a * (Math.ceil(r) - r);
    double n = color_d * (r - Math.floor(r)) + color_c * (Math.ceil(r) - r);

    color = n * (c - Math.floor(c)) + m * (Math.ceil(c) - c);

    return (int) color;
  }
}
