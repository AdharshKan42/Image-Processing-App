import org.junit.Before;
import org.junit.Test;

import model.IMEImage;
import model.IMEPixel;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Image class.
 */
public class ImageTest {

  String twoBytwo_notPPM;
  String twoBytwo_PPM;
  IMEPixel[][] twoBytwoPixels;
  IMEPixel[][] threeBythree;

  String threeBythree_PPM;

  @Before
  public void setup() {
    twoBytwo_notPPM = "240 240 240 1 1 1 1 1 1 2 2 2";
    twoBytwo_PPM = "P3\n2 2\n255\n240\n240\n240\n1\n1\n1\n1\n1\n1\n2\n2\n2\n";
    twoBytwoPixels = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(1, 1, 1)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2)}};

    threeBythree = new IMEPixel[][]{{new Pixel(240, 240, 240),
            new Pixel(0, 0, 0),
            new Pixel(2, 3, 4)}, {new Pixel(1, 1, 1),
            new Pixel(2, 2, 2),
            new Pixel(150, 230, 45)}, {new Pixel(234, 121, 225),
            new Pixel(124, 175, 89),
            new Pixel(163, 95, 254)}};

    threeBythree_PPM = "P3\n" + "3 3\n" + "255\n" + "240\n" + "240\n" + "240\n"
            + "0\n" + "0\n" + "0\n" + "2\n" + "3\n" + "4\n" + "1\n" + "1\n" + "1\n" + "2\n"
            + "2\n" + "2\n" + "150\n" + "230\n" + "45\n" + "234\n" + "121\n" + "225\n" + "124\n"
            + "175\n" + "89\n" + "163\n" + "95\n" + "254\n";
  }

  @Test
  public void testConstructor() {
    System.out.println(threeBythree_PPM);
    String[] parseTwoByTwo = twoBytwo_notPPM.split(" ");

    IMEImage image = new Image(twoBytwoPixels, 255);

    assertEquals(240, image.getPixelAt(0, 0).getRed());
    assertEquals(240, image.getPixelAt(0, 0).getBlue());
    assertEquals(240, image.getPixelAt(0, 0).getGreen());
    assertEquals(1, image.getPixelAt(0, 1).getRed());
    assertEquals(1, image.getPixelAt(0, 1).getBlue());
    assertEquals(1, image.getPixelAt(0, 1).getGreen());
    assertEquals(1, image.getPixelAt(1, 0).getRed());
    assertEquals(1, image.getPixelAt(1, 0).getBlue());
    assertEquals(1, image.getPixelAt(1, 0).getGreen());
    assertEquals(2, image.getPixelAt(1, 1).getRed());
    assertEquals(2, image.getPixelAt(1, 1).getBlue());
    assertEquals(2, image.getPixelAt(1, 1).getGreen());

    assertEquals(2, image.getHeight());
    assertEquals(2, image.getWidth());

    IMEImage image2 = new Image(threeBythree, 255);

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(), image2.getPixelAt(r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(), image2.getPixelAt(r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(), image2.getPixelAt(r, c).getBlue());

      }
    }

    assertEquals(3, image2.getHeight());
    assertEquals(3, image2.getWidth());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_maxValue_less_than_0() {
    IMEImage image = new Image(twoBytwoPixels, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_Null_Pixel_Array() {
    IMEImage image = new Image(null, 255);
  }

  @Test
  public void toPPM() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    assertEquals(twoBytwo_PPM, image.toPPM());

    IMEImage image3by3 = new Image(threeBythree, 255);
    assertEquals(threeBythree_PPM, image3by3.toPPM());
  }

  @Test
  public void map2D() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    //added one to each pixel's RGB values
    IMEImage newImage = image.map2D(new Add1());
    String oldImage_PPM_plus_one = "P3\n2 2\n255\n241\n241\n241\n2\n2\n2\n2\n2\n2\n3\n3\n3\n";
    //comparing newImage PPM to old image PPM with one added to each RGB value
    assertEquals(oldImage_PPM_plus_one, newImage.toPPM());
  }

  @Test
  public void map() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    //subtracts one from each pixel's RGB values
    IMEImage newImage = image.map(new Subtract1());
    String oldImage_PPM_minus_one = "P3\n2 2\n255\n239\n239\n239\n0\n0\n0\n0\n0\n0\n1\n1\n1\n";
    //comparing newImage PPM to old image PPM with one subtracted from each RGB value
    assertEquals(oldImage_PPM_minus_one, newImage.toPPM());

  }

  @Test
  public void getPixelAt() {
    IMEImage image = new Image(twoBytwoPixels, 255);

    for (int r = 0; r < twoBytwoPixels.length; r++) {
      for (int c = 0; c < twoBytwoPixels[0].length; c++) {
        assertEquals(twoBytwoPixels[r][c].getRed(), image.getPixelAt(r, c).getRed());
        assertEquals(twoBytwoPixels[r][c].getGreen(), image.getPixelAt(r, c).getGreen());
        assertEquals(twoBytwoPixels[r][c].getBlue(), image.getPixelAt(r, c).getBlue());

      }
    }

    IMEImage image2 = new Image(threeBythree, 255);

    for (int r = 0; r < threeBythree.length; r++) {
      for (int c = 0; c < threeBythree[0].length; c++) {
        assertEquals(threeBythree[r][c].getRed(), image2.getPixelAt(r, c).getRed());
        assertEquals(threeBythree[r][c].getGreen(), image2.getPixelAt(r, c).getGreen());
        assertEquals(threeBythree[r][c].getBlue(), image2.getPixelAt(r, c).getBlue());

      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAt_negative_InputPosition() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    image.getPixelAt(-1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAt_greaterthanMaxValue_InputPosition() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    image.getPixelAt(256, 256);
  }

  @Test
  public void getWidth() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    assertEquals(2, image.getWidth());

    IMEPixel[][] fiveRowsByFourColumns = new IMEPixel[5][4];
    IMEImage image2 = new Image(fiveRowsByFourColumns, 233);
    assertEquals(4, image2.getWidth());
  }

  @Test
  public void getHeight() {
    IMEImage image = new Image(twoBytwoPixels, 255);
    assertEquals(2, image.getHeight());

    IMEPixel[][] fiveRowsByFourColumns = new IMEPixel[5][4];
    IMEImage image2 = new Image(fiveRowsByFourColumns, 233);
    assertEquals(5, image2.getHeight());
  }
}