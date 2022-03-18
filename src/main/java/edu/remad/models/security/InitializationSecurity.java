package edu.remad.models.security;

import edu.remad.models.InitializationData;
import edu.remad.models.security.EditSecurityModel;
import org.json.simple.JSONObject;

/**
 * Populates a security with data
 */
public class InitializationSecurity implements InitializationData {

  @Override
  public Class<?> getModelType() {
    return EditSecurityModel.class;
  }

  /**
   * Gets name of a sceurity
   *
   * @return the security name
   */
  public String getName() {
    return "Name";
  }

  /**
   * Gets WKN
   *
   * @return the WKN
   */
  public String getWKN() {
    return "WKN";
  }

  /**
   * Gets ISIN
   *
   * @return the ISIN
   */
  public String getISIN() {
    return "ISIN";
  }

  /**
   * The loaded response
   *
   * @return the empty response
   */
  public JSONObject getResponse() {
    return new JSONObject();
  }

  /**
   * Gets symbol.
   *
   * @return the loaded ticker symbol
   */
  public String getSymbol() {
    return "S6TB.XFRA";
  }

  /**
   * Gets close Value of end of day.
   *
   * @return the close value in US dollar
   */
  public Double getCloseValue() {return 0.00;}
}
