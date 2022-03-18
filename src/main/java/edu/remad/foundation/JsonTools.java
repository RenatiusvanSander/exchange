package edu.remad.foundation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Tools for converting string to json and read values.
 */
public class JsonTools {

  /**
   * the json parser
   */
  private static final JSONParser JSON_PARSER = new JSONParser();

  /**
   * Parses {@link String} to JSON object.
   *
   * @param stringEncodedJson string encoded JSON
   * @return the json object, {@link JSONObject}
   */
  public static JSONObject parseStringToJSON(String stringEncodedJson) {
    JSONObject json = new JSONObject();

    try {
      json = (JSONObject) JSON_PARSER.parse(stringEncodedJson);
    } catch (ParseException e) {
      String message = JsonTools.class.getName() + " failed to parse string to json!";
      throw new RuntimeException(message, e);
    }

    return json;
  }

  /**
   * Parses close value.
   *
   * @param data the JSON Array to parse the close value to a number
   * @return value of key close as double number
   */
  public static Double parseCloseToDouble(JSONArray data) {
    if (data == null) {
      throw new IllegalArgumentException(JsonTools.class.getName() + " data has to be not null.");
    }

    JSONObject dataJson = (JSONObject) data.get(0);

    return (double) dataJson.get("close");
  }
}
