package model;

/**
 * Represents a standard RGB pixel within an image that contains a red, green, blue, and alpha color
 * value between 0 and an indicated upper boundary limit.
 */
public interface IMEPixel {
  /**
   * Getter for red value, returns the integer red component of this Pixel.
   *
   * @return the integer red component of this Pixel.
   */
  int getRed();

  /**
   * Getter for green value, returns the integer green component of this Pixel.
   *
   * @return the integer green component of this Pixel.
   */
  int getGreen();

  /**
   * Getter for blue value, returns the integer blue component of this Pixel.
   *
   * @return the integer blue component of this Pixel.
   */
  int getBlue();

  /**
   * Getter for alpha value, returns the integer alpha component of this Pixel.
   *
   * @return the integer alpha component of this Pixel.
   */
  int getAlpha();
}
