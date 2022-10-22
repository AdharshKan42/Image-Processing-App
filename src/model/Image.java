package model;

import java.util.function.Function;

/**
 * An Image, inputted from the user, containing a 2D-array of Pixel Objects to represent every
 * pixel's data and position in the original image.
 */
public class Image implements IMEImage {
  private final IMEPixel[][] pixels;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * The Main Constructor for Image that instantiates image using the 2D-array of Pixels and
   * the maximum RGB value threshold.
   *
   * @param pixels   2D array of Pixels containing color data and location of each respective pixel
   *                 in original image
   * @param maxValue maximum upper boundary for any RGB value within the Image
   * @throws IllegalArgumentException if the maxValue is less than 0 or the inputted pixels array
   *                                  is null
   */
  public Image(IMEPixel[][] pixels, int maxValue) throws IllegalArgumentException {
    if (maxValue < 0 || pixels == null) {
      throw new IllegalArgumentException("Image inputs must be valid.");
    }
    this.pixels = pixels;
    this.width = pixels.length;
    this.height = pixels[0].length;
    this.maxValue = maxValue;
  }

  @Override
  public String toPPM() {
    StringBuilder ppmString = new StringBuilder();
    ppmString.append("P3\n");
    ppmString.append(this.width + " " + this.height + "\n");
    ppmString.append(this.maxValue + "\n");

    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        IMEPixel curr = this.pixels[c][r];

        ppmString.append(curr.getRed());
        ppmString.append("\n");
        ppmString.append(curr.getGreen());
        ppmString.append("\n");
        ppmString.append(curr.getBlue());
        ppmString.append("\n");
      }
    }

    return ppmString.toString();
  }

  @Override
  public IMEImage map2D(Function<IMEPixel[][], IMEPixel[][]> function) {
    IMEPixel[][] newPixels = function.apply(this.pixels);
    return new Image(newPixels, this.maxValue);
  }

  @Override
  public IMEImage map(Function<IMEPixel, IMEPixel> function) {
    IMEPixel[][] newPixels = new Pixel[this.width][this.height];

    for (int r = 0; r < this.width; r++) {
      for (int c = 0; c < this.height; c++) {
        newPixels[r][c] = function.apply(this.pixels[r][c]);
      }
    }

    return new Image(newPixels, this.maxValue);
  }

  @Override
  public IMEPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || (row > pixels.length - 1)) || ((col < 0 || (col > pixels[0].length - 1)))) {
      throw new IllegalArgumentException("Inputted pixel location isn't valid; outside boundaries" +
              "of this image.");
    }
    IMEPixel target = this.pixels[row][col];
    return new Pixel(target.getRed(), target.getGreen(), target.getBlue(), target.getAlpha());
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

}
