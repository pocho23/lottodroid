package com.lottodroid;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

// TODO(omar): javadoc, y de paso reflexiona sobre el nombre de la clase
class DrawAdapter extends BaseAdapter {
  private final Context context;
  private final List<IDraw> drawList;

  public DrawAdapter(Context context, List<IDraw> drawList) {
    this.context = context;
    this.drawList = drawList;
  }

  @Override
  public int getCount() {
    return drawList.size();
  }

  @Override
  public Object getItem(int position) {
    return drawList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    IDraw draw = drawList.get(position);

    // Get the specific draw view for this object
    View v = View.inflate(context, draw.getLayoutResource(), null);
    draw.bindData(v);

    return v;
  }

}
