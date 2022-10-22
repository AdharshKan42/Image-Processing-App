package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the blue component of
 * each pixel.
 */
public class VisualizeBlue extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return pixel.getBlue();
  }
}
