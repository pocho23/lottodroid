package com.lottodroid.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottodroid.R;
import com.lottodroid.model.LotteryId;
import com.lottodroid.model.Quinigol;
import com.lottodroid.util.DateFormatter;

class QuinigolViewController implements LotteryViewController<Quinigol> {

  private static final long serialVersionUID = -8774534644802955726L;
  
  private final String title;
  
  /** Maintains the association between quiniela matches and their corresponding layout id's */  
  private final static Map<Integer, Integer> homeTeamsToId = new HashMap<Integer, Integer>();  
  private final static Map<Integer, Integer> awayTeamsToId = new HashMap<Integer, Integer>();  
  private final static Map<Integer, Integer> homeResultsToId = new HashMap<Integer, Integer>();
  private final static Map<Integer, Integer> awayResultsToId = new HashMap<Integer, Integer>();
   
  static {  
    homeTeamsToId.put(0, R.id.txtLocal0);
    homeTeamsToId.put(1, R.id.txtLocal1);
    homeTeamsToId.put(2, R.id.txtLocal2);
    homeTeamsToId.put(3, R.id.txtLocal3);
    homeTeamsToId.put(4, R.id.txtLocal4);
    homeTeamsToId.put(5, R.id.txtLocal5);
    
    awayTeamsToId.put(0, R.id.txtVisitant0);
    awayTeamsToId.put(1, R.id.txtVisitant1);
    awayTeamsToId.put(2, R.id.txtVisitant2);
    awayTeamsToId.put(3, R.id.txtVisitant3);
    awayTeamsToId.put(4, R.id.txtVisitant4);
    awayTeamsToId.put(5, R.id.txtVisitant5);
    
    homeResultsToId.put(0, R.id.txtResultLocal0);
    homeResultsToId.put(1, R.id.txtResultLocal1);
    homeResultsToId.put(2, R.id.txtResultLocal2);
    homeResultsToId.put(3, R.id.txtResultLocal3);
    homeResultsToId.put(4, R.id.txtResultLocal4);
    homeResultsToId.put(5, R.id.txtResultLocal5);
    
    awayResultsToId.put(0, R.id.txtResultVisitant0);
    awayResultsToId.put(1, R.id.txtResultVisitant1);
    awayResultsToId.put(2, R.id.txtResultVisitant2);
    awayResultsToId.put(3, R.id.txtResultVisitant3);
    awayResultsToId.put(4, R.id.txtResultVisitant4);
    awayResultsToId.put(5, R.id.txtResultVisitant5);

   }  

  public QuinigolViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Quinigol quinigol, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.quinigol_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(quinigol.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(quinigol
        .getDate()));

    fillUpView(layoutView, quinigol);

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
  public View createAndFillUpDetailsView(Quinigol quinigol, Context context) {
    View convertView = View.inflate(context, R.layout.quinigol_content_row, null);
    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, quinigol);

    return convertView;
  }

  private void fillUpView(View view, Quinigol quinigol) {
    final int numMatches = Quinigol.NUM_MATCHES;
    
    try {
      for (int i = 0; i < numMatches; i++) {
        ((TextView) view.findViewById(homeTeamsToId.get(i))).setText(quinigol.getHomeTeam(i));
        ((TextView) view.findViewById(awayTeamsToId.get(i))).setText(quinigol.getAwayTeam(i));
        ((TextView) view.findViewById(homeResultsToId.get(i))).setText(quinigol.getHomeResult(i));
        ((TextView) view.findViewById(awayResultsToId.get(i))).setText(quinigol.getAwayResult(i));
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
    return R.drawable.quinigol;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.QUINIGOL;
  }

}
