package edu.remad.foundation.system;

import java.util.HashMap;
import java.util.Map;

public class SystemConfigLoader {

  /**
   * the loaded and stored config
   */
  private final Map<String, String> storedConfig;
  /**
   * the system config reader
   */
  private final SystemConfigReader configReader;

  /**
   * Constructor
   */
  public SystemConfigLoader() {
    this.configReader = new SystemConfigReader();
    this.storedConfig = new HashMap<>();

    this.load();
  }

  /**
   * Loads the system environment
   */
  private void load() {
    storedConfig.putAll(this.configReader.readConfig());
  }

  /**
   * Gets environment
   *
   * @return the environment settings as a map
   */
  public Map<String, String> getEnv() {
    return new HashMap<>(this.storedConfig);  // defensive copy
  }

  /**
   * @param key the api key
   * @return string-encoded API key
   */
  public String getEnvValueOfKey(String key) {
    if (!(key instanceof String) || key.length() == 0) {
      throw new IllegalArgumentException("key has to be not null or empty.");
    }

    String environmentValueOfKey = this.storedConfig.get(key);

    return environmentValueOfKey == null ? "" : environmentValueOfKey;
  }
}
