package edu.remad;

import edu.remad.controllers.EditSecurityController;
import edu.remad.models.InitializationData;
import edu.remad.models.InitializationDataFactory;
import edu.remad.models.security.EditSecurityDataProvider;
import edu.remad.models.security.EditSecurityModel;
import edu.remad.models.security.ISecurityDataProvider;

public class Main {

  public static void main(String[] args) {
    ISecurityDataProvider dataProvider = new EditSecurityDataProvider();

    InitializationData initializationData = InitializationDataFactory.createInitializationSecurity();
    EditSecurityModel model = new EditSecurityModel(dataProvider, initializationData);
    EditSecurityController controller = new EditSecurityController(model);
  }
}
