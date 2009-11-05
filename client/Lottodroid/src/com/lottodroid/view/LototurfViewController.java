package com.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottodroid.R;
import com.lottodroid.model.Lototurf;
import com.lottodroid.model.LotteryId;
import com.lottodroid.util.DateFormatter;

class LototurfViewController implements LotteryViewController<Lototurf> {

  private static final long serialVersionUID = 4189136947988075144L;

  private final String title;

  public LototurfViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Lototurf lototurf, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.lototurf_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.lototurf);
    ((TextView) layoutView.findViewById(R.id.title)).setText(lototurf.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(lototurf
        .getDate()));

    fillUpView(layoutView, lototurf);

    return layoutView;
  }

  @Override
  public View createAndFillUpOrderView(LotteryId lotteryId, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.lototurf);
    ((TextView) layoutView.findViewById(R.id.title)).setText(lotteryId.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText("");

    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Lototurf lototurf, Context context) {
    View convertView = View.inflate(context, R.layout.lototurf_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, lototurf);

    return convertView;
  }

  private void fillUpView(View view, Lototurf lototurf) {
    String numbers = lototurf.getNum1() + " " + lototurf.getNum2() + " " + lototurf.getNum3() + " "
        + lototurf.getNum4() + " " + lototurf.getNum5() + " " + lototurf.getNum6();

    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView)
     view.findViewById(R.id.txtReinteger)).setText(Integer.toString(lototurf.getReintegro()));
     ((TextView)
     view.findViewById(R.id.txtCaballoGanador)).setText(Integer.toString(lototurf.getCaballoGanador()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.lototurf;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTOTURF;
  }

}
