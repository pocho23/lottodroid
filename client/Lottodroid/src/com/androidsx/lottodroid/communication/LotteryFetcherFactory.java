package com.androidsx.lottodroid.communication;

import android.content.Context;

import com.androidsx.lottodroid.Configuration;

/**
 * Factory that helps instantiate the appropriate lottery fetcher implementation.
 */
public class LotteryFetcherFactory {

  public static LotteryFetcher newLotteryFetcher(Context context)
      throws LotteryInfoUnavailableException {
    return Configuration.OFFLINE_MODE ? new MockLotteryFetcher()
        : Configuration.SERVER_MODE ? new ServerLotteryFetcher(context)
    	: new LotoluckLotteryFetcher(context);
  }

}
