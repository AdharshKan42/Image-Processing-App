package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the luma of the
 * components of the pixel.
 */
public class VisualizeLuma extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return (int) (pixel.getRed() * 0.2126 + pixel.getGreen() * 0.7152 + pixel.getBlue() * 0.0722);
  }
}
