package com.androidsx.lottodroid.sorting;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidsx.lottodroid.Configuration;
import com.androidsx.lottodroid.model.LotteryId;

public class PreferencesLotterySorter implements LotterySorter {

  private static class LotteryIdListSerializer {
    private static final String TAG = LotteryIdListSerializer.class.getName();
    private static final String SEPARATOR = "|";

    private static String list2str(List<LotteryId> order) {
      final StringBuilder builder = new StringBuilder();
      for (LotteryId id : order) {
        builder.append(id.getName() + SEPARATOR);
      }
      final String ret = builder.toString();
      Log.d(TAG, ret);
      return ret;
    }

    private static List<LotteryId> str2list(String storedPreference) {
      String[] strArray = storedPreference.split("\\" + SEPARATOR);
      final List<LotteryId> ret = new LinkedList<LotteryId>();
      Log.d(TAG, "begin");
      for (String str : strArray) {
        Log.d(TAG, str);
        if (!str.equals("")) {
          LotteryId fromName = LotteryId.fromName(str.trim());
          if (fromName != null) {
            ret.add(fromName);
          } else {
            Log.e(TAG, "Invalid lottery ID: " + str);
          }
        }
      }
      Log.d(TAG, "end");
      return ret;
    }

  }

  private static final long serialVersionUID = 1L;
  private static final String TAG = PreferencesLotterySorter.class.getName();
  private final Context context;

  public PreferencesLotterySorter(Context context) {
    this.context = context;
  }

  @Override
  public List<LotteryId> getOrder() {
    Log.d(TAG, "getOrder() - begin");
    final SharedPreferences preferences = context.getSharedPreferences(
        Configuration.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    final String storedPreference = preferences.getString(
        Configuration.PREFERENCE_SORTED_LIST_ATTRIBUTE, "");
    final List<LotteryId> list = LotteryIdListSerializer
        .str2list(storedPreference);
    for (LotteryId id : LotteryId.values()) {
      if (!list.contains(id)) {
        Log.w(TAG, "There is no order information for the lottery " + id);
        list.add(id);
      }
    }
    Log.d(TAG, "getOrder() - end: " + list.size());
    return list;
  }

  @Override
  public void setOrder(List<LotteryId> order) {
    Log.d(TAG, "setOrder(" + order.size() + ")");
    final SharedPreferences preferences = context.getSharedPreferences(
        Configuration.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(Configuration.PREFERENCE_SORTED_LIST_ATTRIBUTE,
        LotteryIdListSerializer.list2str(order));
    editor.commit();
  }

}
