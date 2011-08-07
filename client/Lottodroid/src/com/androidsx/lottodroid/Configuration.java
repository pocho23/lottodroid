package com.androidsx.lottodroid;


/**
 * Constants for global configuration.
 */
public class Configuration {

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead.
   */
  public static boolean OFFLINE_MODE = false;
  
  /**
   * If the server mode is true, no communication with the lotoluck will be performed: parsed data will
   * be generated instead.
   */
  public static boolean SERVER_MODE = false;

  /**
   * If the in-memory mode is true, no file accesses will be performed: mock implementations that
   * store all data in memory will be used.
   */
  public static boolean IN_MEMORY_MODE = false;

  /**
   * Name for desired shared preferences file
   */
  public static final String PREFERENCES_FILE_NAME = "com.Lottodroid.Preferences";
  
  /**
   * Name of the property in the preferences that keeps the sorted list of lottery IDs.
   */
  public static final String PREFERENCE_SORTED_LIST_ATTRIBUTE = "com.Lottodroid.Preferences.SortedList";

}
