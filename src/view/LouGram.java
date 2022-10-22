package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Represents a custom class that generates a histogram of component intensity frequency
 * from the given arrays of component intensity frequencies.
 */
public class LouGram extends JPanel {
  int[] reds;
  int[] greens;
  int[] blues;
  int[] intensities;
  int maxFreq;

  /**
   * Constructor for LouGram class.
   * @param reds        array of red components of an image
   * @param greens      array of green components of an image
   * @param blues       array of blue components of an image
   * @param intensities array of intensity components of an image
   * @param maxFreq     array of max Frequency components of an image
   */
  public LouGram(int[] reds, int[] greens, int[] blues, int[] intensities, int maxFreq) {
    this.reds = reds;
    this.greens = greens;
    this.blues = blues;
    this.intensities = intensities;
    this.maxFreq = maxFreq;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < 256; i++) {
      g.setColor(Color.red);
      g.fillRect(i + 70, maxFreq - reds[i], 1, reds[i]);
      g.setColor(Color.green);
      g.fillRect(i + 70, maxFreq - greens[i], 1, greens[i]);
      g.setColor(Color.blue);
      g.fillRect(i + 70, maxFreq - blues[i], 1, blues[i]);
      g.setColor(Color.gray);
      g.fillRect(i + 70, maxFreq - intensities[i], 1, intensities[i]);
    }
    g.drawString("frequency", 0, maxFreq / 2);
    g.drawString("component intensity", 356 / 2 - 10, maxFreq + 10);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256 + 70, maxFreq + 20);
  }
}
