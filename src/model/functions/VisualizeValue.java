package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the value of the
 * components of the pixel.
 */
public class VisualizeValue extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return Math.max(pixel.getRed(), Math.max(pixel.getGreen(), pixel.getBlue()));
  }
}
