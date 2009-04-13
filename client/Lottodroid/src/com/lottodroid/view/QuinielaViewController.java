package com.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottodroid.R;
import com.lottodroid.model.Quiniela;
import com.lottodroid.util.DateFormatter;

class QuinielaViewController implements LotteryViewController<Quiniela> {

  private static final long serialVersionUID = -8774534644802955726L;
  
  private final String title;

  public QuinielaViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Quiniela quiniela, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.quiniela_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.quiniela);
    ((TextView) layoutView.findViewById(R.id.title)).setText(quiniela.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(quiniela
        .getDate()));

    fillUpView(layoutView, quiniela);

    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Quiniela quiniela, Context context) {
    View convertView = View.inflate(context, R.layout.quiniela_content_row, null);
    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, quiniela);

    return convertView;
  }

  private void fillUpView(View view, Quiniela quiniela) {
    int matchNumber;

    // match 0
    matchNumber = 0;
    ((TextView) view.findViewById(R.id.txtLocal0)).setText(quiniela.getHomeTeam(matchNumber));
    ((TextView) view.findViewById(R.id.txtVisitant0)).setText(quiniela.getAwayTeam(matchNumber));
    ((TextView) view.findViewById(R.id.txtResult0)).setText(quiniela.getResult(matchNumber)
        .toString());

    // match 1
    matchNumber = 1;
    ((TextView) view.findViewById(R.id.txtLocal1)).setText(quiniela.getHomeTeam(matchNumber));
    ((TextView) view.findViewById(R.id.txtVisitant1)).setText(quiniela.getAwayTeam(matchNumber));
    ((TextView) view.findViewById(R.id.txtResult1)).setText(quiniela.getResult(matchNumber)
        .toString());
  }

  @Override
  public int getIconResource() {
    return R.drawable.quiniela;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryViewController.LotteryId.QUINIELA;
  }

}
