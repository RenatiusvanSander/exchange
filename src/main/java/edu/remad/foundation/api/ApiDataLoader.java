package edu.remad.foundation.api;

import edu.remad.foundation.JsonTools;
import edu.remad.foundation.system.SystemConfigReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Loads data from marketstack API for securities.
 */
public class ApiDataLoader {

  /**
   * the cache, key is the ticker symbol and value is the JSON stored data.
   */
  private final Map<String, JSONObject> cache;

  /**
   * api caller to call endpoints of marketstack api
   */
  private final ApiCaller apiCaller;

  /**
   * Constructor
   */
  public ApiDataLoader() {
    this.apiCaller = new ApiCaller(loadApiKey());
    this.cache = new HashMap<>();
  }

  /**
   * Loads API key
   *
   * @return the api key
   */
  private String loadApiKey() {
    SystemConfigReader configReader = new SystemConfigReader();
    Map<String, String> environment = configReader.readConfig();
    String apiKey = environment.get("APIKEY");

    return apiKey;
  }

  /**
   * Loads end of day data for a symbol.
   *
   * @param symbol the ticker symbols to load by end of life endpoint
   * @return the api response body encoded as {@link JSONObject}
   * @throws IOException
   * @throws InterruptedException
   */
  public JSONObject loadEndOfDayBySymbol(String symbol)
      throws IOException, InterruptedException {
    if (this.cache.containsKey(symbol)) {
      return this.cache.get(symbol);
    }

    HttpResponse loadedResult = apiCaller.receiveResponse(Endpoint.END_OF_DAY, symbol);
    JSONObject jsonResponseData = JsonTools.parseStringToJSON(loadedResult.body().toString());
    this.cache.put(symbol, jsonResponseData);

    return jsonResponseData;
  }
}
