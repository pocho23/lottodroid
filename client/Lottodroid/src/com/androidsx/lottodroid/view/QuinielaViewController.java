package com.androidsx.lottodroid.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.util.DateFormatter;

class QuinielaViewController implements LotteryViewController<Quiniela> {

  private static final long serialVersionUID = -8774534644802955726L;
  
  private final String title;
  
  /** Maintains the association between quiniela matches and their corresponding layout id's */  
  private final static Map<Integer, Integer> homeTeamsToId = new HashMap<Integer, Integer>();  
  private final static Map<Integer, Integer> awayTeamsToId = new HashMap<Integer, Integer>();  
  private final static Map<Integer, Integer> resultsToId = new HashMap<Integer, Integer>();
   
  static {  
    homeTeamsToId.put(0, R.id.txtLocal0);
    homeTeamsToId.put(1, R.id.txtLocal1);
    homeTeamsToId.put(2, R.id.txtLocal2);
    homeTeamsToId.put(3, R.id.txtLocal3);
    homeTeamsToId.put(4, R.id.txtLocal4);
    homeTeamsToId.put(5, R.id.txtLocal5);
    homeTeamsToId.put(6, R.id.txtLocal6);
    homeTeamsToId.put(7, R.id.txtLocal7);
    homeTeamsToId.put(8, R.id.txtLocal8);
    homeTeamsToId.put(9, R.id.txtLocal9);
    homeTeamsToId.put(10, R.id.txtLocal10);
    homeTeamsToId.put(11, R.id.txtLocal11);
    homeTeamsToId.put(12, R.id.txtLocal12);
    homeTeamsToId.put(13, R.id.txtLocal13);
    homeTeamsToId.put(14, R.id.txtLocal14);
    
    awayTeamsToId.put(0, R.id.txtVisitant0);
    awayTeamsToId.put(1, R.id.txtVisitant1);
    awayTeamsToId.put(2, R.id.txtVisitant2);
    awayTeamsToId.put(3, R.id.txtVisitant3);
    awayTeamsToId.put(4, R.id.txtVisitant4);
    awayTeamsToId.put(5, R.id.txtVisitant5);
    awayTeamsToId.put(6, R.id.txtVisitant6);
    awayTeamsToId.put(7, R.id.txtVisitant7);
    awayTeamsToId.put(8, R.id.txtVisitant8);
    awayTeamsToId.put(9, R.id.txtVisitant9);
    awayTeamsToId.put(10, R.id.txtVisitant10);
    awayTeamsToId.put(11, R.id.txtVisitant11);
    awayTeamsToId.put(12, R.id.txtVisitant12);
    awayTeamsToId.put(13, R.id.txtVisitant13);
    awayTeamsToId.put(14, R.id.txtVisitant14);
    
    resultsToId.put(0, R.id.txtResult0);
    resultsToId.put(1, R.id.txtResult1);
    resultsToId.put(2, R.id.txtResult2);
    resultsToId.put(3, R.id.txtResult3);
    resultsToId.put(4, R.id.txtResult4);
    resultsToId.put(5, R.id.txtResult5);
    resultsToId.put(6, R.id.txtResult6);
    resultsToId.put(7, R.id.txtResult7);
    resultsToId.put(8, R.id.txtResult8);
    resultsToId.put(9, R.id.txtResult9);
    resultsToId.put(10, R.id.txtResult10);
    resultsToId.put(11, R.id.txtResult11);
    resultsToId.put(12, R.id.txtResult12);
    resultsToId.put(13, R.id.txtResult13);
    resultsToId.put(14, R.id.txtResult14);
   }  

  public QuinielaViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Quiniela quiniela, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.quiniela_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(quiniela.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(quiniela
        .getDate()));

    fillUpView(layoutView, quiniela);

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
  public View createAndFillUpDetailsView(Quiniela quiniela, Context context) {
    View convertView = View.inflate(context, R.layout.quiniela_content_row, null);
    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, quiniela);

    return convertView;
  }

  private void fillUpView(View view, Quiniela quiniela) {
    final int numMatches = Quiniela.NUM_MATCHES;
    
    try {
      for (int i = 0; i < numMatches; i++) {
        ((TextView) view.findViewById(homeTeamsToId.get(i))).setText(quiniela.getHomeTeam(i));
        ((TextView) view.findViewById(awayTeamsToId.get(i))).setText(quiniela.getAwayTeam(i));
        ((TextView) view.findViewById(resultsToId.get(i))).setText(quiniela.getResult(i));
      }
    } catch (Exception e) {
      //TODO: handle error displaying results on UI. 
      
      // This must be a warning because only one lottery type is wrong, not 
      // all of them, but if we display an alert, it is show each time the platform repaints the screen.
      // At the moment the default message on textviews 'OOPS' is displayed instead the real results.
      //new WarningDialog(context, quiniela.getName()).show();
    }
  }

  @Override
  public int getIconResource() {
    return R.drawable.quiniela;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.QUINIELA;
  }

}
