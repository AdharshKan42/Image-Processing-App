package model.functions;

/**
 * Filter Function Object that specifically Blurs the inputted IMEPixel[][], using the 3x3 kernel
 * matrix below.
 */
public class Blur extends AbstractFilter {

  /**
   * Main Constructor for Blur, instantiates the 3x3 kernel applied to each Pixel's components.
   */
  public Blur() {
    kernel = new double[][]{{(double) 1 / 16, (double) 1 / 8, (double) 1 / 16},
      {(double) 1 / 8, (double) 1 / 4, (double) 1 / 8},
      {(double) 1 / 16, (double) 1 / 8, (double) 1 / 16}};
  }

}
