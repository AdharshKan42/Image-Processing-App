package model.functions;

import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Function Object for the Abstract filter class that applies an odd 2D matrix over each pixel's
 * component within an IMEPixel[][] depending on the surrounding pixels, outputting the augmented
 * 2D array of Pixels.
 */
public abstract class AbstractFilter implements Function<IMEPixel[][], IMEPixel[][]> {
  protected double[][] kernel;
  private IMEPixel[][] pixels;

  /**
   * Applies the Kernel to each component of a pixel and returns an augmented IMEPixel[][].
   *
   * @param pixels the function argument, inputted 2D array of Pixels representing an Image
   * @return an augmented 2D array of Pixels
   */
  @Override
  public IMEPixel[][] apply(IMEPixel[][] pixels) {
    this.pixels = pixels;
    int height = pixels.length;
    int width = pixels[0].length;
    IMEPixel[][] newPixels = new IMEPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int redValue = (int) computeKernelValAt(r, c, "red");
        int greenValue = (int) computeKernelValAt(r, c, "green");
        int blueValue = (int) computeKernelValAt(r, c, "blue");

        newPixels[r][c] = new Pixel(redValue, greenValue, blueValue, pixels[r][c].getAlpha());
      }
    }
    return newPixels;
  }

  //checks if a Pixel is outside the image dimensions it's a part of
  private boolean isPixelOutsideImageDimensions(int r, int c) {
    return (r < 0 || r > (pixels.length - 1)) || (c < 0 || c > (pixels[0].length - 1));
  }

  //computes the kernel value at for each pixel component, and clamps at 0 and 255, inclusive.
  private double computeKernelValAt(int r, int c, String component) {
    int rowDiff = r - (kernel.length / 2);
    int colDiff = c - (kernel.length / 2);

    double returnVal = 0.0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        int pixelVal;

        if (isPixelOutsideImageDimensions(i + rowDiff, j + colDiff)) {
          pixelVal = 0;
        } else {
          switch (component) {
            case "red":
              pixelVal = pixels[i + rowDiff][j + colDiff].getRed();
              break;
            case "green":
              pixelVal = pixels[i + rowDiff][j + colDiff].getGreen();
              break;
            case "blue":
              pixelVal = pixels[i + rowDiff][j + colDiff].getBlue();
              break;
            case "alpha":
              pixelVal = pixels[i + rowDiff][j + colDiff].getAlpha();
              break;
            default:
              pixelVal = 0;
          }
        }
        returnVal += (kernel[i][j] * pixelVal);
      }
    }

    if (returnVal > 255.0) {
      return 255.0;
    } else if (returnVal < 0.0) {
      return 0.0;
    }
    return returnVal;
  }
}
