package model.functions;

import java.util.function.Function;

import model.IMEPixel;

/**
 * Function Object for the Abstract Flip class that flips an image either vertically or
 * horizontally, outputting the augmented 2D array of Pixels.
 */
public abstract class AbstractFlip implements Function<IMEPixel[][], IMEPixel[][]> {
  @Override
  public IMEPixel[][] apply(IMEPixel[][] pixels) {
    int height = pixels.length;
    int width = pixels[0].length;
    IMEPixel[][] newPixels = new IMEPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int[] rep = computeReplacement(r, c, height, width);
        newPixels[r][c] = pixels[rep[0]][rep[1]];
      }
    }
    return newPixels;
  }

  /**
   * Computes the indices of an augmented Pixel originally at (row,col) and returns the
   * resulting row and col as an array of integers.
   *
   * @param row    of inputted Pixel being flipped
   * @param col    of inputted Pixel being flipped
   * @param height of image being flipped
   * @param width  of image being flipped
   * @return the resulting row and col as an array of integers
   */
  protected abstract int[] computeReplacement(int row, int col, int height, int width);
}
