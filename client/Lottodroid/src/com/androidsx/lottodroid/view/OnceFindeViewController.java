package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.OnceFinde;
import com.androidsx.lottodroid.util.DateFormatter;

class OnceFindeViewController implements LotteryViewController<OnceFinde> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public OnceFindeViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(OnceFinde onceFinde, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.oncefinde_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(onceFinde.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(onceFinde
        .getDate()));

    fillUpView(layoutView, onceFinde);

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
  public View createAndFillUpDetailsView(OnceFinde onceFinde, Context context) {
    View convertView = View.inflate(context, R.layout.oncefinde_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, onceFinde);

    return convertView;
  }

  private void fillUpView(View view, OnceFinde onceFinde) {
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(onceFinde.getNum());
    ((TextView) view.findViewById(R.id.txtSerie)).setText(onceFinde.getSerie());
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
    return LotteryId.ONCE_FINDE;
  }

  @Override
  public View createAndFillUpPrizeView(OnceFinde lottery, Context context) {

	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_once_row, null);

	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " €");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
