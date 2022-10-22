package model;

import java.util.function.Function;

/**
 * An Image, inputted from the user, containing a 2D-array of Pixel Objects to represent every
 * pixel's data and position in the original image.
 */
public interface IMEImage {

  /**
   * Maps through all the IMEPixels and applies a Function object on each pixel (in the order
   * determined by the function object), and returns a new IMEImage with these modifications.
   * The function object uses the indices of the 2D array of pixels to perform its operation (why
   * the 2D array is inputted into the function object).
   *
   * @param function inputted Function Object that performs some operation on an inputted IMEPixel
   *                 and outputs a new IMEPixel. Takes in a 2D array of Pixels to use its indices
   * @return a new IMEImage with the changes applied by the inputted Function Object
   */
  public IMEImage map2D(Function<IMEPixel[][], IMEPixel[][]> function);

  /**
   * Maps through all the IMEPixels, row by row, within an IMEImage, and applies the inputted
   * Function to each Pixel, returning a new IMEImage with these modifications.
   *
   * @param function inputted Function Object that performs some operation on an inputted IMEPixel
   *                 and outputs a new IMEPixel
   * @return a new IMEImage with the changes applied by the inputted Function Object
   */
  public IMEImage map(Function<IMEPixel, IMEPixel> function);

  /**
   * Converts an Image into the ASCII PPM file format (outputted as a String to be written to any
   * file).
   *
   * @return Image data as a String in readable ASCII PPM format
   */
  public String toPPM();

  /**
   * Returns the IMEPixel Object at a desired row and column within the Image boundaries.
   *
   * @param row row of desired IMEPixel Object
   * @param col column of desired IMEPixel Object
   * @return IMEPixel object at a certain inputted row and column.
   * @throws IllegalArgumentException if the inputted row, column position isn't within the
   *                                  boundaries of the image
   */
  public IMEPixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Getter that return the width, in pixels, of this IMEImage.
   *
   * @return an integer width of this IMEImage, as in the number of pixels across
   */
  public int getWidth();

  /**
   * Getter that return the height, in pixels, of this IMEImage.
   *
   * @return an integer height of this IMEImage, as in the number of pixels down
   */
  public int getHeight();

}
