package model.functions;

import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;


/**
 * Function Object for the AlterBrightness class that adds a positive or negative value to each
 * of the components within an IMEPixel, and outputs the augmented IMEPixel.
 */
public class AlterBrightness implements Function<IMEPixel, IMEPixel> {
  private int increment;
  private int maxRGB;

  /**
   * Constructor for AlterBrightness that automatically sets the maxRGB value to 255 and sets
   * the increment that each RGB value should be changed by.
   *
   * @param increment the integer amount each RGB value should be changed by
   */
  public AlterBrightness(int increment) {
    this(increment, 255);
  }

  /**
   * Main Constructor for AlterBrightness that  sets the maxRGB value and sets
   * the increment that each RGB value should be changed by.
   *
   * @param increment the integer amount each RGB value should be changed by
   * @param maxRGB    the maximum value each component within IMEPixel can reach
   */
  public AlterBrightness(int increment, int maxRGB) {
    this.increment = increment;
    this.maxRGB = maxRGB;
  }

  @Override
  public IMEPixel apply(IMEPixel pixel) {
    return clampBrightness(pixel, increment);
  }

  //clamps the brightness within a pixel's component to between 0 and 255, inclusive while also
  //altering the brightness of each pixel's component
  private IMEPixel clampBrightness(IMEPixel pixel, int value) {
    int red = pixel.getRed();
    int green = pixel.getGreen();
    int blue = pixel.getBlue();

    int[] arr = {red, green, blue};

    for (int i = 0; i < arr.length; i++) {
      if ((arr[i] + value) > this.maxRGB) {
        arr[i] = this.maxRGB;
      } else if ((arr[i] + value) < 0) {
        arr[i] = 0;
      } else {
        arr[i] += value;
      }
    }
    return new Pixel(arr[0], arr[1], arr[2], pixel.getAlpha());
  }
}
