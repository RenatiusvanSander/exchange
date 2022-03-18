package edu.remad.models;

import edu.remad.foundation.system.SystemConfigReader;
import edu.remad.foundation.api.ApiCaller;
import edu.remad.foundation.api.Endpoint;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiCallerTest {

  @Test
  public void testBuildApiUrl() {
    SystemConfigReader configReader = new SystemConfigReader();
    Map<String, String> environment = configReader.readConfig();
    String apiKey = environment.get("APIKEY");
    String symbol = "AAPL";

    ApiCaller api = new ApiCaller(apiKey);
    String endPointUrl = api.buildEndOfLifeUrl(Endpoint.END_OF_DAY, symbol);

    System.out.println(endPointUrl);
    Assertions.assertEquals(90, endPointUrl.length());
  }

  @Test
  public void testReceive() throws IOException, InterruptedException {
    SystemConfigReader configReader = new SystemConfigReader();
    Map<String, String> environment = configReader.readConfig();
    String apiKey = environment.get("APIKEY");

    String symbol = "S6TB.XFRA";
    ApiCaller api = new ApiCaller(apiKey);
    HttpResponse<String> response = api.receiveResponse(Endpoint.END_OF_DAY, symbol);

    String result = response.body();
    Assertions.assertNotNull(result);

    Assertions.assertEquals(200, response.statusCode());

    Assertions.assertEquals(1119, result.length());
  }
}
