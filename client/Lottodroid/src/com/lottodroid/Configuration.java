package com.lottodroid;

import com.lottodroid.communication.LotteryFetcher;

/**
 * Constants for global configuration.
 */
class Configuration {

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link LotteryFetcher}.
   */
  public static boolean OFFLINE_MODE = true;
}
