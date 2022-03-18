package edu.remad.models.security;

import org.json.simple.JSONObject;

/**
 * Interface for data provider load security data
 */
public interface ISecurityDataProvider {

  /**
   * Loads symbol.
   *
   * @param symbol the symbol to load data for
   * @return the loaded symbols data as encoded JSON, {@link JSONObject}
   */
  JSONObject loadSymbol(String symbol);

  /**
   * Loads close value.
   *
   * @param symbol the symbol for loading the close value
   * @return the close value as double number
   */
  Double loadCloseValueForSymbol(String symbol);
}
