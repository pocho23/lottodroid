package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class CuponazoOnceViewController implements LotteryViewController<CuponazoOnce> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public CuponazoOnceViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(CuponazoOnce cuponazoOnce, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.cuponazoonce_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(cuponazoOnce.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(cuponazoOnce
        .getDate()));

    fillUpView(layoutView, cuponazoOnce);

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
  public View createAndFillUpDetailsView(CuponazoOnce cuponazoOnce, Context context) {
    View convertView = View.inflate(context, R.layout.cuponazoonce_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, cuponazoOnce);

    return convertView;
  }

  private void fillUpView(View view, CuponazoOnce cuponazoOnce) {
    ((TextView) view.findViewById(R.id.txtNumber)).setText(cuponazoOnce.getNum());
    ((TextView) view.findViewById(R.id.txtSerie)).setText(cuponazoOnce.getSerie());
    ((TextView) view.findViewById(R.id.txtSeriesAdicionales)).setText(cuponazoOnce.getSeriesAdicionales());
  }

  @Override
  public int getIconResource() {
    return R.drawable.once;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.CUPONAZO_ONCE;
  }

  @Override
  public View createAndFillUpPrizeView(CuponazoOnce lottery, Context context) {

	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_once_row, null);
	     
	    // Capitalize the the first letter.
	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index)
	    		.substring(0, 1).toUpperCase() + lottery.getCategoria(index)
	    		.substring(1, lottery.getCategoria(index).length()));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " €");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
