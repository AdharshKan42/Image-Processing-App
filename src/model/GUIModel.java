package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.functions.AlterBrightness;
import model.functions.Blur;
import model.functions.Downscale;
import model.functions.HorizontalFlip;
import model.functions.Sharpen;
import model.functions.TransformLuma;
import model.functions.TransformSepia;
import model.functions.VerticalFlip;
import model.functions.VisualizeBlue;
import model.functions.VisualizeGreen;
import model.functions.VisualizeIntensity;
import model.functions.VisualizeLuma;
import model.functions.VisualizeRed;
import model.functions.VisualizeValue;

/**
 * Represents a GUI-Specific that can perform operations such as visualize, changing the brightness,
 * flipping, filtering, and transforming inputted images. Is able to perform these operations and
 * store the augmented images internally.
 */
public class GUIModel implements IMEModel {
  private IMEImage image;
  public static String filePath;

  /**
   * Constructor for GUIModel; instantiates object fields.
   */
  public GUIModel() {
    filePath = "WORKINGIMAGE.png";
    File file = new File(filePath);
  }

  private void map2D(Function<IMEPixel[][], IMEPixel[][]> function,
                     String imageName, String newImageName) {
    this.image = this.image.map2D(function);
  }

  private void map(Function<IMEPixel, IMEPixel> function, String imageName, String newImageName) {
    this.image = this.image.map(function);
  }

  @Override
  public void visualize(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    final Map<String, Function<IMEPixel, IMEPixel>> visualizeFunctions;

    //adding visualize functions
    visualizeFunctions = new HashMap<>();
    visualizeFunctions.put("red", new VisualizeRed());
    visualizeFunctions.put("green", new VisualizeGreen());
    visualizeFunctions.put("blue", new VisualizeBlue());
    visualizeFunctions.put("value", new VisualizeValue());
    visualizeFunctions.put("intensity", new VisualizeIntensity());
    visualizeFunctions.put("luma", new VisualizeLuma());

    Function<IMEPixel, IMEPixel> visualizeFunction =
            visualizeFunctions.getOrDefault(component, null);
    if (visualizeFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted visualize command.");
    } else {
      this.map(visualizeFunction, imageName, newImageName);
    }

  }

  @Override
  public void flip(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    final Map<String, Function<IMEPixel[][], IMEPixel[][]>> flipFunctions;

    //adding flipFunctions
    flipFunctions = new HashMap<>();
    flipFunctions.put("vertical", new VerticalFlip());
    flipFunctions.put("horizontal", new HorizontalFlip());

    Function<IMEPixel[][], IMEPixel[][]> flipFunction =
            flipFunctions.getOrDefault(component, null);
    if (flipFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted flip command.");
    } else {
      this.map2D(flipFunction, imageName, newImageName);
    }
  }

  @Override
  public void changeBrightness(int increment, String imageName, String newImageName)
          throws IllegalArgumentException {
    this.map(new AlterBrightness(increment), imageName, newImageName);
  }

  @Override
  public void putImage(String imageName, IMEImage image) throws IllegalArgumentException {
    this.image = image;
  }

  @Override
  public void filter(String type, String imageName, String newImageName)
          throws IllegalArgumentException {
    final Map<String, Function<IMEPixel[][], IMEPixel[][]>> filterFunctions;

    //adding filter functions
    filterFunctions = new HashMap<>();
    filterFunctions.put("blur", new Blur());
    filterFunctions.put("sharpen", new Sharpen());

    Function<IMEPixel[][], IMEPixel[][]> filterFunction =
            filterFunctions.getOrDefault(type, null);
    if (filterFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted filter command.");
    } else {
      this.map2D(filterFunction, imageName, newImageName);
    }
  }

  @Override
  public void transform(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    final Map<String, Function<IMEPixel, IMEPixel>> transformFunctions;

    transformFunctions = new HashMap<>();
    transformFunctions.put("sepia", new TransformSepia());
    transformFunctions.put("greyscale", new TransformLuma());

    Function<IMEPixel, IMEPixel> transformFunction =
            transformFunctions.getOrDefault(component, null);
    if (transformFunctions == null) {
      throw new IllegalArgumentException("Couldn't find inputted flip command.");
    } else {
      this.map(transformFunction, imageName, newImageName);
    }
  }

  @Override
  public IMEImage getImage(String imageName) {
    return image;
  }

  @Override
  public void downscale(int width, int height, String imageName, String newImageName) {
    this.map2D(new Downscale(width, height), imageName, newImageName);
  }

  @Override
  public IMEPixel getPixelAt(String imageName, int row, int col) {
    return image.getPixelAt(row, col);
  }

  @Override
  public int getWidth(String imageName) {
    return image.getWidth();
  }

  @Override
  public int getHeight(String imageName) {
    return image.getHeight();
  }
}
