package com.lottodroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Lototurf implements IDraw {
  public static final int iconResource = R.drawable.lototurf;
  public static final int layoutResource = R.layout.row_lototurf;

  public static final String title = "Lototurf";

  public String date = null;
  public String temperature = null;

  public Lototurf(String date) {
    this.date = date;
  }

  public int getLayoutResource() {
    return Lototurf.layoutResource;
  }

  public void bindData(View v) {
    TextView titleCtrl = (TextView) v.findViewById(R.id.title);
    if (titleCtrl != null)
      titleCtrl.setText(Lototurf.title);

    TextView dateCtrl = (TextView) v.findViewById(R.id.date);
    if (dateCtrl != null)
      dateCtrl.setText(date);

    ImageView iconCtrl = (ImageView) v.findViewById(R.id.icon);
    if (iconCtrl != null)
      iconCtrl.setImageResource(Lototurf.iconResource);

  }
}
