package com.androidsx.lottodroid.view;

import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;

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
   * @param lotteryId the ID of the lottery object that we are dealing with
   * @return the appropriate view controller
   */
  public static LotteryViewController<? extends Lottery> createViewController(LotteryId lotteryId) {
    if (lotteryId == LotteryId.BONOLOTO) {
      return new BonolotoViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.QUINIELA) {
      return new QuinielaViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.PRIMITIVA) {
      return new PrimitivaViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.LOTOTURF) {
      return new LototurfViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.QUINIGOL) {
      return new QuinigolViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.EUROMILLON) {
      return new EuromillonViewController(lotteryId.getName());
    } else if (lotteryId == LotteryId.LOTERIA_NACIONAL) {
      return new LoteriaNacionalViewController(lotteryId.getName());
    } else {
      throw new IllegalStateException();
    }
  }
}
