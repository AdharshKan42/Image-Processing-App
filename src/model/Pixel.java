package model;

/**
 * Represents a standard RGB Pixel within an Image, with Red, Green, Blue,
 * and Alpha components between 0 and 255, inclusive as integers.
 */
public class Pixel implements IMEPixel {
  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * RGB constructor for a Pixel without a specified Alpha value,
   * checks if the inputted RGB components are between 0 and 255,
   * inclusive. Automatically sets the alpha value to 255 as this
   * is the standard for image creation.
   *
   * @param red   integer component between 0 and 255, inclusive
   * @param green integer component between 0 and 255, inclusive
   * @param blue  integer component between 0 and 255, inclusive
   * @throws IllegalArgumentException if any of the inputted RGBa values are not between
   *                                  0 and 255, inclusive
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    this(red, green, blue, 255);
  }

  /**
   * Main Constructor for Pixel Class, checks if the inputted red, green, blue, and alpha values
   * are between 0 and 255, inclusive. This then sets these inputted values to internal fields.
   *
   * @param red   integer component between 0 and 255, inclusive
   * @param green integer component between 0 and 255, inclusive
   * @param blue  integer component between 0 and 255, inclusive
   * @param alpha integer component between 0 and 255, inclusive
   * @throws IllegalArgumentException if any of the inputted RGBa values are not between
   *                                  0 and 255, inclusive
   */
  public Pixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    if ((red < 0 || red > 255) || (green < 0 || green > 255)
            || (blue < 0 || blue > 255) || (alpha < 0 || alpha > 255)) {
      throw new IllegalArgumentException("Inputted values must be between 0 and 255, inclusive");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}
