import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Testing Function object for an IMEPixel that subtracts one from each of the R,G,and B values
 * of the inputted pixel, outputs these changes in the form of a new Pixel.
 */
public class Subtract1 implements Function<IMEPixel, IMEPixel> {

  /**
   * Apply method for Function Object, performs some operation on an IMEPixel and outputs a
   * new IMEPixel.
   *
   * @param pixel the function argument (one IMEPixel to perform operations on)
   * @return an augmented IMEPixel
   */
  @Override
  public IMEPixel apply(IMEPixel pixel) {
    return new Pixel(pixel.getRed() - 1, pixel.getGreen() - 1,
            pixel.getBlue() - 1, pixel.getAlpha() - 1);
  }
}