package model.functions;

/**
 * Flip Function Object that specifically flips the image horizontally (left to right).
 */
public class HorizontalFlip extends AbstractFlip {
  @Override
  protected int[] computeReplacement(int row, int col, int height, int width) {
    return new int[]{height - row - 1, col};
  }
}
