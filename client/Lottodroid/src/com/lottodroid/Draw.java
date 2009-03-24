package com.lottodroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// TODO(omar): javadoc, y piensa sobre el nombre de la interfaz
abstract class Draw {
  public static final int mainLayoutResource = R.layout.main_layout_row;

  private int contentResource;
  private int iconResource;

  private String title;
  private String date;

  protected Draw(int contentResource, int iconResource, String title, String date) {
    this.contentResource = contentResource;
    this.iconResource = iconResource;
    this.title = title;
    this.date = date;
  }

  void bindTitleAndIcon(View v) {
    TextView titleCtrl = (TextView) v.findViewById(R.id.title);
    titleCtrl.setText(title);

    ImageView iconCtrl = (ImageView) v.findViewById(R.id.icon);
    iconCtrl.setImageResource(iconResource);
  }

  void bindDate(View v) {
    TextView dateCtrl = (TextView) v.findViewById(R.id.date);
    dateCtrl.setText(date);
  }

  public int getContentResource() {
    return contentResource;
  }
  
  public String getTitle() {
    return title;
  }

  abstract void bindContent(View v);
}
