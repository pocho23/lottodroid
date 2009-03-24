package com.lottodroid;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

// TODO(omar): javadoc, y de paso reflexiona sobre el nombre de la clase
class DrawAdapter extends BaseAdapter {
  private final Context context;
  private final List<Draw> drawList;

  public DrawAdapter(Context context, List<Draw> drawList) {
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
    Draw draw = drawList.get(position);

    /* Get the main layout for a row */
    View layoutView = View.inflate(context, Draw.mainLayoutResource, null);
    
    /* Get the parent layout */
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    
    /* Inflate the main layout with the specific draw view */
    layoutContent.addView( View.inflate(context, draw.getContentResource(), null) );
    
    draw.bindTitleAndIcon(layoutView);
    draw.bindDate(layoutView);
    draw.bindContent(layoutView);

    return layoutView;
  }

}
