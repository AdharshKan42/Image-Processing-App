package controller;

/**
 * Represents a controller for an IME program, takes in input from the Main Class (as a Readable),
 * completes operations using the IMEModel and output results to the IMEView.
 */
public interface IMEController {
  /**
   * Loops until the program is quit, parsing inputs and calling commands accordingly.
   * @throws IllegalStateException if it cannot render a message to its view
   */
  void process() throws IllegalStateException;
}
