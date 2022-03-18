package edu.remad.models.security;

import edu.remad.foundation.JsonTools;
import edu.remad.foundation.api.ApiDataLoader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Data provider for editing a security.
 */
public class EditSecurityDataProvider implements ISecurityDataProvider {

  /**
   * the API data loader for API calls
   */
  private final ApiDataLoader apiDataLoader;
  /**
   * The cache for close values of distinct ticker symbols
   */
  private final Map<String, Double> closeValues;

  /**
   * Constructor
   */
  public EditSecurityDataProvider() {
    this.apiDataLoader = new ApiDataLoader();
    this.closeValues = new HashMap<>();
  }

  @Override
  public JSONObject loadSymbol(String symbol) {
    JSONObject jsonResult = new JSONObject();

    try {
      jsonResult = this.apiDataLoader.loadEndOfDayBySymbol(symbol);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return jsonResult;
  }

  @Override
  public Double loadCloseValueForSymbol(String symbol) {

    if (this.closeValues.containsKey(symbol)) {
      return this.closeValues.get(symbol); // return cached close value
    }

    try {
      JSONObject symbolData = this.apiDataLoader.loadEndOfDayBySymbol(symbol);

      if (symbol != null && symbol.length() > 1 && symbolData != null) {
        Double closeValue = JsonTools.parseCloseToDouble((JSONArray) symbolData.get("data"));
        this.closeValues.put(symbol, closeValue);

        return closeValue;
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    throw new RuntimeException(
        this.getClass().getName() + " close value failed by converting to double.");
  }
}
