package model.functions;

/**
 * Transform Function Object that specifically changes an image into greyscale using luma.
 */
public class TransformLuma extends AbstractTransform {

  /**
   * Main Constructor for TransformLuma, instantiates the transform matrix
   * applied to each Pixel's components.
   */
  public TransformLuma() {
    transformMatrix = new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
  }
}
