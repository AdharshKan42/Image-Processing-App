import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Testing Function object that inputs and outputs a 2D array of IMEPixels. This adds one to each
 * of the R,G,and B values of the inputted pixels, outputs these changes in the form of a 2D array
 * of new IMEPixels.
 */
public class Add1 implements Function<IMEPixel[][], IMEPixel[][]> {

  /**
   * Apply method for Function Object, performs operation on 2D array of IMEPixels and outputs
   * an augmented version of IMEPixels.
   *
   * @param pixels the function argument (2D array of IMEPixel Objects)
   * @return augmented 2D array of IMEPixel Objects
   */
  @Override
  public IMEPixel[][] apply(IMEPixel[][] pixels) {
    int height = pixels.length;
    int width = pixels[0].length;
    IMEPixel[][] newPixels = new IMEPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        pixels[r][c] = new Pixel(pixels[r][c].getRed() + 1,
                pixels[r][c].getGreen() + 1, pixels[r][c].getBlue() + 1);
        newPixels[r][c] = pixels[r][c];
      }
    }
    return newPixels;
  }

}