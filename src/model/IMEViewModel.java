package model;

/**
 * Represents a View Only version of an IMEModel, to be used by the IMEView to display data to the
 * user interacting with the program. Displays the Pixel at a specific position, an Image's width,
 * and its height to encapsulate as much data as necessary.
 */
public interface IMEViewModel {
  /**
   * Returns the IMEPixel for a specific Image at a given row and column location.
   *
   * @param imageName the name of the IMEImage being located, as stored within the Model
   * @param row       of the Pixel trying to be retrieved
   * @param col       of the Pixel trying to be retrieved
   * @return the IMEPixel for a specific Image at a given row and column location
   */
  public IMEPixel getPixelAt(String imageName, int row, int col);

  /**
   * Gets the width for a specific Image Name, as stored within the Model.
   *
   * @param imageName the name of the IMEImage being located, as stored within the Model
   * @return the integer width for a specific Image Name, as stored within the Model
   */
  public int getWidth(String imageName);

  /**
   * Gets the height for a specific Image Name, as stored within the Model.
   *
   * @param imageName the name of the IMEImage being located, as stored within the Model
   * @return the integer height for a specific Image Name, as stored within the Model
   */
  public int getHeight(String imageName);
}
