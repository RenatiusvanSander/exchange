package edu.remad.foundation.control;

import edu.remad.foundation.IPropertyId;
import javax.swing.JTextField;

public class JTextFieldAdvanced extends JTextField {

  /**
   * the identifier
   */
  private final IPropertyId identifier;

  /**
   * Constructor
   *
   * @param identifier string encoded identifier
   */
  public JTextFieldAdvanced(IPropertyId identifier) {
    super();

    this.identifier = identifier;
  }

  /**
   * Constructor
   *
   * @param column     the numbered column
   * @param identifier the identifier to initialize
   */
  public JTextFieldAdvanced(int column, IPropertyId identifier) {
    super(column);

    this.identifier = identifier;
  }

  /**
   * Gets identifier
   *
   * @return the identifier
   */
  public IPropertyId getIdentifier() {
    return identifier;
  }
}
