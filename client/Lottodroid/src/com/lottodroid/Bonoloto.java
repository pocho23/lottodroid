package com.lottodroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Bonoloto implements IDraw {
  private static final int iconResource = R.drawable.bonoloto;
  private static final int layoutResource = R.layout.row_bonoloto;

  private static final String title = "Bonoloto";

  public String date = null;

  public Bonoloto(String date) {
    this.date = date;
  }

  public int getLayoutResource() {
    // MAL: De momento para poder coger esto desde la interfaz --> PABLO
    return Bonoloto.layoutResource;
  }

  public void bindData(View v) {
    TextView titleCtrl = (TextView) v.findViewById(R.id.title);
    if (titleCtrl != null)
      titleCtrl.setText(Bonoloto.title);

    TextView dateCtrl = (TextView) v.findViewById(R.id.date);
    if (dateCtrl != null)
      dateCtrl.setText(date);

    ImageView iconCtrl = (ImageView) v.findViewById(R.id.icon);
    if (iconCtrl != null)
      iconCtrl.setImageResource(Bonoloto.iconResource);
  }
}
