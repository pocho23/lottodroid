package com.lottodroid.sorting;

import com.lottodroid.Configuration;

public class LotterySorterFactory {

  public static LotterySorter newLotterySorter() {
    if (Configuration.IN_MEMORY_MODE) {
      return new MockLotterySorter();
    } else {
      throw new UnsupportedOperationException("The real implementation is not yet implemented");
    }
  }

}
