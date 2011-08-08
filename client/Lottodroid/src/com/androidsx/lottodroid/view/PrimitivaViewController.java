package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.util.DateFormatter;

class PrimitivaViewController implements LotteryViewController<Primitiva> {
  
  private static final long serialVersionUID = 4189136947988075144L;
  
  private final String title;

  public PrimitivaViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Primitiva primitiva, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.primitiva_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(primitiva.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(primitiva
        .getDate()));

    fillUpView(layoutView, primitiva);

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
    return LotteryId.PRIMITIVA;
  }

  @Override
  public View createAndFillUpFullView(Primitiva lottery, Context context) {
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
