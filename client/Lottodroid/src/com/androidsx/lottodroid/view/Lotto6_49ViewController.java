package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.util.DateFormatter;

class Lotto6_49ViewController implements LotteryViewController<Lotto6_49> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public Lotto6_49ViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Lotto6_49 lotto6_49, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.lotto6_49_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(lotto6_49.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(lotto6_49
        .getDate()));

    fillUpView(layoutView, lotto6_49);

    return layoutView;
  }
  
  @Override
  public View createAndFillUpOrderView(LotteryId lotteryId, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    
    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(lotteryId.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText("");
    
    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Lotto6_49 lotto6_49, Context context) {
    View convertView = View.inflate(context, R.layout.lotto6_49_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, lotto6_49);

    return convertView;
  }

  private void fillUpView(View view, Lotto6_49 lotto6_49) {
    String numbers =  lotto6_49.getNum1() + " " + lotto6_49.getNum2() + " " + lotto6_49.getNum3() + " " +
    			      lotto6_49.getNum4() + " " + lotto6_49.getNum5() + " " + lotto6_49.getNum6();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtComplementary)).setText(Integer.toString(lotto6_49.getComplementario()));
    ((TextView) view.findViewById(R.id.txtReinteger)).setText(Integer.toString(lotto6_49.getReintegro()));
    ((TextView) view.findViewById(R.id.txtJoker)).setText(Long.toString(lotto6_49.getJoker()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.lotocatalunya;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTTO6_49;
  }

  @Override
  public View createAndFillUpPrizeView(Lotto6_49 lottery, Context context) {

	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_row, null);
	    
	    ((TextView) layoutView.findViewById(R.id.txtNumAcertantes)).setText("" + lottery.getAcetantes(index));
	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " \u20AC");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
