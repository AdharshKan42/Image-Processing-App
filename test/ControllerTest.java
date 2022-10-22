import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.Controller;
import controller.IMEController;
import model.IMEModel;
import model.IMEPixel;
import model.ImageModel;
import model.Pixel;
import view.IMEView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the functionality, methods, and fields of the Controller.
 */
public class ControllerTest {
  private IMEController controller;
  private IMEModel model;
  private IMEView view;
  private Appendable appendable;
  private String input;
  private Readable readable;

  @Before
  public void init() {
    appendable = new StringBuilder();
    input = "";
    readable = new StringReader(input);
    model = new ImageModel();
    view = new TextView(model, appendable);
    controller = new Controller(model, view, readable);
  }

  @Test
  public void testInput() {
    input = "load res/testing3by3.ppm 3by3";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();
    String answer = "Welcome to our IME program! Please see the README included in our res\n" +
            "directory if you are a first-time user! Ready for input when you are :)\n" +
            "Loaded image from res/testing3by3.ppm and stored it in the model as 3by3\n" +
            "Ready for next input!\n";
    assertEquals(answer, appendable.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputError() {
    input = "load res/testing3by3.ppm 3by3 ijoajsdsasda";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();
  }

  @Test
  public void testConstructor() {
    try {
      controller = new Controller(null, view, readable);
    } catch (IllegalArgumentException e) {
      assertEquals("given a null input", e.getMessage());
    }
    try {
      controller = new Controller(model, null, readable);
    } catch (IllegalArgumentException e) {
      assertEquals("given a null input", e.getMessage());
    }
    try {
      controller = new Controller(model, view, null);
    } catch (IllegalArgumentException e) {
      assertEquals("given a null input", e.getMessage());
    }
    try {
      controller = new Controller(model, view, readable);
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testLoadPPM() {
    input = "load res/testing3by3.ppm 3by3";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    assertEquals(3, model.getHeight("3by3"));
    assertEquals(3, model.getWidth("3by3"));

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 3, 4)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(150, 230, 45)}, {new Pixel(234, 121, 225),
            new Pixel(124, 175, 89),
            new Pixel(163, 95, 254)}};


    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3", r, c).getBlue());

      }
    }
  }

  @Test
  public void testSave() {
    // this may fail if it has already been run because the program differentiates between save
    // and overwrite, requiring you to use the "overwrite" command if a file exists at the
    // target location
    input = "load res/testing3by3.ppm 3by3 " +
            "red-component 3by3 3by3Red " +
            "save res/testing3by3Red.ppm 3by3Red";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    File f = new File("res/testing3by3Red.ppm");
    assertTrue(f.isFile());
  }

  @Test
  public void testSavedFile() {
    input = "load res/testing3by3Red.ppm 3by3Red2";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(1, 1, 1),
            new Pixel(234, 234, 234)}, {new Pixel(0, 0, 0),
            new Pixel(2, 2, 2),
            new Pixel(124, 124, 124)}, {new Pixel(2, 2, 2),
            new Pixel(150, 150, 150),
            new Pixel(163, 163, 163)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Red2", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Red2", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Red2", r, c).getBlue());
      }
    }
  }

  @Test
  public void testVisualize() {
    // red component
    input = "load res/testing3by3.ppm 3by3 red-component 3by3 3by3Red";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    assertEquals(3, model.getHeight("3by3"));
    assertEquals(3, model.getWidth("3by3"));


    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 2, 2)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(150, 150, 150)}, {new Pixel(234, 234, 234),
            new Pixel(124, 124, 124),
            new Pixel(163, 163, 163)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Red", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Red", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Red", r, c).getBlue());
      }
    }

    // green component
    input = "load res/testing3by3.ppm 3by3 green-component 3by3 3by3Green";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(3, 3, 3)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(230, 230, 230)}, {new Pixel(121, 121, 121),
            new Pixel(175, 175, 175),
            new Pixel(95, 95, 95)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Green", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Green", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Green", r, c).getBlue());
      }
    }

    input = "load res/testing3by3.ppm 3by3 blue-component 3by3 3by3Blue";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(4, 4, 4)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(45, 45, 45)}, {new Pixel(225, 225, 225),
            new Pixel(89, 89, 89),
            new Pixel(254, 254, 254)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Blue", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Blue", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Blue", r, c).getBlue());
      }
    }

    // value component
    input = "load res/testing3by3.ppm 3by3 value-component 3by3 3by3Value";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(4, 4, 4)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(230, 230, 230)}, {new Pixel(234, 234, 234),
            new Pixel(175, 175, 175),
            new Pixel(254, 254, 254)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Value", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Value", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Value", r, c).getBlue());
      }
    }

    input = "load res/testing3by3.ppm 3by3 intensity-component 3by3 3by3Intensity";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(3, 3, 3)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(141, 141, 141)}, {new Pixel(193, 193, 193),
            new Pixel(129, 129, 129),
            new Pixel(170, 170, 170)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Intensity", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Intensity", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Intensity", r, c).getBlue());
      }
    }

    // luma component
    input = "load res/testing3by3.ppm 3by3 luma-component 3by3 3by3Luma";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 2, 2)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(199, 199, 199)}, {new Pixel(152, 152, 152),
            new Pixel(157, 157, 157),
            new Pixel(120, 120, 120)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Luma", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Luma", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Luma", r, c).getBlue());
      }
    }
  }

  @Test
  public void testFlip() {
    // vertical flip
    input = "load res/testing3by3.ppm 3by3 vertical-flip 3by3 3by3VerticalFlip";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(2, 3, 4),
            new Pixel(0, 0, 0),
            new Pixel(240, 240, 240)}, {new Pixel(150, 230, 45),
            new Pixel(2, 2, 2),
            new Pixel(1, 1, 1)}, {new Pixel(163, 95, 254),
            new Pixel(124, 175, 89),
            new Pixel(234, 121, 225)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3VerticalFlip", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3VerticalFlip", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3VerticalFlip", r, c).getBlue());
      }
    }

    // horizontal flip
    input = "load res/testing3by3.ppm 3by3 horizontal-flip 3by3 3by3HorizontalFlip";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(234, 121, 225),
            new Pixel(124, 175, 89),
            new Pixel(163, 95, 254)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(150, 230, 45)}, {new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 3, 4)}};


    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3HorizontalFlip", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3HorizontalFlip", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3HorizontalFlip", r, c).getBlue());
      }
    }
  }

  @Test
  public void testChangeBrightness() {
    // brighten 10
    input = "load res/testing3by3.ppm 3by3 brighten 10 3by3 3by3Brighten10";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(250, 250, 250),
            new Pixel(10, 10, 10),
            new Pixel(12, 13, 14)}, {new Pixel(11, 11, 11),
            new Pixel(12, 12, 12),
            new Pixel(160, 240, 55)}, {new Pixel(244, 131, 235),
            new Pixel(134, 185, 99),
            new Pixel(173, 105, 255)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Brighten10", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Brighten10", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Brighten10", r, c).getBlue());
      }
    }


    // darken 10
    input = "load res/testing3by3.ppm 3by3 darken 10 3by3 3by3Darken10";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    threeBythree = new IMEPixel[][]{{new Pixel(230, 230, 230),
            new Pixel(0, 0, 0),
            new Pixel(0, 0, 0)}, {new Pixel(0, 0, 0),
            new Pixel(0, 0, 0),
            new Pixel(140, 220, 35)}, {new Pixel(224, 111, 215),
            new Pixel(114, 165, 79),
            new Pixel(153, 85, 244)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Darken10", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Darken10", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Darken10", r, c).getBlue());
      }
    }
  }

  @Test
  public void testSharpen() {
    input = "load res/testing3by3.ppm 3by3 filter-sharpen 3by3 3by3Sharpen";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(156, 162, 163),
            new Pixel(33, 70, 2),
            new Pixel(0, 0, 0)}, {new Pixel(111, 94, 102),
            new Pixel(230, 218, 216),
            new Pixel(163, 253, 74)}, {new Pixel(196, 94, 180),
            new Pixel(231, 255, 190),
            new Pixel(172, 151, 229)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Sharpen", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Sharpen", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Sharpen", r, c).getBlue());
      }
    }
  }

  @Test
  public void testBlur() {
    input = "load res/testing3by3.ppm 3by3 filter-blur 3by3 3by3Blur";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(60, 60, 60),
            new Pixel(39, 45, 33),
            new Pixel(19, 29, 6)}, {new Pixel(67, 56, 64),
            new Pixel(74, 79, 62),
            new Pixel(66, 80, 49)}, {new Pixel(74, 52, 67),
            new Pixel(90, 85, 85),
            new Pixel(75, 74, 80)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Blur", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Blur", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Blur", r, c).getBlue());
      }
    }
  }

  @Test
  public void testTransformGreyscale() {
    input = "load res/testing3by3.ppm 3by3 transform-greyscale 3by3 3by3Greyscale";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    //luma greyscale
    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 2, 2)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(199, 199, 199)}, {new Pixel(152, 152, 152),
            new Pixel(157, 157, 157),
            new Pixel(120, 120, 120)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Greyscale", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Greyscale", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Greyscale", r, c).getBlue());
      }
    }
  }

  @Test
  public void testTransformSepia() {
    input = "load res/testing3by3.ppm 3by3 transform-sepia 3by3 3by3Sepia";
    readable = new StringReader(input);
    controller = new Controller(model, view, readable);
    controller.process();

    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(255, 255, 224),
            new Pixel(0, 0, 0),
            new Pixel(3, 3, 2)}, {new Pixel(1, 1, 0),
            new Pixel(2, 2, 1),
            new Pixel(244, 217, 169)}, {new Pixel(227, 202, 157),
            new Pixel(200, 178, 138),
            new Pixel(185, 164, 128)}};

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(),
                model.getPixelAt("3by3Sepia", r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(),
                model.getPixelAt("3by3Sepia", r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(),
                model.getPixelAt("3by3Sepia", r, c).getBlue());
      }
    }
  }

}
