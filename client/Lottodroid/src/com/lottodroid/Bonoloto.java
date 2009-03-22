package com.lottodroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class Bonoloto implements IDraw {
  private static final int iconResource = R.drawable.bonoloto;
  private static final int layoutResource = R.layout.row_bonoloto;

  private static final String title = "Bonoloto";

  private final String date;

  public Bonoloto(String date) {
    this.date = date;
  }

  @Override
  public int getLayoutResource() {
    return Bonoloto.layoutResource;
  }

  @Override
  public void bindData(View v) {
    // TODO(omar): borrar este comentario en cuanto lo leas
    // Quito la comprobacion de nulls abajo, porque si alguno es null es mejor
    // ver la excepcion, ya que seria un error que de verdad queremos ver y corregir
    TextView titleCtrl = (TextView) v.findViewById(R.id.title);
    titleCtrl.setText(Bonoloto.title);

    TextView dateCtrl = (TextView) v.findViewById(R.id.date);
    dateCtrl.setText(date);

    ImageView iconCtrl = (ImageView) v.findViewById(R.id.icon);
    iconCtrl.setImageResource(Bonoloto.iconResource);
  }
}
