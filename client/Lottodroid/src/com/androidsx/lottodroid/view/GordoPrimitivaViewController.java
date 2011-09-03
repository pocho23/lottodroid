package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class GordoPrimitivaViewController implements LotteryViewController<GordoPrimitiva> {

  private static final long serialVersionUID = -3849551523505137857L;

  private final String title;

  public GordoPrimitivaViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(GordoPrimitiva gordoPrimitiva, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.gordo_primitiva_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(gordoPrimitiva.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(gordoPrimitiva
        .getDate()));

    fillUpView(layoutView, gordoPrimitiva);

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
  public View createAndFillUpDetailsView(GordoPrimitiva gordoPrimitiva, Context context) {
    View convertView = View.inflate(context, R.layout.gordo_primitiva_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, gordoPrimitiva);

    return convertView;
  }

  private void fillUpView(View view, GordoPrimitiva gordoPrimitiva) {
    String numbers =  gordoPrimitiva.getNum1() + " " + gordoPrimitiva.getNum2() + " " + gordoPrimitiva.getNum3() + " " +
      gordoPrimitiva.getNum4() + " " + gordoPrimitiva.getNum5();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtReinteger)).setText(Integer.toString(gordoPrimitiva.getReintegro()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.gordoprimitiva; 
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.GORDO_PRIMITIVA;
  }

  @Override
  public View createAndFillUpPrizeView(GordoPrimitiva lottery, Context context) {
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
