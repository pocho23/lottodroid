package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Loteria7_39;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class Loteria7_39ViewController implements LotteryViewController<Loteria7_39> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public Loteria7_39ViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Loteria7_39 loteria7_39, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.loteria7_39_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(loteria7_39.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(loteria7_39
        .getDate()));

    fillUpView(layoutView, loteria7_39);

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
  public View createAndFillUpDetailsView(Loteria7_39 loteria7_39, Context context) {
    View convertView = View.inflate(context, R.layout.loteria7_39_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, loteria7_39);

    return convertView;
  }

  private void fillUpView(View view, Loteria7_39 loteria7_39) {
    String numbers =  loteria7_39.getNum1() + " " + loteria7_39.getNum2() + " " + loteria7_39.getNum3() + " " +
                      loteria7_39.getNum4() + " " + loteria7_39.getNum5() + " " + loteria7_39.getNum6() + " " +
                      loteria7_39.getNum7();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtReinteger)).setText(Integer.toString(loteria7_39.getReintegro()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.icon;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTERIA7_39;
  }

  @Override
  public View createAndFillUpPrizeView(Loteria7_39 lottery, Context context) {

	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_row, null);
	    
	    ((TextView) layoutView.findViewById(R.id.txtNumAcertantes)).setText("" + lottery.getAcetantes(index));
	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " €");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
