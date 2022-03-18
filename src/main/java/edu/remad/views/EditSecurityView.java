package edu.remad.views;

import edu.remad.controllers.EditSecurityController;
import edu.remad.foundation.control.JTextFieldAdvanced;
import edu.remad.models.security.EditSecurityModel;
import edu.remad.models.security.EditSecurityIPropertyId;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditSecurityView extends AbstractView implements ActionListener, Runnable {

  /**
   * the controller
   */
  private final EditSecurityController controller;

  /**
   * the data model
   */
  private final EditSecurityModel dataModel;

  /**
   * the thread to run this instance
   */
  private final Thread thread;

  /**
   * true / false for updating the view
   */
  private volatile boolean hasToUpdate = false;

  /**
   * True / false for stopping the run-method.
   */
  private volatile boolean hasStopped;

  private JTextFieldAdvanced closeValueTextfield;

  /**
   * Constructor
   *
   * @param controller edit security controller
   * @param dataModel  the data model
   */
  public EditSecurityView(EditSecurityController controller, EditSecurityModel dataModel) {
    super("Edit a security");

    this.controller = controller;
    this.dataModel = dataModel;
    this.dataModel.registerObserver(this);

    this.createSecurityPanel();
    this.createView();

    this.hasStopped = false;
    this.thread = new Thread(this);
    this.thread.start();
  }

  /**
   * Method to create security panel
   */
  private void createSecurityPanel() {
    JPanel securityPanel = new JPanel();

    GridLayout gridLayout = new GridLayout(5, 2);
    securityPanel.setLayout(gridLayout);

    JTextField nameTextfield = new JTextFieldAdvanced(1, EditSecurityIPropertyId.NAME);
    nameTextfield.setText(dataModel.getName());
    nameTextfield.setSize(new Dimension(200, 25));
    nameTextfield.setFont(nameTextfield.getFont().deriveFont(16));
    nameTextfield.addActionListener(this);
    JLabel nameLabel = new JLabel("Name:", SwingConstants.LEFT);
    nameLabel.setSize(new Dimension(45, 25));

    JTextField wknTextfield = new JTextFieldAdvanced(1, EditSecurityIPropertyId.WKN);
    wknTextfield.setText(dataModel.getWkn());
    wknTextfield.setSize(new Dimension(200, 25));
    wknTextfield.setFont(nameTextfield.getFont().deriveFont(16));
    wknTextfield.addActionListener(this);
    JLabel wknLabel = new JLabel("WKN:", SwingConstants.LEFT);
    wknLabel.setSize(new Dimension(45, 25));

    JTextField isinTextfield = new JTextFieldAdvanced(1, EditSecurityIPropertyId.ISIN);
    isinTextfield.setText(dataModel.getIsin());
    isinTextfield.setSize(new Dimension(200, 25));
    isinTextfield.setFont(nameTextfield.getFont().deriveFont(16));
    isinTextfield.addActionListener(this);
    JLabel isinLabel = new JLabel("ISIN:", SwingConstants.LEFT);
    isinLabel.setSize(new Dimension(45, 25));

    JTextField symbolTextfield = new JTextFieldAdvanced(1, EditSecurityIPropertyId.SYMBOL);
    symbolTextfield.setText(dataModel.getSymbol());
    symbolTextfield.setSize(new Dimension(200, 25));
    symbolTextfield.setFont(nameTextfield.getFont().deriveFont(16));
    symbolTextfield.addActionListener(this);
    JLabel symbolLabel = new JLabel("Symbol:", SwingConstants.LEFT);
    symbolLabel.setSize(new Dimension(45, 25));

    this.closeValueTextfield = new JTextFieldAdvanced(1, EditSecurityIPropertyId.CLOSE_VALUE);
    closeValueTextfield.setText(dataModel.getCloseValue().toString());
    closeValueTextfield.setSize(new Dimension(200, 25));
    closeValueTextfield.setEditable(false);
    closeValueTextfield.setFont(nameTextfield.getFont().deriveFont(16));
    JLabel closeValueLabel = new JLabel("Close value:", SwingConstants.LEFT);
    closeValueLabel.setSize(new Dimension(45, 25));

    securityPanel.add(nameLabel);
    securityPanel.add(nameTextfield);
    securityPanel.add(wknLabel);
    securityPanel.add(wknTextfield);
    securityPanel.add(isinLabel);
    securityPanel.add(isinTextfield);
    securityPanel.add(symbolLabel);
    securityPanel.add(symbolTextfield);
    securityPanel.add(closeValueLabel);
    securityPanel.add(closeValueTextfield);

    this.getViewPanel().add(securityPanel);
  }

  @Override
  public void createView() {
    super.createView();
  }

  /**
   * Method to update the observer
   */
  @Override
  public void update() {
    String closeValue = this.controller.getCloseValue().toString();
    if(!closeValue.equals(closeValueTextfield.getText())) {
      closeValueTextfield.setText(closeValue);
    }

    this.getFrame().repaint();
    this.hasToUpdate = false;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent != null) {
      if (actionEvent.getSource() instanceof JTextFieldAdvanced) {
        EditSecurityIPropertyId id = castIdentifier(actionEvent);

        switch (id) {
          case NAME: {
            this.controller.setName(actionEvent.getActionCommand());
            this.hasToUpdate = true;
            break;
          }
          case WKN: {
            this.controller.setWkn(actionEvent.getActionCommand());
            this.hasToUpdate = true;
            break;
          }
          case ISIN: {
            this.controller.setIsin(actionEvent.getActionCommand());
            this.hasToUpdate = true;
            break;
          }
          case SYMBOL:
          case CLOSE_VALUE:
          {
            this.controller.setSymbol(actionEvent.getActionCommand());
            this.hasToUpdate = true;
            break;
          }
          default: {
            throw new IllegalArgumentException(
                "The instance of EditSecurityPropertyId is not known.");
          }
        }
      }
    }
  }

  /**
   * Casts identifier.
   *
   * @param actionEvent the triggered action event
   * @return cast instance of {@link EditSecurityIPropertyId}
   */
  private EditSecurityIPropertyId castIdentifier(ActionEvent actionEvent) {
    if (!(((JTextFieldAdvanced) actionEvent.getSource()).getIdentifier() instanceof EditSecurityIPropertyId)) {
      throw new IllegalArgumentException("Illegal control");
    }

    JTextFieldAdvanced source = (JTextFieldAdvanced) actionEvent.getSource();

    return (EditSecurityIPropertyId) source.getIdentifier();
  }

  @Override
  public void run() {
    while (!this.hasStopped) {
      if (hasToUpdate) {
        this.update();
      }
    }
  }

  /**
   * Method to stop run method
   */
  public void stopRun() {
    this.hasStopped = true;
  }
}
