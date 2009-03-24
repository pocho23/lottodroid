package com.lottodroid;

import android.view.View;
import android.widget.TextView;

class Bonoloto extends Draw {
  private final static String numbers = "1 2 3 4 5 6";
  private final static String reinteger = "1";
  private final static String complementary = "33";

  public Bonoloto(String date) {
    super(R.layout.bonoloto_content_row, R.drawable.bonoloto, "Bonoloto", date);
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
