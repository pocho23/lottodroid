package com.lottodroid;

import android.view.View;
import android.widget.TextView;

class Lototurf extends Draw {
  private final static String numbers = "1 7 3 5 5";
  private final static String reinteger = "55";
  private final static String complementary = "1";

  public Lototurf(String date) {
    super(R.layout.lototurf_content_row, R.drawable.lototurf, "Lototurf", date);
  }

  @Override
  public void bindContent(View v) {
    TextView numCtrl = (TextView) v.findViewById(R.id.txtNumbers);
    numCtrl.setText(numbers);

    TextView complementaryCtrl = (TextView) v.findViewById(R.id.txtComplementary);
    complementaryCtrl.setText(complementary);

    TextView reintegerCtrl = (TextView) v.findViewById(R.id.txtReinteger);
    reintegerCtrl.setText(reinteger);
  }
}
