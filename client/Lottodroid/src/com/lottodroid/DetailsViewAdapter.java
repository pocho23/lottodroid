package com.lottodroid;

import java.util.Date;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * A simple adapter.
 * TODO: documentar, pero casi todo es temporal
 */
public class DetailsViewAdapter extends BaseExpandableListAdapter {
  // Sample data set. children[i] contains the children (String[]) for groups[i].
  
  // Solo temporal para probar la interfaz, sólo cuenta el tamaño
  private String[] groups = { "a", "b", "c", "d", "e", "f", "g", "h" };
  private String[][] children = { 
      { "a" }, { "a" }, { "a" }, { "a" } , 
      { "a" } , { "a" }, { "a" }, { "a" }};

  private Context context;

  public DetailsViewAdapter(Context context) {
    this.context = context;
  }

  public Object getChild(int groupPosition, int childPosition) {
    return children[groupPosition][childPosition];
  }

  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  public int getChildrenCount(int groupPosition) {
    return children[groupPosition].length;
  }

  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
      View convertView, ViewGroup parent) {

    // textView.setText(getChild(groupPosition, childPosition).toString());

    // Reuse the inflated view if possible
    if (convertView == null)
      convertView = View.inflate(context, R.layout.bonoloto_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    TextView numCtrl = (TextView) convertView.findViewById(R.id.txtNumbers);
    numCtrl.setText("1 2 3 4 5 6");

    TextView complementaryCtrl = (TextView) convertView.findViewById(R.id.txtComplementary);
    complementaryCtrl.setText("2");

    TextView reintegerCtrl = (TextView) convertView.findViewById(R.id.txtReinteger);
    reintegerCtrl.setText("4");

    return convertView;
  }

  public Object getGroup(int groupPosition) {
    return groups[groupPosition];
  }

  public int getGroupCount() {
    return groups.length;
  }

  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

    // Reuse the inflated view if possible
    if (convertView == null)
      convertView = View.inflate(context, R.layout.details_group_row, null);

    ((TextView) convertView.findViewById(R.id.date)).setText((new Date()).toString());

    return convertView;
  }

  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }

  public boolean hasStableIds() {
    return true;
  }

}