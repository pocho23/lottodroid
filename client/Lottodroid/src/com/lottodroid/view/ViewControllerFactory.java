package com.lottodroid.view;

import com.lottodroid.FavoriteHandlerFactory;
import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Primitiva;
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
    if (lottery instanceof Bonoloto) {
      return new BonolotoViewController(lottery.getName(), FavoriteHandlerFactory
          .newFavoriteHandler());
    } else if (lottery instanceof Quiniela) {
      return new QuinielaViewController(lottery.getName(), FavoriteHandlerFactory
          .newFavoriteHandler());
    } else if (lottery instanceof Primitiva) {
      return new PrimitivaViewController(lottery.getName(), FavoriteHandlerFactory
          .newFavoriteHandler());
    } else {
      throw new IllegalStateException();
    }
  }
}
