package edu.remad.models;

/**
 * Interface for initializations data
 */
public interface InitializationData {

  /**
   * Gets the model class name
   *
   * @return the class to initialize data for
   */
   Class<?> getModelType();
}
