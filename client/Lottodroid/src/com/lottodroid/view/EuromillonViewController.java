package com.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottodroid.R;
import com.lottodroid.model.Euromillon;
import com.lottodroid.model.LotteryId;
import com.lottodroid.util.DateFormatter;

class EuromillonViewController implements LotteryViewController<Euromillon> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public EuromillonViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Euromillon euromillon, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.euromillon_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.bonoloto);
    ((TextView) layoutView.findViewById(R.id.title)).setText(euromillon.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(euromillon
        .getDate()));

    fillUpView(layoutView, euromillon);

    return layoutView;
  }
  
  @Override
  public View createAndFillUpOrderView(LotteryId lotteryId, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    
    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.bonoloto);
    ((TextView) layoutView.findViewById(R.id.title)).setText(lotteryId.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText("");
    
    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Euromillon euromillon, Context context) {
    View convertView = View.inflate(context, R.layout.euromillon_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, euromillon);

    return convertView;
  }

  private void fillUpView(View view, Euromillon euromillon) {
    String numbers =  euromillon.getNum1() + " " + euromillon.getNum2() + " " + euromillon.getNum3() + " " +
                      euromillon.getNum4() + " " + euromillon.getNum5();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtEstrella1)).setText(Integer.toString(euromillon.getEstrella1()));
    ((TextView) view.findViewById(R.id.txtEstrella2)).setText(Integer.toString(euromillon.getEstrella2()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.bonoloto;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.EUROMILLON;
  }

}
