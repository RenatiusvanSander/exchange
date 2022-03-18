package edu.remad.foundation.system;

import java.util.Map;

public class SystemConfigReader {

  /**
   * Reads config
   *
   * @return the system config
   */
  public Map<String, String> readConfig() {
    return System.getenv();
  }
}
