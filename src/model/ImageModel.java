package model;

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
 * Represents an IMEModel that can perform operations such as visualize, changing the brightness,
 * flipping, filtering, and transforming inputted images. Is able to perform these operations and
 * store the augmented images internally.
 */
public class ImageModel implements IMEModel {
  private Map<String, IMEImage> images;
  private final Map<String, Function<IMEPixel, IMEPixel>> visualizeFunctions;
  private final Map<String, Function<IMEPixel[][], IMEPixel[][]>> flipFunctions;
  private final Map<String, Function<IMEPixel, IMEPixel>> transformFunctions;
  private final Map<String, Function<IMEPixel[][], IMEPixel[][]>> filterFunctions;


  /**
   * Main Constructor for ImageModel, instantiates the images HashMap to be able to input IMEImages,
   * perform operations on them, and store them within the Model. This also instantiates the
   * various function objects (as described above) and places them within their respective HashMaps
   * to be called by the methods below. Each set of Function Objects are in their own separate
   * Hashmap so outside users are unable to call varying function object within methods that don't
   * relate (such as calling "red", aka VisualizeRed, within transform's function
   */
  public ImageModel() {
    images = new HashMap<>();

    //adding visualize functions
    visualizeFunctions = new HashMap<>();
    visualizeFunctions.put("red", new VisualizeRed());
    visualizeFunctions.put("green", new VisualizeGreen());
    visualizeFunctions.put("blue", new VisualizeBlue());
    visualizeFunctions.put("value", new VisualizeValue());
    visualizeFunctions.put("intensity", new VisualizeIntensity());
    visualizeFunctions.put("luma", new VisualizeLuma());

    //adding flipFunctions
    flipFunctions = new HashMap<>();
    flipFunctions.put("vertical", new VerticalFlip());
    flipFunctions.put("horizontal", new HorizontalFlip());

    //adding transform functions
    transformFunctions = new HashMap<>();
    transformFunctions.put("sepia", new TransformSepia());
    transformFunctions.put("greyscale", new TransformLuma());

    //adding filter functions
    filterFunctions = new HashMap<>();
    filterFunctions.put("blur", new Blur());
    filterFunctions.put("sharpen", new Sharpen());
  }

  @Override
  public void visualize(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);
    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }

    Function<IMEPixel, IMEPixel> visualizeFunction =
            this.visualizeFunctions.getOrDefault(component, null);
    if (visualizeFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted visualize command.");
    } else {
      this.map(visualizeFunction, imageName, newImageName);
    }
  }

  @Override
  public void flip(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);
    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }

    Function<IMEPixel[][], IMEPixel[][]> flipFunction =
            this.flipFunctions.getOrDefault(component, null);
    if (flipFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted flip command.");
    } else {
      this.map2D(flipFunction, imageName, newImageName);
    }
  }

  @Override
  public void changeBrightness(int increment, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);
    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }
    this.map(new AlterBrightness(increment), imageName, newImageName);
  }

  @Override
  public void downscale(int width, int height, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);
    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }
    this.map2D(new Downscale(width, height), imageName, newImageName);
  }

  private void map2D(Function<IMEPixel[][], IMEPixel[][]> function,
                     String imageName, String newImageName) {
    this.images.put(newImageName, this.images.get(imageName).map2D(function));
  }

  private void map(Function<IMEPixel, IMEPixel> function, String imageName, String newImageName) {
    this.images.put(newImageName, this.images.get(imageName).map(function));
  }

  @Override
  public void putImage(String imageName, IMEImage image) throws IllegalArgumentException {
    if (imageName == null || image == null) {
      throw new IllegalArgumentException("Inputted image or image name are null.");
    }
    images.put(imageName, image);
  }

  @Override
  public void filter(String type, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);
    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }

    Function<IMEPixel[][], IMEPixel[][]> filterFunction =
            this.filterFunctions.getOrDefault(type, null);
    if (filterFunction == null) {
      throw new IllegalArgumentException("Couldn't find inputted filter command.");
    } else {
      this.map2D(filterFunction, imageName, newImageName);
    }

  }

  @Override
  public void transform(String component, String imageName, String newImageName)
          throws IllegalArgumentException {
    IMEImage originalImage = this.images.getOrDefault(imageName, null);

    if (originalImage == null) {
      throw new IllegalArgumentException("Inputted image could not be found.");
    }

    Function<IMEPixel, IMEPixel> transformFunction =
            this.transformFunctions.getOrDefault(component, null);
    if (transformFunctions == null) {
      throw new IllegalArgumentException("Couldn't find inputted flip command.");
    } else {
      this.map(transformFunction, imageName, newImageName);
    }
  }

  @Override
  public IMEImage getImage(String imageName) {
    return images.getOrDefault(imageName, null);
  }


  @Override
  public IMEPixel getPixelAt(String imageName, int row, int col) {
    return images.get(imageName).getPixelAt(row, col);
  }

  @Override
  public int getWidth(String imageName) {
    return images.get(imageName).getWidth();
  }

  @Override
  public int getHeight(String imageName) {
    return images.get(imageName).getHeight();
  }
}
