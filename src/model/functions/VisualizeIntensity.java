package model.functions;

import model.IMEPixel;

/**
 * Visualize Function Object that displays a greyscale image depending on the intensity of the
 * components of the pixel.
 */
public class VisualizeIntensity extends AbstractVisualize {
  @Override
  protected int computeValue(IMEPixel pixel) {
    return (int) ((0.0 + pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
  }
}
