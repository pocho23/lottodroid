package com.lottodroid;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lottodroid.model.Lottery;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.ViewControllerFactory;

/**
 * Adapter for the main view: maintains the association between rows in the list and actual lottery
 * results.
 */
class MainViewAdapter extends BaseAdapter {

  private final List<Lottery> lotteryList;
  private final Context context;

  public MainViewAdapter(Context context, List<Lottery> lotteryList) {
    this.context = context;
    this.lotteryList = lotteryList;
  }

  @Override
  public int getCount() {
    return lotteryList.size();
  }

  @Override
  public Object getItem(int position) {
    return lotteryList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
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
    return viewController.createAndFillUpMainView(lottery, context);
  }

}
