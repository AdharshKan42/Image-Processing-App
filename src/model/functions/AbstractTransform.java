package model.functions;

import java.util.function.Function;

import model.IMEPixel;
import model.Pixel;

/**
 * Function Object for the Abstract transform class that applies a three by three over each pixel's
 * component, outputting the augmented Pixel.
 */
public abstract class AbstractTransform implements Function<IMEPixel, IMEPixel> {

  protected double[][] transformMatrix;

  /**
   * Main Constructor for AbstractTransform, instantiates the transformMatrix to be of size
   * three by three.
   */
  public AbstractTransform() {
    transformMatrix = new double[3][3];
  }

  @Override
  public IMEPixel apply(IMEPixel pixel) {
    double[] pixelValues = matrixMultiplication(transformMatrix, pixel);
    return new Pixel((int) pixelValues[0], (int) pixelValues[1], (int) pixelValues[2]);
  }

  //multiplies an inputted matrix with an IMEPixel
  //Taken Inspiration from https://www.baeldung.com/
  private double[] matrixMultiplication(double[][] transformMatrix, IMEPixel pixel) {
    double[] pixelArr = {(double) pixel.getRed(), (double) pixel.getGreen(),
        (double) pixel.getBlue()};

    double[] resArr = new double[3];

    for (int i = 0; i < 3; i++) {
      resArr[i] = matrixMultCell(transformMatrix, pixelArr, i);
    }

    return resArr;
  }

  //Multiples together a specific cell within the outputted matrix
  private double matrixMultCell(double[][] transform, double[] pixel, int row) {
    double cell = 0;
    cell += (transform[row][0] * pixel[0]);
    cell += (transform[row][1] * pixel[1]);
    cell += (transform[row][2] * pixel[2]);

    if (cell > 255) {
      return 255;
    } else if (cell < 0) {
      return 0;
    }
    return cell;
  }
}
