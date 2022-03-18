package edu.remad.models;

import edu.remad.models.security.InitializationSecurity;

public class InitializationDataFactory {

  public static InitializationData createInitializationSecurity() {
    return new InitializationSecurity();
  }

}
