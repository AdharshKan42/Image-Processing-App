package model.functions;

/**
 * Transform Function Object that specifically changes an image into a sepia version.
 */
public class TransformSepia extends AbstractTransform {
  /**
   * Main Constructor for TransformSepia, instantiates the transform matrix
   * applied to each Pixel's components.
   */
  public TransformSepia() {
    transformMatrix = new double[][]{{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168},
      {0.272, 0.534, 0.131}};
  }
}
