package edu.remad.models.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditSecurityDataProviderTest {

  @Test
  public void testLoadloadCloseValueForSymbolShouldReturnDouble() {
    EditSecurityDataProvider dataProvider = new EditSecurityDataProvider();
    Double returnedNumber = dataProvider.loadCloseValueForSymbol("S6TB.XFRA");

    Assertions.assertTrue(returnedNumber > 0);
  }
}
