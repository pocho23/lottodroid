package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class LoteriaNacionalViewController implements LotteryViewController<LoteriaNacional> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public LoteriaNacionalViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(LoteriaNacional loteriaNacional, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.loterianacional_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(loteriaNacional.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(loteriaNacional
        .getDate()));

    fillUpView(layoutView, loteriaNacional);

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
  public View createAndFillUpDetailsView(LoteriaNacional loteriaNacional, Context context) {
    View convertView = View.inflate(context, R.layout.loterianacional_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, loteriaNacional);

    return convertView;
  }

  private void fillUpView(View view, LoteriaNacional loterianacional) {
    ((TextView) view.findViewById(R.id.txtPremio1)).setText(Integer.toString(loterianacional
        .getPremio1()));
    ((TextView) view.findViewById(R.id.txtPremio2)).setText(Integer.toString(loterianacional
        .getPremio2()));
    ((TextView) view.findViewById(R.id.txtFraccion)).setText(Integer.toString(loterianacional
        .getFraccion()));
    ((TextView) view.findViewById(R.id.txtSerie)).setText(Integer.toString(loterianacional
        .getSerie()));
    ((TextView) view.findViewById(R.id.txtReintegro1)).setText(Integer.toString(loterianacional
        .getReintegro1()));
    ((TextView) view.findViewById(R.id.txtReintegro2)).setText(Integer.toString(loterianacional
        .getReintegro2()));
    ((TextView) view.findViewById(R.id.txtReintegro3)).setText(Integer.toString(loterianacional
        .getReintegro3()));
  }

  @Override
  public int getIconResource() {
    return R.drawable.loterianacional;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTERIA_NACIONAL;
  }

  @Override
  public View createAndFillUpPrizeView(LoteriaNacional lottery, Context context) {
	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_row, null);
	    
	    ((TextView) layoutView.findViewById(R.id.txtAct)).setVisibility(View.GONE);
	    ((TextView) layoutView.findViewById(R.id.txtNumAcertantes)).setVisibility(View.GONE);
	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " €");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
