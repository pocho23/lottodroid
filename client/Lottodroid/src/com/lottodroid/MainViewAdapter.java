package com.lottodroid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lottodroid.model.Lottery;
import com.lottodroid.model.LotteryId;
import com.lottodroid.sorting.LotterySorter;
import com.lottodroid.sorting.LotterySorterFactory;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.ViewControllerFactory;

/**
 * Adapter for the main view: maintains the association between rows in the list and actual lottery
 * results.
 */
class MainViewAdapter extends BaseAdapter {

  private static final String TAG = "com.lottodroid.MainViewAdapter";
  
  private List<Lottery> lotteryList;
  private final Context context;
  private final LotterySorter sorter;
  
  private boolean orderMode = false;

  public MainViewAdapter(Context context, List<Lottery> lotteryList) {
    this.context = context;
    this.lotteryList = lotteryList;
    sorter = LotterySorterFactory.newLotterySorter();
    refresh();
  }

  @Override
  public int getCount() {
    return lotteryList.size();
  }

  @Override
  public Object getItem(int position) {
    refresh();
    return lotteryList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  // TODO(pablo): we should only work with the IDs, rather than doing the refresh...
  public void moveItem(int from, int to) {
    List<LotteryId> order = sorter.getOrder();
    LotteryId fromId = order.get(from);
    LotteryId toId = order.get(to);
    order.set(from, toId);
    order.set(to, fromId);
    sorter.setOrder(order);
    refresh();
  }

  public boolean getOrderMode() {
    return this.orderMode;
  }

  public void setOrderMode(boolean orderMode) {
    this.orderMode = orderMode;
  }

  // TODO(pablo): We are currently doing this way too often
  public void refresh() {
    List<LotteryId> order = sorter.getOrder();
    lotteryList = sort(lotteryList, order);
  }

  private List<Lottery> sort(List<Lottery> unsortedLotteries, List<LotteryId> order) {
    Map<LotteryId, Lottery> idToLotteryMap = new HashMap<LotteryId, Lottery>();
    for (Lottery lottery : unsortedLotteries) {
      idToLotteryMap.put(lottery.getId(), lottery);
    }

    List<Lottery> sortedLotteries = new LinkedList<Lottery>();
    for (LotteryId id : order) {
      Lottery lottery = idToLotteryMap.get(id);
      sortedLotteries.add(lottery);
      idToLotteryMap.remove(id);
    }

    // Now add any remaining elements for which there is no order information
    for (Lottery lottery : idToLotteryMap.values()) {
      Log.w(TAG, "There is no order information for the lottery " + lottery);
      sortedLotteries.add(lottery);
    }

    return sortedLotteries;
  }
  
  /**
   * Creates a custom view for a row in the list, which corresponds to a particular type of lottery
   * result
   */
  @SuppressWarnings("unchecked") // TODO(pablo): Can I fix the generic types mess here?
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Lottery lottery = lotteryList.get(position);
    
    LotteryViewController viewController = ViewControllerFactory.createViewController(lottery);
    if (!orderMode) {
      return viewController.createAndFillUpMainView(lottery, context);
    } else {
      return viewController.createAndFillUpOrderView(lottery, context);
    }
  }

}
