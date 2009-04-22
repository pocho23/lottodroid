package com.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottodroid.FavoriteHandler;
import com.lottodroid.R;
import com.lottodroid.model.Primitiva;
import com.lottodroid.util.DateFormatter;

class PrimitivaViewController implements LotteryViewController<Primitiva> {
  
  private static final long serialVersionUID = 4189136947988075144L;
  
  private final String title;
  private final FavoriteHandler favorites;

  public PrimitivaViewController(String title, FavoriteHandler favorites) {
    this.title = title;
    this.favorites = favorites;
  }

  @Override
  public View createAndFillUpMainView(Primitiva primitiva, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.primitiva_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.primitiva);
    ((TextView) layoutView.findViewById(R.id.title)).setText(primitiva.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(primitiva
        .getDate()));

    ViewUtil.applyFavoriteEffect(favorites, primitiva.getName(), layoutView);
    
    fillUpView(layoutView, primitiva);

    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Primitiva primitiva, Context context) {
    View convertView = View.inflate(context, R.layout.primitiva_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, primitiva);

    return convertView;
  }

  private void fillUpView(View view, Primitiva primitiva) {
    String numbers =  primitiva.getNum1() + " " + primitiva.getNum2() + " " + primitiva.getNum3() + " " +
                      primitiva.getNum4() + " " + primitiva.getNum5() + " " + primitiva.getNum6();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtComplementary)).setText(Integer.toString(primitiva.getComplementario()));
    ((TextView) view.findViewById(R.id.txtReinteger)).setText(Integer.toString(primitiva.getReintegro()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.primitiva;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryViewController.LotteryId.PRIMITIVA;
  }

}
