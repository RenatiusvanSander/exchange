package edu.remad.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * the general view with panel, menubar, menu for file and actions
 */
public abstract class AbstractView implements Observer {

  private final String title;
  private final JFrame viewFrame;
  private final JPanel viewPanel;
  private final JMenuBar menuBar;
  private final JMenu fileMenu;
  private final JMenuItem exit;

  /**
   * Constructs the general view with panel, menubar, menu for file and actions
   *
   * @param title title of the view
   */
  public AbstractView(String title) {
    this.title = title;
    this.viewFrame = new JFrame(this.title);
    this.viewPanel = new JPanel(new GridLayout(1, 2));
    this.menuBar = new JMenuBar();
    this.fileMenu = new JMenu("File");
    this.exit = new JMenuItem("Quit");
  }

  /**
   * First comes the JFrame, which contains JMenuBar and the JContentPane for JLabels and other
   * controls.
   */
  public void createView() {
    initViewFrame();
    initExit();

    fileMenu.add(exit);
    menuBar.add(fileMenu);
    viewFrame.setJMenuBar(menuBar);
    viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);

    viewFrame.pack();
    viewFrame.setVisible(true);
  }

  /**
   * Method to initialize exit
   */
  private void initExit() {
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
  }

  /**
   * Method to initialize viewFrame
   */
  private void initViewFrame() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    viewFrame.setSize(1920, 1080);
  }

  /**
   * Gets title
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the main view panel
   *
   * @return the view panel
   */
  public JPanel getViewPanel() {
    return viewPanel;
  }

  /**
   * Gets the menu bar
   *
   * @return the menu bar
   */
  public JMenuBar getMenuBar() {
    return menuBar;
  }

  /**
   * Gets the file menu
   *
   * @return the main file menu
   */
  public JMenu getFileMenu() {
    return fileMenu;
  }

  /**
   * Gets Frame
   *
   * @return the view frame
   */
  public JFrame getFrame() {
    return viewFrame;
  }
}
