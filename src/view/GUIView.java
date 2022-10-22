package view;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIController;
import model.GUIModel;
import model.IMEPixel;


/**
 * GUI-based view for Image Processing Application, displays the image and its operations to the
 * user to use and outputs any new changes to the user.
 */
public class GUIView extends JFrame implements ActionListener, IMEView {
  private GUIController controller;
  private GUIModel model;
  private JPanel superMainPanel;
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JLabel changeBrightnessComponent;
  public JLabel recentCommand;
  private JLabel downscaleWidth;
  private JLabel downscaleHeight;
  private JLabel greyscaleType;
  private JPanel histogram;

  /**
   * Main Constructor for GUIView, displays all images and operations to the user and takes in
   * inputs from the user.
   *
   * @param controller used by the GUIView
   * @param model      View-only model that uses getters to get minimal image data
   */
  public GUIView(GUIController controller, GUIModel model) {

    super();

    JScrollPane mainScrollPane;
    JLabel fileOpenDisplay;
    JLabel fileSaveDisplay;
    JButton flipHorizontalButton;
    JButton flipVerticalButton;
    JButton blurButton;
    JButton sharpenButton;
    JButton sepiaButton;

    setTitle("GRIME");
    setSize(800, 600);

    this.controller = controller;
    this.model = model;

    superMainPanel = new JPanel();
    superMainPanel.setLayout(new FlowLayout());
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(superMainPanel);
    add(mainScrollPane);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new FlowLayout());
    superMainPanel.add(mainPanel);


    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.setMaximumSize(new Dimension(model.getWidth(""),
            model.getHeight("")));
    mainPanel.add(imagePanel);

    String[] images = {GUIModel.filePath};
    JLabel[] imageLabel = new JLabel[images.length];
    JScrollPane[] imageScrollPane = new JScrollPane[images.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(new ImageIcon(images[i]));
      imageScrollPane[i].setPreferredSize(new Dimension(100, 200));
      imagePanel.add(imageScrollPane[i]);
    }

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);
    dialogBoxesPanel.setMaximumSize(new Dimension(100, 100));

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    //password fields

    JPanel pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder(""));
    mainPanel.add(pPanel);
    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));

    flipHorizontalButton = new JButton("flip horizontally");
    flipHorizontalButton.addActionListener(this);
    flipHorizontalButton.setActionCommand("horizontal-flip");
    pPanel.add(flipHorizontalButton);

    flipVerticalButton = new JButton("flip vertically");
    flipVerticalButton.addActionListener(this);
    flipVerticalButton.setActionCommand("vertical-flip");
    pPanel.add(flipVerticalButton);

    blurButton = new JButton("blur");
    blurButton.addActionListener(this);
    blurButton.setActionCommand("blur");
    pPanel.add(blurButton);

    sharpenButton = new JButton("sharpen");
    sharpenButton.addActionListener(this);
    sharpenButton.setActionCommand("sharpen");
    pPanel.add(sharpenButton);

    sepiaButton = new JButton("sepia");
    sepiaButton.addActionListener(this);
    sepiaButton.setActionCommand("sepia");
    pPanel.add(sepiaButton);

    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    JButton inputButton = new JButton("change brightness");
    inputButton.setActionCommand("Change brightness");
    inputButton.addActionListener(this);
    pPanel.add(inputButton);

    changeBrightnessComponent = new JLabel("Default");
    //inputDialogPanel.add(changeBrightnessComponent);

    //JOptionsPane options dialog
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(optionsDialogPanel);

    JButton optionButton = new JButton("greyscale");
    optionButton.setActionCommand("greyscale");
    optionButton.addActionListener(this);
    pPanel.add(optionButton);

    greyscaleType = new JLabel("Default");
    //optionsDialogPanel.add(greyscaleType);

    recentCommand = new JLabel("default");

    JButton downscaleButton = new JButton("downscale");
    downscaleButton.setActionCommand("downscale");
    downscaleButton.addActionListener(this);
    pPanel.add(downscaleButton);

    downscaleWidth = new JLabel("Default");
    downscaleHeight = new JLabel("Default");

    histogram = new JPanel();
    histogram.setLayout(new GridBagLayout());

    histogram.setBackground(Color.black);
    superMainPanel.add(histogram);

  }

  private void makeHistogram() {
    int[] redFreq = new int[256];
    int[] greenFreq = new int[256];
    int[] blueFreq = new int[256];
    int[] intensityFreq = new int[256];
    int maxFreq = 0;


    for (int i = 0; i < model.getImage("").getWidth(); i++) {
      for (int j = 0; j < model.getImage("").getHeight(); j++) {
        if (redFreq[model.getPixelAt("", i, j).getRed()] <= 500) {
          redFreq[model.getPixelAt("", i, j).getRed()]++;
        }
        if (redFreq[model.getPixelAt("", i, j).getRed()] > maxFreq) {
          maxFreq = redFreq[model.getPixelAt("", i, j).getRed()];
        }
      }
    }

    for (int i = 0; i < model.getImage("").getWidth(); i++) {
      for (int j = 0; j < model.getImage("").getHeight(); j++) {
        if (greenFreq[model.getPixelAt("", i, j).getGreen()] <= 500) {
          greenFreq[model.getPixelAt("", i, j).getGreen()]++;
        }
        if (greenFreq[model.getPixelAt("", i, j).getGreen()] > maxFreq) {
          maxFreq = greenFreq[model.getPixelAt("", i, j).getGreen()];
        }
      }
    }

    for (int i = 0; i < model.getImage("").getWidth(); i++) {
      for (int j = 0; j < model.getImage("").getHeight(); j++) {
        if (blueFreq[model.getPixelAt("", i, j).getBlue()] <= 500) {
          blueFreq[model.getPixelAt("", i, j).getBlue()]++;
        }
        if (blueFreq[model.getPixelAt("", i, j).getBlue()] > maxFreq) {
          maxFreq = blueFreq[model.getPixelAt("", i, j).getBlue()];
        }
      }
    }

    for (int i = 0; i < model.getImage("").getWidth(); i++) {
      for (int j = 0; j < model.getImage("").getHeight(); j++) {
        IMEPixel currPixel = model.getPixelAt("", i, j);
        int intensity = (int)
                ((0.0 + currPixel.getRed() + currPixel.getGreen() + currPixel.getBlue()) / 3);
        if (intensityFreq[intensity] <= 500) {
          intensityFreq[intensity]++;
        }
        if (intensityFreq[intensity] > maxFreq) {
          maxFreq = intensityFreq[intensity];
        }
      }
    }
    histogram = (new LouGram(redFreq, greenFreq, blueFreq, intensityFreq, maxFreq));
    superMainPanel.remove(1);
    superMainPanel.add(histogram);

    repaint();
  }

  private void refreshImage() {
    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setMaximumSize(new Dimension(model.getWidth(""),
            model.getHeight("")));
    mainPanel.add(imagePanel, 0);

    JLabel imageLabel = new JLabel();
    BufferedImage pic = null;
    try {
      pic = ImageIO.read(new File(GUIModel.filePath));
    } catch (IOException e) {
      //
    }
    imageLabel.setIcon(new ImageIcon(pic));
    imagePanel.add(imageLabel);
    mainPanel.remove(1);
    makeHistogram();
    mainPanel.repaint();
    mainPanel.revalidate();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(e.toString());
    String command = e.getActionCommand();
    switch (command) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "supported images", "jpg", "gif", "png", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(GUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          controller.handle("load " + f.getAbsolutePath());
          this.refreshImage();
        }
        recentCommand.setText("open file");
        break;
      }

      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(GUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          controller.handle("save " + f.getAbsolutePath());
        }
        recentCommand.setText("save file");
        break;
      }

      case "Change brightness": {
        changeBrightnessComponent
                .setText(JOptionPane.showInputDialog("input int value to change brightness by"));
        controller.handle("change-brightness " + changeBrightnessComponent.getText());
        this.refreshImage();
        recentCommand.setText("change brightness");
        break;
      }

      case "greyscale": {
        String[] options = {"red", "green", "blue", "luma", "intensity", "value", "transform"};
        int retvalue = JOptionPane.showOptionDialog(GUIView.this,
                "Select type", "Options",
                JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[4]);
        greyscaleType.setText(options[retvalue]);
        controller.handle("greyscale " + greyscaleType.getText());
        this.refreshImage();
      }
      recentCommand.setText("greyscale");
      break;

      case "downscale": {
        downscaleWidth.setText(JOptionPane.showInputDialog("enter downscaled width"));
        downscaleHeight.setText(JOptionPane.showInputDialog("enter downscaled height"));
        controller.handle("downscale "
                + downscaleHeight.getText() + " " + downscaleWidth.getText());
        this.refreshImage();
      }
      recentCommand.setText("downscale");
      break;

      default: {
        controller.handle(command);
        recentCommand.setText(command);
        this.refreshImage();
        break;
      }
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {//

  }
}
