package com.lottodroid;

/**
 * Constants that define the names for the extra data that is passed to the intents in the
 * application: they are usually the {@code name} argument for the call {@link Intent.putExtra}.
 */
class IntentExtraDataNames {

  /**
   * Name for the extra that contains the appropriate view controller, passed to the details
   * activity
   */
  public static String LOTTERY_VIEW_CONTROLLER = "com.lottodroid.ViewController";
  
  /** Sorter object, passed from {@link Lottodroid} to {@link SortingActivity}. */
  public static String SORTER_IN = "com.lottodroid.sorting.LotterySorter-in";
  
  /** Sorter object, passed from {@link SortingActivity} to {@link Lottodroid} as a result */
  public static String SORTER_OUT = "com.lottodroid.sorting.LotterySorter-out";
  
}
