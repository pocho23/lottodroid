package com.androidsx.lottodroid.view;

import java.io.Serializable;

import android.content.Context;
import android.view.View;

import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;

/**
 * Manages for the view behavior of the different lottery result types, providing methods to fill up
 * the corresponding views, to get the lottery title, ...
 * 
 * @param <T> the lottery type
 */
public interface LotteryViewController<T extends Lottery> extends Serializable {

  View createAndFillUpOrderView(LotteryId lotteryId, Context context);
  
  View createAndFillUpMainView(T lottery, Context context);

  View createAndFillUpDetailsView(T lottery, Context context);
  
  View createAndFillUpFullView(T lottery, Context context);

  String getTitle();

  int getIconResource();

  LotteryId getId();
}
