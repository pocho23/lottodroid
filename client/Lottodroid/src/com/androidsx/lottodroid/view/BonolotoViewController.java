package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class BonolotoViewController implements LotteryViewController<Bonoloto> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public BonolotoViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Bonoloto bonoloto, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.bonoloto_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(bonoloto.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(bonoloto
        .getDate()));

    fillUpView(layoutView, bonoloto);

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
  public View createAndFillUpDetailsView(Bonoloto bonoloto, Context context) {
    View convertView = View.inflate(context, R.layout.bonoloto_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, bonoloto);

    return convertView;
  }

  private void fillUpView(View view, Bonoloto bonoloto) {
    String numbers =  bonoloto.getNum1() + " " + bonoloto.getNum2() + " " + bonoloto.getNum3() + " " +
                      bonoloto.getNum4() + " " + bonoloto.getNum5() + " " + bonoloto.getNum6();
    
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView) view.findViewById(R.id.txtComplementary)).setText(Integer.toString(bonoloto.getComplementario()));
    ((TextView) view.findViewById(R.id.txtReinteger)).setText(Integer.toString(bonoloto.getReintegro()));
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
    return LotteryId.BONOLOTO;
  }

  @Override
  public View createAndFillUpFullView(Bonoloto lottery, Context context) {

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
