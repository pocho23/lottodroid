package com.androidsx.lottodroid.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.FootballLotery;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.util.DateFormatter;

public class QuinielaScoresView {

  public View createAndFillUpScoresView(Lottery lottery, Context context) {
	
	FootballLotery footballLotery = null;
	
	if(lottery.getId() == LotteryId.QUINIELA)
		footballLotery = (Quiniela) lottery;
	else if(lottery.getId() == LotteryId.QUINIGOL)
		footballLotery = (Quinigol) lottery;
	  
	  
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.quiniela);
    ((TextView) layoutView.findViewById(R.id.title)).setText(footballLotery.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(footballLotery
        .getDate()));
    
    View premioRow;
    
    final int numMatches = footballLotery.getNumMatches();
    
    for (int i = 0; i < numMatches; i++) {
	  
		premioRow =  View.inflate(context, R.layout.scores_row, null);
				
	    ((TextView) premioRow.findViewById(R.id.txtLocal)).setText(footballLotery.getHomeTeam(i));
	    ((TextView) premioRow.findViewById(R.id.txtLocalResult)).setText(""+footballLotery.getHomeScore(i));
	    ((TextView) premioRow.findViewById(R.id.txtVisitant)).setText(footballLotery.getAwayTeam(i));
	    ((TextView) premioRow.findViewById(R.id.txtVisitantResult)).setText(""+footballLotery.getAwayScore(i));
	    
	    layoutContent.addView(premioRow);
    }
    
    return layoutView;
  }
}
