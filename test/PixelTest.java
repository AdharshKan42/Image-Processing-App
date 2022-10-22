import org.junit.Before;
import org.junit.Test;

import model.IMEPixel;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Pixel Class.
 */
public class PixelTest {
  IMEPixel pix0;
  IMEPixel pix1;
  IMEPixel pix255;

  @Before
  public void setup() {
    pix0 = new Pixel(0, 0, 0);
    pix1 = new Pixel(10, 150, 250);
    pix255 = new Pixel(255, 255, 255);

  }

  @Test
  public void testConstructor() {
    IMEPixel pix = new Pixel(240, 230, 200);
    assertEquals(240, pix.getRed());
    assertEquals(230, pix.getGreen());
    assertEquals(200, pix.getBlue());
    assertEquals(255, pix.getAlpha());

    IMEPixel pix2 = new Pixel(240, 230, 200, 234);
    assertEquals(240, pix2.getRed());
    assertEquals(230, pix2.getGreen());
    assertEquals(200, pix2.getBlue());
    assertEquals(234, pix2.getAlpha());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLessThan0_red() {
    IMEPixel pix = new Pixel(-1, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLessThan0_green() {
    IMEPixel pix = new Pixel(1, -2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLessThan0_blue() {
    IMEPixel pix = new Pixel(1, 2, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLessThan0_alpha() {
    IMEPixel pix = new Pixel(1, 2, 3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreaterThan255_red() {
    IMEPixel pix = new Pixel(2440, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreaterThan255_green() {
    IMEPixel pix = new Pixel(1, 256, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreaterThan255_blue() {
    IMEPixel pix = new Pixel(1, 2, 257);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreaterThan255_alpha() {
    IMEPixel pix = new Pixel(1, 2, 254, 278);
  }

  @Test
  public void testGetRed() {
    IMEPixel pix2 = new Pixel(0, 145, 233);
    IMEPixel pix3 = new Pixel(255, 200, 200);
    assertEquals(0, pix0.getRed());
    assertEquals(10, pix1.getRed());
    assertEquals(0, pix2.getRed());
    assertEquals(255, pix3.getRed());
    assertEquals(255, pix255.getRed());
  }

  @Test
  public void getGreen() {
    assertEquals(0, pix0.getGreen());
    assertEquals(150, pix1.getGreen());
    assertEquals(255, pix255.getGreen());
  }

  @Test
  public void getBlue() {
    assertEquals(0, pix0.getBlue());
    assertEquals(250, pix1.getBlue());
    assertEquals(255, pix255.getBlue());
  }

  @Test
  public void testGetAlpha() {
    IMEPixel pix2 = new Pixel(0, 145, 233, 23);
    IMEPixel pix3 = new Pixel(255, 200, 200, 200);
    assertEquals(255, pix0.getAlpha());
    assertEquals(255, pix1.getAlpha());
    assertEquals(23, pix2.getAlpha());
    assertEquals(200, pix3.getAlpha());
    assertEquals(255, pix255.getAlpha());
  }

}