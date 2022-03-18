package edu.remad.foundation.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiCaller {

  /**
   * the base api url of Market Stack
   */
  private static final String BASE_API_URL = "http://api.marketstack.com/v1";

  /**
   * the placeholder api key to replace
   */
  private static final String PLACEHOLDER_APIKEY = "%APIKEY%";

  /**
   * the api key to use for api calls
   */
  private String apiKey;

  /**
   * Constructor
   *
   * @param apiKey the api key
   */
  public ApiCaller(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Gets API keys.
   *
   * @return the API key
   */
  public String getApiKey() {
    return apiKey;
  }

  /**
   * Sets API key
   *
   * @param apiKey the API key to set
   */
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Builds end of life endpoint url
   *
   * @param endpoint  the api key
   * @param symbol the ticker symbols to load
   * @return string-encoded end of life endpoint url
   */
  public String buildEndOfLifeUrl(Endpoint endpoint, String symbol) {
    StringBuilder sb = new StringBuilder(BASE_API_URL);
    sb.append(endpoint.url);
    sb.replace(endpoint.startPositionAsNumber, endpoint.endPositionAsNumber, this.apiKey);

    // add symbols, later extract into method
    if (symbol != null && symbol.length() > 1) {
        sb.append(symbol);
    }

    return sb.toString();
  }

  /**
   * Builds the client
   *
   * @return the build http client, {@link HttpClient}
   */
  private HttpClient buildHttpClient() {
    return HttpClient
        .newBuilder()
        .version(Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(10)).build();
  }

  /**
   * Builds request
   *
   * @param endPointUrl the string-encoded build endpoint url
   * @return the build http request, {@link HttpRequest}
   */
  private HttpRequest buildRequest(String endPointUrl) {
    URI endPointUri = URI.create(endPointUrl);

    return HttpRequest.newBuilder()
        .GET()
        .setHeader("User-Agent", "Java 11 HttpClient remad")
        .uri(endPointUri)
        .build();
  }

  /**
   * Receives response of the called endpoint, identified by {@link Endpoint}.
   *
   * @param endpoint the endpoint instance, {@link Endpoint}
   * @param symbol  the ticker symbol to load
   * @return the http response
   * @throws IOException
   * @throws InterruptedException
   */
  public HttpResponse<String> receiveResponse(Endpoint endpoint, String symbol)
      throws IOException, InterruptedException {
    String endpointUrl = buildApiEndpointUrl(endpoint, symbol);
    HttpRequest request = buildRequest(endpointUrl);
    HttpClient client = buildHttpClient();

    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }

  /**
   * Builds api endpoint url
   *
   * @param endpoint the endpoint instance, {@link Endpoint}
   * @param symbol  the ticker symbol
   * @return string encoded api endpoint url.
   */
  private String buildApiEndpointUrl(Endpoint endpoint, String symbol) {
    String endPointUrl = "";

    switch (endpoint) {
      case END_OF_DAY: {
        endPointUrl = buildEndOfLifeUrl(endpoint, symbol);
        break;
      }
      default: {
        throw new IllegalArgumentException("This endpoint does not exist");
      }
    }

    return endPointUrl;
  }
}
