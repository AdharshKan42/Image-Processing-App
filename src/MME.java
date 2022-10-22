//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

//import controller.Controller;
import controller.Controller;
import controller.GUIController;
//import controller.IMEController;
import controller.IMEController;
import model.GUIModel;
//import model.IMEModel;
//import model.IMEViewModel;
//import model.ImageModel;
//import model.ViewModel;
import model.IMEModel;
import model.IMEViewModel;
import model.ImageModel;
import model.ViewModel;
import view.GUIView;
import view.IMEView;
import view.TextView;
//import view.IMEView;
//import view.TextView;

/**
 * Final Class from where the entire Image Processing Application is started (can input commands
 * through the command line after running the main method).
 */
public final class MME {
  /**
   * Main method for Image Processing Application: instantiates one Image Processing Application
   * into which various images can be loaded in, processed (performed operations on), and then
   * saved to their respective destinations).
   *
   * @param args can parse txt file input if CLI received is -file [filename]
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      IMEModel model = new ImageModel();
      IMEViewModel viewModel = new ViewModel(model);
      IMEView view = new TextView(viewModel, System.out);
      IMEController controller;

      if (args[0].equals("-file")) {
        BufferedReader reader;
        try {
          reader = new BufferedReader(new FileReader(args[1]));
        } catch (IOException e) {
          throw new IllegalArgumentException("file not found");
        }
        controller = new Controller(model, view, reader);
        controller.process();
      }
      else if (args[0].equals("-text")) {
        controller = new Controller(model, view, new InputStreamReader(System.in));
        controller.process();
      }
      else {
        throw new IllegalArgumentException("illegal input provided");
      }
    }

    else {
      GUIModel model = new GUIModel();
      GUIController controller = new GUIController(model);

      GUIView.setDefaultLookAndFeelDecorated(false);
      GUIView frame = new GUIView(controller, model);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      } catch (Exception e) {
        throw new IllegalStateException("illegal state lol");
      }
    }
  }
}
