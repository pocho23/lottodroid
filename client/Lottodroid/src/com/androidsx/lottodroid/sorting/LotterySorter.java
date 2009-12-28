package com.androidsx.lottodroid.sorting;

import java.io.Serializable;
import java.util.List;

import com.androidsx.lottodroid.model.LotteryId;

/**
 * Handles the order in which the lottery results should be shown to the user.
 */
public interface LotterySorter extends Serializable {

  /**
   * Sets the order for the lottery results.
   * <p>
   * It does not fail even if not all the lottery IDs are provided: the user should make sure of
   * that.
   * 
   * @param order the lottery order to be set
   */
  void setOrder(List<LotteryId> order);

  /**
   * Gets the order for the lottery results.
   * <p>
   * It does not ensure that all lottery IDs are contained in the returned list.
   * <p>
   * Even if {@link #setOrder} has not been called, this method returns a non-null list.
   * 
   * @return the lottery IDs in the correponding order
   */
  List<LotteryId> getOrder();

}
