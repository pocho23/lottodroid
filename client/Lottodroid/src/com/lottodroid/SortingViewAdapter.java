package com.lottodroid;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lottodroid.model.Lottery;
import com.lottodroid.model.LotteryId;
import com.lottodroid.sorting.LotterySorter;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.ViewControllerFactory;

/**
 * Adapter that interacts with a {@link LotterySorter} object in order to move the items in its
 * list.
 */
class SortingViewAdapter extends BaseAdapter {

  private final Context context;
  private final LotterySorter sorter;

  public SortingViewAdapter(Context context, LotterySorter sorter) {
    this.context = context;
    this.sorter = sorter;
  }

  public void moveItem(int from, int to) {
    List<LotteryId> order = sorter.getOrder();
    LotteryId fromId = order.get(from);
    LotteryId toId = order.get(to);
    order.set(from, toId);
    order.set(to, fromId);
    sorter.setOrder(order);
  }

  public LotterySorter getSorter() {
    return sorter;
  }
  
  @Override
  public int getCount() {
    return sorter.getOrder().size();
  }

  @Override
  public Object getItem(int position) {
    return sorter.getOrder().get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LotteryId lotteryId = sorter.getOrder().get(position);

    LotteryViewController<? extends Lottery> viewController = ViewControllerFactory
        .createViewController(lotteryId);
    return viewController.createAndFillUpOrderView(lotteryId, context);
  }

}
