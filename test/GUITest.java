import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import controller.GUIController;
import model.GUIModel;
import view.GUIView;

import static java.awt.event.ActionEvent.ACTION_PERFORMED;
import static org.junit.Assert.assertEquals;

/**
 * Tests some GUI functionality.
 */
public class GUITest {
  private GUIView view;

  @Before
  public void init() {
    GUIModel model = new GUIModel();
    GUIController controller = new GUIController(model);
    view = new GUIView(controller, model);
  }

  @Test
  public void testInput() {
    ActionEvent e = new ActionEvent(new Object(), ACTION_PERFORMED,  "horizontal-flip");
    view.actionPerformed(e);
    assertEquals("horizontal-flip", view.recentCommand.getText());
  }

  // idk what else to test lol open the thing it works i promise

}
