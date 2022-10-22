package view;

import java.io.IOException;

/**
 * View for Image Processing Application (currently just renders completion messages from commands
 * done by the IMEController).
 */
public interface IMEView {
  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
