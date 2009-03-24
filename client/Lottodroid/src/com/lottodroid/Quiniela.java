package com.lottodroid;

import android.view.View;
import android.widget.TextView;

class Quiniela extends Draw {
  private final static String local = "Barcelona";
  private final static String visitant = "Betis";
  private final static String result = "1";

  public Quiniela(String date) {
    super(R.layout.quiniela_content_row, R.drawable.quiniela, "Quiniela", date);
  }

  @Override
  public void bindContent(View v) {
    TextView numCtrl = (TextView) v.findViewById(R.id.txtLocal0);
    numCtrl.setText(local);

    TextView complementaryCtrl = (TextView) v.findViewById(R.id.txtVisitant0);
    complementaryCtrl.setText(visitant);

    TextView reintegerCtrl = (TextView) v.findViewById(R.id.txtResult0);
    reintegerCtrl.setText(result);
  }
}
