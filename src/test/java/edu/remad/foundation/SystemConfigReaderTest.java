package edu.remad.foundation;

import edu.remad.foundation.system.SystemConfigReader;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SystemConfigReaderTest {

  @Test
  public void testRead() {
    SystemConfigReader configReader = new SystemConfigReader();
    Map<String, String> environment = configReader.readConfig();

    Assertions.assertTrue(environment.size() > 0);

    String apiKey = environment.get("APIKEY");
    Assertions.assertEquals(32, apiKey.length());
  }
}
