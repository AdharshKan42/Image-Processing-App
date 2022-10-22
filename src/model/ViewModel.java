package model;

/**
 * Represents a View Only version of an IMEModel, to be used by the IMEView to display data to the
 * user interacting with the program. Displays the Pixel at a specific position, an Image's width,
 * and its height to encapsulate as much data as necessary.
 */
public class ViewModel implements IMEViewModel {
  private final IMEModel model;

  /**
   * Main constructor for ViewModel, takes in an IMEModel and implements IMEViewModel, meaning
   * the ViewModel object is only able to output view-only functions.
   *
   * @param model IMEModel inputted used by application
   * @throws IllegalArgumentException if the inputted IMEModel is null
   */
  public ViewModel(IMEModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Inputted IMEModel is null.");
    }
    this.model = model;
  }

  @Override
  public IMEPixel getPixelAt(String imageName, int row, int col) {
    return model.getPixelAt(imageName, row, col);
  }

  @Override
  public int getWidth(String imageName) {
    return model.getWidth(imageName);
  }

  @Override
  public int getHeight(String imageName) {
    return model.getHeight(imageName);
  }

}
