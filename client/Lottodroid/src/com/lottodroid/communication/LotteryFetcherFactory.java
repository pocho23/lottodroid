package com.lottodroid.communication;

import java.net.ConnectException;

import android.content.Context;

import com.lottodroid.Configuration;

/**
 * Factory that helps instantiate the appropriate lottery fecther implementation.
 */
public class LotteryFetcherFactory {

  public static LotteryFetcher newLotteryFetcher(Context context) throws ConnectException {
    return Configuration.OFFLINE_MODE ? new MockLotteryFetcher()
        : new ServerLotteryFetcher(context);
  }

}
