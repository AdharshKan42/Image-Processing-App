package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the green component of
 * each pixel.
 */
public class VisualizeGreen extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return pixel.getGreen();
  }
}
