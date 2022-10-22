import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.IMEViewModel;
import model.ImageModel;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests for TextView class.
 */
public class TextViewTest {

  @Before
  public void setUp() throws Exception {
    IMEViewModel viewModel = new ImageModel();
    Appendable app = new StringBuilder();
    TextView view = new TextView(viewModel, app);
  }

  @Test
  public void testConstructor() {
    IMEViewModel viewModel = new ImageModel();
    Appendable app = new StringBuilder();
    TextView view = new TextView(viewModel, app);
    assertEquals("", app.toString());
  }

  @Test
  public void renderMessage() {
    IMEViewModel viewModel = new ImageModel();
    Appendable app = new StringBuilder();
    TextView view = new TextView(viewModel, app);
    try {
      view.renderMessage("Hello World");
    } catch (IOException e) {
      fail();
    }

    assertEquals("Hello World", app.toString());

    app = new StringBuilder();
    view = new TextView(viewModel, app);

    try {
      view.renderMessage("Goodbye moon!");
    } catch (IOException e) {
      fail();
    }

    assertEquals("Goodbye moon!", app.toString());

  }
}