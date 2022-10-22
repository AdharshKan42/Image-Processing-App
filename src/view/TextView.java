package view;

import java.io.IOException;
import java.io.InputStreamReader;

import model.IMEViewModel;

/**
 * Text-based view for Image Processing Application; (currently just renders completion messages
 * from commands done by the IMEController).
 */
public class TextView implements IMEView {
  //private IMEViewModel viewModel;
  private Appendable app;
  private Readable r = new InputStreamReader(System.in);

  /**
   * Main Constructor for TextView that inputs in a ViewModel (read-only version of IMEModel) and
   * sets the output Appendable location while instantiating the view.
   *
   * @param viewModel (read-only version of IMEModel)
   * @param app       Appendable output object to display the view.
   */
  public TextView(IMEViewModel viewModel, Appendable app) {
    if (viewModel == null || app == null) {
      throw new IllegalArgumentException("Input(s) are invalid");
    }
    //this.viewModel = viewModel;
    this.app = app;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.app.append(message);
  }
}
