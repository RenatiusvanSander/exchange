package edu.remad.models.security;

import edu.remad.foundation.IPropertyId;

public enum EditSecurityIPropertyId implements IPropertyId {
  /**
   * property id for the name
   */
  NAME(0,"name"),
  /**
   * property id for the wkn
   */
  WKN(1, "wkn"),
  /**
   * property id for the ISIN
   */
  ISIN(2, "isin"),
  /**
   * property id for the symbol
   */
  SYMBOL(3, "symbol"),
  /**
   * proeprty id for the close value
   */
  CLOSE_VALUE(4,"closevalue");

  /**
   * the ordinary number of enum
   */
  public final int ordinary;

  /**
   * the identifier
   */
  public final String identifier;

  /**
   * Constructor
   *
   * @param ordinary   the ordinary number
   * @param identifier the string encoded identifier
   */
  EditSecurityIPropertyId(int ordinary, String identifier) {
    this.ordinary = ordinary;
    this.identifier = identifier;
  }

  @Override
  public String getPropertyId() {
    return this.identifier;
  }
}
