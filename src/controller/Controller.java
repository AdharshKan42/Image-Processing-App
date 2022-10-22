package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ChangeBrightness;
import controller.commands.Downscale;
import controller.commands.Filter;
import controller.commands.IMECommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transform;
import controller.commands.Visualize;
import controller.commands.Flip;
import model.IMEModel;
import view.IMEView;

/**
 * Represents an implementation of the IMEController class to control the behavior of the
 * IME program while it is running. Keeps track of an IMEModel and its IMEView, telling them
 * what to do through the use of command objects
 */
public class Controller implements IMEController {
  private final Map<String, Function<Scanner, IMECommand>> commands;
  private final Scanner s;
  private final IMEModel model;
  private final IMEView view;

  /**
   * Constructs a new Controller object with the given IMEModel, IMEView, and Readable objects,
   * also instantiates the Controller's commands.
   *
   * @param model the model
   * @param view  the view
   * @param r     the Readable object from which the Controller parses input
   */
  public Controller(IMEModel model, IMEView view, Readable r) {
    if (model == null || view == null || r == null) {
      throw new IllegalArgumentException("given a null input");
    }
    this.commands = new HashMap<>();
    this.s = new Scanner(r);
    this.model = model;
    this.view = view;
    this.initCommands();
  }

  private void initCommands() {
    // making design decision to pass scanner in case a future command makes an egregious amount of
    // calls to it so that one might avoid having 80 parameters in a hashmap put method :)
    // -- also because different commands need different things/amounts of things from the scanner
    commands.put("load", s -> new Load(s, model));
    commands.put("red-component", s -> new Visualize(s, "red"));
    commands.put("green-component", s -> new Visualize(s, "green"));
    commands.put("blue-component", s -> new Visualize(s, "blue"));
    commands.put("value-component", s -> new Visualize(s, "value"));
    commands.put("intensity-component", s -> new Visualize(s, "intensity"));
    commands.put("luma-component", s -> new Visualize(s, "luma"));
    commands.put("horizontal-flip", s -> new Flip(s, "horizontal"));
    commands.put("vertical-flip", s -> new Flip(s, "vertical"));
    commands.put("brighten", s -> new ChangeBrightness(s, true));
    commands.put("darken", s -> new ChangeBrightness(s, false));
    commands.put("save", s -> new Save(s, model));
    commands.put("transform-sepia", s -> new Transform(s, "sepia"));
    commands.put("transform-greyscale", s -> new Transform(s, "greyscale"));
    commands.put("filter-blur", s -> new Filter(s, "blur"));
    commands.put("filter-sharpen", s -> new Filter(s, "sharpen"));
    commands.put("downscale", s -> new Downscale(s));
  }

  @Override
  public void process() throws IllegalStateException {
    boolean quit = false;
    try {
      view.renderMessage("Welcome to our IME program! Please see the README included in our res\n" +
              "directory if you are a first-time user! Ready for input when you are :)\n");
    } catch (IOException e) {
      throw new IllegalStateException("could not render message");
    }
    while (!quit) {
      if (s.hasNext()) {
        String in = s.next();
        if (in.equals("quit")) {
          quit = true;
          try {
            view.renderMessage("Quit the program\n");
          } catch (IOException e) {
            throw new IllegalStateException("Could not render message.");
          }
        } else {
          Function<Scanner, IMECommand> cmd = this.commands.getOrDefault(in, null);
          if (cmd == null) {
            throw new IllegalArgumentException("Couldn't find inputted command.");
          } else {
            IMECommand c = cmd.apply(s);
            try {
              c.doThing(model);
            } catch (IllegalArgumentException e) {
              try {
                view.renderMessage("Invalid inputs. Please check your entry and try again.\n");
                continue;
              } catch (IOException exc) {
                throw new IllegalStateException();
              }
            }

            try {
              view.renderMessage(c.description());
              view.renderMessage("Ready for next input!\n");
            } catch (IOException e) {
              throw new IllegalStateException("Could not render message.");
            }
          }
        }
      } else {
        quit = true;
      }
    }
  }
}
