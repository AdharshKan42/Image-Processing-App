package model;

/**
 * This interface represents the operations offered by the Image Processing Model. Each of these
 * operations perform some modifications to an inputted IMEImage and has the ability to save these
 * changes to separate files. An object of the model represents one Image Processing application
 * with the ability to input, store, modify, and output multiple images.
 */
public interface IMEModel extends IMEViewModel {

  /**
   * Visualizes various components (or channels) of an Image's pixels as a greyscale image, with
   * each of the components making the RGB values in a singular pixel be equivalent to one value
   * (each pixel has its own unique value its RGB components are set to).
   *
   * @param component    component being visualized (either "red", "green", "blue", "intensity",
   *                     "luma", "value")
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after visualization), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void visualize(String component, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Takes an image and flips it in a specified direction (either "horizontal" or "vertical"),
   * converted the entire image to this flipped version.
   *
   * @param component    type of flipping operation (either "horizontal" or "vertical")
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after flipping), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void flip(String component, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Takes an image and downscales it to the appropriate dimensions, as inputted, and
   * converts the entire image to this downscaled version.
   *
   * @param width of the resulting downscaled image (from 0 to less than original width)
   * @param height of the resulting downscaled image (from 0 to less than original height)
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after flipping), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void downscale(int width, int height, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Changes the brightness of the components of an Image's pixels, in either the positive or
   * negative direction based on the increment, while clamping down at 0 and 255 (inclusive) for a
   * Pixel's component values.
   *
   * @param increment    that changes the value of each component in a Pixel, either brightening or
   *                     darkening the image
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after flipping), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void changeBrightness(int increment, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Inputs an IMEImage into the Model, storing it internally for use with operations later.
   *
   * @param imageName the name of the image as stored within the IMEModel (as designated by the
   *                  *                  user)
   * @param image     the inputted IMEImage
   * @throws IllegalArgumentException if the inputted image or string imageName is null
   */
  void putImage(String imageName, IMEImage image) throws IllegalArgumentException;

  /**
   * Filters each pixel within an IMEImage based on a specific odd dimensioned 2D square matrix
   * known as a kernel, specifically for components of an Image's pixels. Saves this to a new
   * IMEImage.
   *
   * @param type         of filtering operation (either "blur" or "sharpen")
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after flipping), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void filter(String type, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Transforms an image by applying a three by three matrix on each pixel (represented as a 3 by 1
   * matrix of its RGB components) and saves this to a new IMEImage.
   *
   * @param component    type of filtering operation (either "greyscale" or "sepia")
   * @param imageName    the name of the image as stored within the IMEModel (as designated by the
   *                     user)
   * @param newImageName the name desired for the augmented image (after flipping), where it's
   *                     stored within the IMEModel
   * @throws IllegalArgumentException if the inputted image is null or the visualize function object
   *                                  cannot be found
   */
  void transform(String component, String imageName, String newImageName)
          throws IllegalArgumentException;

  /**
   * Returns an IMEImage given the name of the image being requested.
   *
   * @param imageName the name of the IMEImage being requested as a String
   * @return the IMEImage being requested from the model
   */
  IMEImage getImage(String imageName);
}
