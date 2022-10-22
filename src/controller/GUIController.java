package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//import java.util.function.Function;

import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.ChangeBrightness;
import controller.commands.Downscale;
import controller.commands.Filter;
import controller.commands.Flip;
//import controller.commands.IMECommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transform;
import controller.commands.Visualize;
import model.GUIModel;
import model.IMEImage;
import model.IMEPixel;
import model.Image;
import model.Pixel;
//import model.functions.VisualizeRed;

/**
 * Represents a GUI-specific implementation of the IMEController class to control the behavior of
 * the IME program while it is running. Keeps track of an IMEModel and its IMEView, telling them
 * what to do through the use of command objects. Outputs its operations to a GUI for user input
 */
public class GUIController implements IMEController {
  private GUIModel model;
  private boolean fileOpen;

  /**
   * Constructor for a GUICOntroller.
   * @param model the model
   */
  public GUIController(GUIModel model) {
    fileOpen = false;
    this.model = model;
    clearWorkingImage();
  }

  /**
   * Clears the working image.
   */
  private void clearWorkingImage() {
    IMEPixel[][] blankPixel = new Pixel[1][1];
    blankPixel[0][0] = new Pixel(0, 0, 0);
    IMEImage blankImage = new Image(blankPixel, 255);
    model.putImage("", blankImage);
    BufferedImage img =
            new BufferedImage(1,
                    1,
                    BufferedImage.TYPE_INT_ARGB);
    int rgb = ((0 & 0x0ff) << 24)
            | ((0 & 0x0ff) << 16)
            | ((0 & 0x0ff) << 8)
            | (0 & 0xff);
    img.setRGB(0, 0, rgb);
    try {
      ImageIO.write(img, "png", new File(GUIModel.filePath));
    }
    catch (IOException e) {
      throw new IllegalArgumentException("could not write given image to given file");
    }
  }

  /**
   * Handles command input.
   * @param command the command
   */
  public void handle(String command) {
    Scanner s = new Scanner(command);
    String initialInput = s.next();
    String fileName;
    System.out.println(initialInput);
    switch (initialInput) {
      case "load":
        fileName = s.next();
        new Load(fileName, model).doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        this.fileOpen = true;
        break;
      case "save":
        if (! fileOpen) {
          //render message that a file needs to be open and do nothing
          break;
        }
        fileName = s.next();
        new Save(fileName, model).doThing(model);
        break;
      case "horizontal-flip":
        new Flip("horizontal").doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "vertical-flip":
        new Flip("vertical").doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "blur":
        new Filter("blur").doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "sharpen":
        new Filter("sharpen").doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "change-brightness":
        int inc = s.nextInt();
        new ChangeBrightness(inc).doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "sepia":
        new Transform("sepia").doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "greyscale":
        String gNext = s.next();
        if (gNext.equals("transform")) {
          new Transform("greyscale").doThing(model);
        }
        else {
          new Visualize(gNext).doThing(model);
        }
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      case "downscale":
        int width = s.nextInt();
        int height = s.nextInt();
        new Downscale(width, height).doThing(model);
        new Save(GUIModel.filePath, model).doThing(model);
        break;
      default:
        //Should never reach this point in the switch statement
    }
  }

  @Override
  public void process() throws IllegalStateException {
//    boolean quit = false;
//    try {
//      view.renderMessage("Welcome to our IME program! Please see the README included in our
//      res\n" +
//              "directory if you are a first-time user!\n");
//    } catch (IOException e) {
//      throw new IllegalStateException("could not render message");
//    }
//    while (!quit) {
//      if (s.hasNext()) {
//        String in = s.next();
//        if (in.equals("quit")) {
//          quit = true;
//          try {
//            view.renderMessage("Quit the program\n");
//          } catch (IOException e) {
//            throw new IllegalStateException("Could not render message.");
//          }
//        } else {
//          Function<Scanner, IMECommand> cmd = this.commands.getOrDefault(in, null);
//          if (cmd == null) {
//            throw new IllegalArgumentException("Couldn't find inputted command.");
//          } else {
//            IMECommand c = cmd.apply(s);
//            try {
//              c.doThing(model);
//            } catch (IllegalArgumentException e) {
//              try {
//                view.renderMessage("Invalid inputs. Please check your entry and try again.\n");
//                continue;
//              } catch (IOException exc) {
//                throw new IllegalStateException();
//              }
//            }
//
//            try {
//              view.renderMessage(c.description());
//              view.renderMessage("Ready for next input!\n");
//            } catch (IOException e) {
//              throw new IllegalStateException("Could not render message.");
//            }
//          }
//        }
//      } else {
//        quit = true;
//      }
//    }
  }
}
