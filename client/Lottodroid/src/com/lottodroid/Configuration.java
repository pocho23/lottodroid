package com.lottodroid;

import android.content.Context;
import android.content.SharedPreferences;

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
  
  /**
   * Name for desired shared preferences file
   */
  private static final String PREFERENCES_FILE = "com.Lottodroid.Preferences";

  /**
   * Retrieve the contents of preferences file {@link PREFERENCES_FILE}. In order to retrieve shared preferences 
   * use the context of Lottodroid activity
   * 
   * @return Returns the single SharedPreferences instance that can be used to retrieve and modify
   *         the preference values.
   */
  public static SharedPreferences getSharedPreferences() {
    return Lottodroid.context.getSharedPreferences(
        Configuration.PREFERENCES_FILE, Context.MODE_PRIVATE);
  }

}
