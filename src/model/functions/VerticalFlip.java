package model.functions;

/**
 * Flip Function Object that specifically flips the image vertically (top to bottom).
 */
public class VerticalFlip extends AbstractFlip {
  @Override
  protected int[] computeReplacement(int row, int col, int height, int width) {
    return new int[]{row, width - col - 1};
  }
}
