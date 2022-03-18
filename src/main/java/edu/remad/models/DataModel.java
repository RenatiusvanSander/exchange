package edu.remad.models;

import edu.remad.views.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * The general methods of a data model
 */
public abstract class DataModel implements IModel {

  /**
   * the initialization data for generic names
   */
  private final InitializationData initData;
  /**
   * the observers to update
   */
  private final List<Observer> observer;
  /**
   * stop interrupts the thread
   */
  private volatile boolean stopThread;

  /**
   * Constructor Data Model
   *
   * @param initData the initialization data for data model
   */
  public DataModel(InitializationData initData) {
    this.initData = initData;
    this.observer = new ArrayList<>();
    this.stopThread = false;
  }

  /**
   * Method to initialize properties
   */
  protected abstract void initProperties();

  /**
   * Gets initialization data
   *
   * @return the initialization data, {@link InitializationData}
   */
  public InitializationData getInitData() {
    return initData;
  }

  /**
   * Registers observer
   *
   * @param observer the observer to register
   */
  public void registerObserver(Observer observer) {
    this.observer.add(observer);
  }

  /**
   * Removes the observer
   *
   * @param observer the observer to remove
   */
  public void removeObserver(Observer observer) {
    this.observer.remove(observer);
  }

  /**
   * Stops the thread.
   */
  public void stopThread() {
    this.stopThread = true;
  }

  /**
   * Gets stop thread flag.
   *
   * @return true / false for thread is to stop
   */
  public boolean isThreadStopped() {
    return this.stopThread;
  }
}
