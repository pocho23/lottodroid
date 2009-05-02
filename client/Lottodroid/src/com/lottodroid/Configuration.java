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
  public static boolean OFFLINE_MODE = false;

  /**
   * If the in-memory mode is true, no file accesses will be performed: mock implementations that
   * store all data in memory will be used.
   */
  public static boolean IN_MEMORY_MODE = true;

  /**
   * Name for desired shared preferences file
   */
  private static final String PREFERENCES_FILE = "com.Lottodroid.Preferences";

}
