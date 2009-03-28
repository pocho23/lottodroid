package com.lottodroid.controller;

import java.util.HashMap;
import java.util.Map;

import com.lottodroid.R;
import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Takes care of choosing an appropriate view for a lottery type, and populating it with data.
 * <p/>
 * It is aware of the different lottery types and the corresponding resources for them.
 */
public class ViewConfigurer {

  /** Maintains the association between lottery types and their corresponding layout files */
  private final static Map<Class<? extends Lottery>, Integer> lotteryToLayout = new HashMap<Class<? extends Lottery>, Integer>();

  static {
    lotteryToLayout.put(Bonoloto.class, R.layout.bonoloto_content_row);
    lotteryToLayout.put(Quiniela.class, R.layout.quiniela_content_row);
  }

  /** Maintains the association between lottery types and their corresponding icons */
  private final static Map<Class<? extends Lottery>, Integer> lotteryToIcon = new HashMap<Class<? extends Lottery>, Integer>();

  static {
    lotteryToIcon.put(Bonoloto.class, R.drawable.bonoloto);
    lotteryToIcon.put(Quiniela.class, R.drawable.quiniela);
  }

  private ViewConfigurer() {
    // Instantiation is not allowed for this class
  }

  public static View createAndConfigureTheCorrespondingView(Context context, Lottery lottery) {

    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);

    // set the corresponding view
    layoutContent.addView(View.inflate(context, lotteryToLayout.get(lottery.getClass()), null));

    // set the icon
    ImageView iconCtrl = (ImageView) layoutView.findViewById(R.id.icon);
    iconCtrl.setImageResource(lotteryToIcon.get(lottery.getClass()));

    // set the title
    TextView titleCtrl = (TextView) layoutView.findViewById(R.id.title);
    titleCtrl.setText(lottery.getName());

    // set date (generic)
    TextView dateCtrl = (TextView) layoutView.findViewById(R.id.date);
    dateCtrl.setText(lottery.getDate().toString());

    if (lottery instanceof Bonoloto) {
      Bonoloto bonoloto = (Bonoloto) lottery;

      TextView numCtrl = (TextView) layoutView.findViewById(R.id.txtNumbers);
      numCtrl.setText(bonoloto.getNumbers());

      TextView complementaryCtrl = (TextView) layoutView.findViewById(R.id.txtComplementary);
      complementaryCtrl.setText(bonoloto.getComplementario());

      TextView reintegerCtrl = (TextView) layoutView.findViewById(R.id.txtReinteger);
      reintegerCtrl.setText(bonoloto.getReintegro());

    } else if (lottery instanceof Quiniela) {
      Quiniela quiniela = (Quiniela) lottery;

      int matchNumber;

      // match 0
      matchNumber = 0;
      TextView numCtrl0 = (TextView) layoutView.findViewById(R.id.txtLocal0);
      numCtrl0.setText(quiniela.getHomeTeam(matchNumber));

      TextView complementaryCtrl0 = (TextView) layoutView.findViewById(R.id.txtVisitant0);
      complementaryCtrl0.setText(quiniela.getAwayTeam(matchNumber));

      TextView reintegerCtrl0 = (TextView) layoutView.findViewById(R.id.txtResult0);
      reintegerCtrl0.setText(quiniela.getResult(matchNumber).toString());

      // match 1
      matchNumber = 1;
      TextView numCtrl1 = (TextView) layoutView.findViewById(R.id.txtLocal1);
      numCtrl1.setText(quiniela.getHomeTeam(matchNumber));

      TextView complementaryCtrl1 = (TextView) layoutView.findViewById(R.id.txtVisitant1);
      complementaryCtrl1.setText(quiniela.getAwayTeam(matchNumber));

      TextView reintegerCtrl1 = (TextView) layoutView.findViewById(R.id.txtResult1);
      reintegerCtrl1.setText(quiniela.getResult(matchNumber).toString());

    } else {
      throw new IllegalArgumentException("Unknown lottery type");
    }

    return layoutView;
  }

}
