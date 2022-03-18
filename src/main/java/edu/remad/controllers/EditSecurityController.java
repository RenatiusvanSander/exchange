package edu.remad.controllers;

import edu.remad.models.security.EditSecurityModel;
import edu.remad.views.EditSecurityView;

/**
 * Controls edit security functionalities like load data
 */
public class EditSecurityController implements IController {

  /**
   * the model to control
   */
  private final EditSecurityModel model;

  /**
   * Constructor
   *
   * @param model edit security model
   */
  public EditSecurityController(EditSecurityModel model) {
    this.model = model;
    this.model.init();

    EditSecurityView view = new EditSecurityView(this, model);
  }

  /**
   * Sets wkn.
   *
   * @param wkn the wkn to set
   */
  public void setWkn(String wkn) {
    model.initWkn(wkn);
  }

  /**
   * Sets name.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    model.initName(name);
  }

  /**
   * Sets ISIN
   *
   * @param isin the isin to set
   */
  public void setIsin(String isin) {
    model.initIsin(isin);
  }

  /**
   * Sets the symbol
   *
   * @param symbol the symbol to set
   */
  public void setSymbol(String symbol) {
    model.initSymbol(symbol);
  }

  /**
   * Gets close value
   *
   * @return the close value as number
   */
  public Double getCloseValue() {
    return this.model.getCloseValue();
  }
}
