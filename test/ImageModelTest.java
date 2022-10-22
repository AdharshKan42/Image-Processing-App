import org.junit.Before;
import org.junit.Test;

import model.IMEImage;
import model.IMEModel;
import model.IMEPixel;
import model.Image;
import model.ImageModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Tests for ImageModel (Image Processing Model operations + viewers).
 */
public class ImageModelTest {
  private IMEModel model;
  private IMEImage image;

  @Before
  public void setUp() {
    model = new ImageModel();
    IMEPixel[][] threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 3, 4)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(150, 230, 45)}, {new Pixel(234, 121, 225),
            new Pixel(124, 175, 89),
            new Pixel(163, 95, 254)}};

    image = new Image(threeBythree, 255);
  }

  @Test
  public void testConstructor() {
    IMEModel model1 = new ImageModel();
    model1.putImage("3by3", image);
    assertEquals(3, model1.getHeight("3by3"));
    assertEquals(3, model1.getWidth("3by3"));
  }

  @Test
  public void putImage() {
    model.putImage("3by3", image);
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
  public void visualize() {
    model.putImage("3by3", image);
    assertEquals(3, model.getHeight("3by3"));
    assertEquals(3, model.getWidth("3by3"));

    //Red Component
    model.visualize("red", "3by3", "3by3Red");

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

    //Green Component
    model.visualize("green", "3by3", "3by3Green");

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

    //Blue Component
    model.visualize("blue", "3by3", "3by3Blue");

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

    //Value Component
    model.visualize("value", "3by3", "3by3Value");

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

    //Intensity Component
    model.visualize("intensity", "3by3", "3by3Intensity");

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

    //Luma Component
    model.visualize("luma", "3by3", "3by3Luma");

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

  @Test(expected = IllegalArgumentException.class)
  public void testVisualize_invalid_image_input() {
    model.putImage("3by3", image);
    model.visualize("luma", "asdfadsf", "asdfasdf");
  }

  @Test
  public void flip() {
    model.putImage("3by3", image);

    //vertical flip
    model.flip("vertical", "3by3", "3by3VerticalFlip");

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

    //horizontal flip
    model.flip("horizontal", "3by3", "3by3HorizontalFlip");

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

  @Test(expected = IllegalArgumentException.class)
  public void testFlip_invalid_image_input() {
    model.putImage("3by3", image);
    model.flip("vertical", "asdfadsf", "asdfasdf");
  }

  @Test
  public void changeBrightness() {
    model.putImage("3by3", image);
    model.changeBrightness(10, "3by3", "3by3Brighten10");

    //brighten 10
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

    //darken 10

    model.changeBrightness(-10, "3by3", "3by3Darken10");

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

  @Test(expected = IllegalArgumentException.class)
  public void testChangeBrightness_invalid_image_input() {
    model.putImage("3by3", image);
    model.changeBrightness(10, "asdfadsf", "asdfasdf");
  }

  @Test
  public void transform_Sepia() {

    model.putImage("3by3", image);
    model.transform("sepia", "3by3", "3by3Sepia");

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

  @Test
  public void transform_greyscale() {
    model.putImage("3by3", image);
    model.transform("greyscale", "3by3", "3by3Greyscale");

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

  @Test(expected = IllegalArgumentException.class)
  public void testTransform_Sepia_invalid_image_input() {
    model.putImage("3by3", image);
    model.transform("sepia", "asdfadsf", "asdfasdf");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransform_Greyscale_invalid_image_input() {
    model.putImage("3by3", image);
    model.transform("greyscale", "asdfadsf", "asdfasdf");
  }


  @Test
  public void filter_Blur() {
    model.putImage("3by3", image);
    model.filter("blur", "3by3", "3by3Blur");

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

  @Test(expected = IllegalArgumentException.class)
  public void testFilter_Blur_invalid_image_input() {
    model.putImage("3by3", image);
    model.filter("blur", "asdfadsf", "asdfasdf");
  }

  @Test
  public void filter_Sharpen() {
    model.putImage("3by3", image);
    model.filter("sharpen", "3by3", "3by3Sharpen");

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

  @Test(expected = IllegalArgumentException.class)
  public void testFilter_Sharpen_invalid_image_input() {
    model.putImage("3by3", image);
    model.filter("sharpen", "asdfadsf", "asdfasdf");
  }

  @Test
  public void getPixelAt() {
    model.putImage("3by3", image);
    model.changeBrightness(10, "3by3", "3by3Brighten10");

    //brighten 10
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
  }


  @Test
  public void getImage() {
    model.putImage("3by3", image);
    assertEquals(image, model.getImage("3by3"));
  }

  @Test
  public void getWidth() {
    IMEModel model1 = new ImageModel();
    model1.putImage("3by3", image);
    assertEquals(3, model1.getWidth("3by3"));
  }

  @Test
  public void getHeight() {
    IMEModel model1 = new ImageModel();
    model1.putImage("3by3", image);
    assertEquals(3, model1.getHeight("3by3"));
  }
}