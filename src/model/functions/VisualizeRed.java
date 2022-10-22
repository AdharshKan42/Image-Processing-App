package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the red component of
 * each pixel.
 */
public class VisualizeRed extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return pixel.getRed();
  }
}
