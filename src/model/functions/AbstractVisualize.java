package model.functions;

import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Function Object for the Abstract visualize class that changes an IMEPixel into a respective
 * greyscale IMEPixel.
 */
public abstract class AbstractVisualize implements Function<IMEPixel, IMEPixel> {
  @Override
  public IMEPixel apply(IMEPixel pixel) {
    int value = computeValue(pixel);
    return new Pixel(value, value, value, pixel.getAlpha());
  }

  /**
   * Computes the greyscale value for each RGB value, as specified by each implementation.
   *
   * @param pixel inputted IMEPixel upon which greyscale operations are performed
   * @return the component value, as an integer, for the augmented greyscale pixel
   */
  protected abstract int computeValue(IMEPixel pixel);
}
