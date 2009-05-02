package com.lottodroid.sorting;

import com.lottodroid.Configuration;

public class LotterySorterFactory {

  private static LotterySorter INSTANCE;
  static {
    if (Configuration.IN_MEMORY_MODE) {
      INSTANCE = new MockLotterySorter();
    } else {
      throw new UnsupportedOperationException("The real implementation is not yet implemented");
    }
  }
  
  public static LotterySorter getLotterySorter() {
    return INSTANCE;
  }

}
