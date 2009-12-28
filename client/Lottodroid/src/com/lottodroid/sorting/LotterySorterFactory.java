package com.lottodroid.sorting;

import android.content.Context;

import com.lottodroid.Configuration;

public class LotterySorterFactory {

  private static LotterySorter INSTANCE;
  
  public static LotterySorter getLotterySorter(final Context context) {
    if (INSTANCE == null) {
      if (Configuration.IN_MEMORY_MODE) {
        INSTANCE = new MockLotterySorter();
      } else {
        INSTANCE = new PreferencesLotterySorter(context);
      }
    }
    return INSTANCE;
  }

}
