package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.QuintuplePlus;
import com.androidsx.lottodroid.util.DateFormatter;

class QuintuplePlusViewController implements LotteryViewController<QuintuplePlus> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public QuintuplePlusViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(QuintuplePlus quintuplePlus, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.quintupleplus_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(quintuplePlus.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(quintuplePlus
        .getDate()));

    fillUpView(layoutView, quintuplePlus);

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
  public View createAndFillUpDetailsView(QuintuplePlus quintuplePlus, Context context) {
    View convertView = View.inflate(context, R.layout.quintupleplus_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, quintuplePlus);

    return convertView;
  }

  private void fillUpView(View view, QuintuplePlus quintuplePlus) {
    ((TextView) view.findViewById(R.id.txtRace1)).setText(Integer.toString(quintuplePlus.getRace1()));
    ((TextView) view.findViewById(R.id.txtRace2)).setText(Integer.toString(quintuplePlus.getRace2()));
    ((TextView) view.findViewById(R.id.txtRace3)).setText(Integer.toString(quintuplePlus.getRace3()));
    ((TextView) view.findViewById(R.id.txtRace4)).setText(Integer.toString(quintuplePlus.getRace4()));
    ((TextView) view.findViewById(R.id.txtRace5)).setText(Integer.toString(quintuplePlus.getRace5()));
    ((TextView) view.findViewById(R.id.txtRace5_2)).setText(Integer.toString(quintuplePlus.getRace5_2()));
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
    return LotteryId.QUINTUPLE_PLUS;
  }

  @Override
  public View createAndFillUpPrizeView(QuintuplePlus lottery, Context context) {

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
