package edu.remad.models.security;

import edu.remad.models.DataModel;
import edu.remad.models.InitializationData;
import org.json.simple.JSONObject;

/**
 * Edits data of a security
 */
public class EditSecurityModel extends DataModel implements IDataModel, Runnable {

  /**
   * the security data provider
   */
  private final ISecurityDataProvider dataProvider;
  /**
   * name of security
   */
  private String name;
  /**
   * the wkn
   */
  private String wkn;
  /**
   * the ISIN
   */
  private String isin;
  /**
   * the ticker symbol
   */
  private String symbol;
  /**
   * security data as encoded JSON
   */
  private JSONObject securityData;
  /**
   * The close value of end of day
   */
  private Double closeValue;
  /**
   * The thread to start
   */
  private Thread thread;

  /**
   * Constructs a data provider for editing a security.
   *
   * @param dataProvider the security data provider
   * @param data         the initialization data object
   */
  public EditSecurityModel(ISecurityDataProvider dataProvider, InitializationData data) {
    super(data);

    this.dataProvider = dataProvider;
    this.thread = new Thread(this);

    this.initProperties();
    this.thread.start();
  }

  /**
   * Gets close value
   *
   * @return close value as number with digits
   */
  public Double getCloseValue() {
    return closeValue;
  }

  /**
   * Gets name.
   *
   * @return the name of security
   */
  public String getName() {
    return name;
  }

  /**
   * Initializes name
   *
   * @param name name to initialize
   */
  public void initName(String name) {
    this.name = name;
  }

  /**
   * Gets WKN.
   *
   * @return the WKN
   */
  public String getWkn() {
    return wkn;
  }

  /**
   * Initializes WKN
   *
   * @param wkn the WKN to init
   */
  public void initWkn(String wkn) {
    this.wkn = wkn;
  }

  /**
   * Gets the isin
   *
   * @return the isin
   */
  public String getIsin() {
    return isin;
  }

  /**
   * Initializes isin
   *
   * @param isin the isin to init
   */
  public void initIsin(String isin) {
    this.isin = isin;
  }

  /**
   * Gets the symbol
   *
   * @return the symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Initializes symbol
   *
   * @param symbol the symbol to initialize
   */
  public void initSymbol(String symbol) {
    this.symbol = symbol;
    init();
  }

  /**
   * Gets data of security
   *
   * @return security data
   */
  public JSONObject getSecurityData() {
    return securityData;
  }

  /**
   * Initializes security data
   *
   * @param securityData the security data to initialize
   */
  public void initSecurityData(JSONObject securityData) {
    this.securityData = securityData;
  }

  /**
   * Gets {@link InitializationSecurity}
   *
   * @param data the initialization data object
   * @return a valid {@link InitializationSecurity}
   */
  private InitializationSecurity getInitializationSecurity(InitializationData data) {
    if (data instanceof InitializationSecurity) {
      return (InitializationSecurity) data;
    } else {
      throw new IllegalStateException("Illegal InitializationData");
    }
  }

  @Override
  protected void initProperties() {
    InitializationSecurity data = this.getInitializationSecurity(this.getInitData());

    this.name = data.getName();
    this.wkn = data.getWKN();
    this.isin = data.getISIN();
    this.securityData = data.getResponse();
    this.symbol = data.getSymbol();
  }

  @Override
  public void init() {
    this.initSecurityData();
    this.initCloseData();
  }

  /**
   * Method to init close data
   */
  private void initCloseData() {
    this.closeValue = this.dataProvider.loadCloseValueForSymbol(this.symbol);
  }

  /**
   * Method to initialize security data
   */
  private void initSecurityData() {
    this.securityData = this.dataProvider.loadSymbol(this.symbol);
  }

  @Override
  public void run() {
    while (!this.isThreadStopped()) {
    }
  }
}
