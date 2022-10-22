package model.functions;

/**
 * Filter Function Object that specifically Sharpens the inputted IMEPixel[][], using the 5x5 kernel
 * matrix below.
 */
public class Sharpen extends AbstractFilter {

  /**
   * Main Constructor for Blur, instantiates the 5x5 kernel applied to each Pixel's components.
   */
  public Sharpen() {
    kernel = new double[][]{{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
      {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
      {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
      {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
      {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

  }
}
