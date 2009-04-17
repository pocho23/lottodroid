package com.lottodroid.view;

import java.util.HashMap;
import java.util.Map;

import com.lottodroid.MockFavoriteHandler;
import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Factory for the different view controller types.
 */
public class ViewControllerFactory {

  private ViewControllerFactory() {
    // This class is not instantiable
  }

  /**
   * Instantiates and returns the right implementation of a {@link LotteryViewController} for a
   * lottery type.
   * 
   * @param lottery the lottery object that we are dealing with
   * @return the appropriate view controller
   */
  public static LotteryViewController<? extends Lottery> createViewController(Lottery lottery) {

    Map<String, Boolean> favoritesMap = new HashMap<String, Boolean>();
    favoritesMap.put("Bonoloto", false);
    favoritesMap.put("Quiniela", true);

    if (lottery instanceof Bonoloto) {
      return new BonolotoViewController(lottery.getName(), new MockFavoriteHandler(favoritesMap));
    } else if (lottery instanceof Quiniela) {
      return new QuinielaViewController(lottery.getName(), new MockFavoriteHandler(favoritesMap));
    } else {
      throw new IllegalStateException();
    }
  }
}
