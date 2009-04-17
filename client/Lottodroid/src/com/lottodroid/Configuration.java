package com.lottodroid;

/**
 * Constants for global configuration.
 */
public class Configuration {

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead.
   */
  public static boolean OFFLINE_MODE = true;

  /**
   * If the in-memory mode is true, no dababase accesses will be performed: mocks for the dabase
   * access will handle in-memory data instead.
   */
  public static boolean IN_MEMORY_MODE = true;

}
