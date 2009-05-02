package com.lottodroid.sorting;

import java.util.LinkedList;
import java.util.List;

import com.lottodroid.model.LotteryId;

/**
 * In-memory implementation for the lottery sorter.
 */
class MockLotterySorter implements LotterySorter {
  
  private static final List<LotteryId> DEFAULT_ORDER;
  static {
    DEFAULT_ORDER = new LinkedList<LotteryId>();
    for (LotteryId id : LotteryId.values()) {
      DEFAULT_ORDER.add(id);
    }
  }
  
  private List<LotteryId> order;
  
  public MockLotterySorter() {
    order = DEFAULT_ORDER;
  }

  @Override
  public void setOrder(List<LotteryId> order) {
    this.order = order;
  }

  @Override
  public List<LotteryId> getOrder() {
    return order;
  }

}
