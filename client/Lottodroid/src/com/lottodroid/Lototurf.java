package com.lottodroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class Lototurf implements IDraw {
  private static final int iconResource = R.drawable.lototurf;
  private static final int layoutResource = R.layout.row_lototurf;

  private static final String title = "Lototurf";

  private final String date;

  public Lototurf(String date) {
    this.date = date;
  }

  @Override
  public int getLayoutResource() {
    return Lototurf.layoutResource;
  }

  @Override
  public void bindData(View v) {
    TextView titleCtrl = (TextView) v.findViewById(R.id.title);
    titleCtrl.setText(Lototurf.title);

    TextView dateCtrl = (TextView) v.findViewById(R.id.date);
    dateCtrl.setText(date);

    ImageView iconCtrl = (ImageView) v.findViewById(R.id.icon);
    iconCtrl.setImageResource(Lototurf.iconResource);
  }
}
