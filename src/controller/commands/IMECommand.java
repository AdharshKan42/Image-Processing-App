package controller.commands;

import model.IMEModel;

/**
 * An interface to represent a Command object. Does something to the given model and provides
 * a description of what it's doing.
 */
public interface IMECommand {

  /**
   * Is called from the Controller and does operations using methods from the Model.
   * @param model An IMEModel used to call various operations on
   * @throws IllegalArgumentException if an invalid argument was input into the Scanner used to
   *                                  construct the object
   */
  void doThing(IMEModel model) throws IllegalArgumentException;

  /**
   * Returns a String description of what action this IMECommand object does to the model.
   * @return the description
   */
  String description();
}
