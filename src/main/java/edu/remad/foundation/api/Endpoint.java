package edu.remad.foundation.api;

/**
 * Endpoint identifies the endpoint to use.
 */
public enum Endpoint {
  /** endpoint end of day */
  END_OF_DAY(0, "/eod?access_key=%APIKEY%&symbols=", 45, 53);

  /** numbered value to identify enum */
  public final int numberedValue;

  /** the endpoint url */
  public final String url;

  /** start position for api key placeholder */
  public final int startPositionAsNumber;

  /** end position for api key placeholder */
  public final int endPositionAsNumber;

  /**
   * Constructor
   *
   * @param numberedValue the numbered value for endpoint
   * @param url the endpoint url
   * @param startPositionAsNumber start position for api key placeholder
   * @param endPositionAsNumber end position for api key placeholder
   */
  Endpoint(int numberedValue, String url, int startPositionAsNumber, int endPositionAsNumber) {
    this.numberedValue = numberedValue;
    this.url = url;
    this.startPositionAsNumber = startPositionAsNumber;
    this.endPositionAsNumber = endPositionAsNumber;
  }
}
