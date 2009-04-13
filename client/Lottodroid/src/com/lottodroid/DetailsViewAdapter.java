package com.lottodroid;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lottodroid.model.Lottery;
import com.lottodroid.util.DateFormatter;
import com.lottodroid.view.LotteryViewController;

/**
 * Adapter for the details view: maintains the association between the list of results
 * of a specific lottery type and their view.
 * 
 * @extends  BaseExpandableListAdapter
 */
public class DetailsViewAdapter extends BaseExpandableListAdapter {
  private List<? extends Lottery> listLottery;
  private Context context;
  private final LotteryViewController<Lottery> viewController;

  public DetailsViewAdapter(Context context, List<? extends Lottery> listLottery, LotteryViewController<Lottery> viewController) {
    this.context = context;
    this.listLottery = listLottery;
    this.viewController = viewController;
  }

  public Object getChild(int groupPosition, int childPosition) {
    return listLottery.get(groupPosition);
  }

  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  public int getChildrenCount(int groupPosition) {
    // There will be only one child per group
    return 1;
  }

  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
      View convertView, ViewGroup parent) {
    Lottery lottery = (Lottery) getChild(groupPosition, childPosition);
    return viewController.createAndFillUpDetailsView(lottery, context);
  }

  public Object getGroup(int groupPosition) {
    return listLottery.get(groupPosition);
  }

  public int getGroupCount() {
    return listLottery.size();
  }

  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

    // Reuse the inflated view if possible
    if (convertView == null)
      convertView = View.inflate(context, R.layout.details_group_row, null);

    Date date = ((Lottery) getGroup(groupPosition)).getDate();
    ((TextView) convertView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(date));

    return convertView;
  }

  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }

  public boolean hasStableIds() {
    return true;
  }

}