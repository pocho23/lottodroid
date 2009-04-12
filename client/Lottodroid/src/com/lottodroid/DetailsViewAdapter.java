package com.lottodroid;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Adapter for the details view: maintains the association between the list of results
 * of a specific lottery type and their view.
 * 
 * @extends  BaseExpandableListAdapter
 */
public class DetailsViewAdapter extends BaseExpandableListAdapter {
  private List<? extends Lottery> listLottery;
  private Context context;

  public DetailsViewAdapter(Context context, List<? extends Lottery> listLottery) {
    this.context = context;
    this.listLottery = listLottery;
  }

  public Object getChild(int groupPosition, int childPosition) {
    return listLottery.get(groupPosition);
  }

  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  public int getChildrenCount(int groupPosition) {
    // There will be only one child per group
    return 1;
  }

  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
      View convertView, ViewGroup parent) {

    Lottery lottery = (Lottery) getChild(groupPosition, childPosition);
    
    if (lottery instanceof Bonoloto) {
      convertView = View.inflate(context, R.layout.bonoloto_content_row, null);
      
      convertView.setPadding(20, 5, 0, 5);
      convertView.setBackgroundColor(Color.parseColor("#323232"));
      
      Bonoloto bonoloto = (Bonoloto) lottery;

      TextView numCtrl = (TextView) convertView.findViewById(R.id.txtNumbers);
      numCtrl.setText(bonoloto.getNumbers());

      TextView complementaryCtrl = (TextView) convertView.findViewById(R.id.txtComplementary);
      complementaryCtrl.setText(bonoloto.getComplementario());

      TextView reintegerCtrl = (TextView) convertView.findViewById(R.id.txtReinteger);
      reintegerCtrl.setText(bonoloto.getReintegro());

    } else if (lottery instanceof Quiniela) {
      convertView = View.inflate(context, R.layout.quiniela_content_row, null);
      
      Quiniela quiniela = (Quiniela) lottery;
      
      convertView.setPadding(20, 5, 0, 5);
      convertView.setBackgroundColor(Color.parseColor("#323232"));

      int matchNumber;

      // match 0
      matchNumber = 0;
      TextView numCtrl0 = (TextView) convertView.findViewById(R.id.txtLocal0);
      numCtrl0.setText(quiniela.getHomeTeam(matchNumber));

      TextView complementaryCtrl0 = (TextView) convertView.findViewById(R.id.txtVisitant0);
      complementaryCtrl0.setText(quiniela.getAwayTeam(matchNumber));

      TextView reintegerCtrl0 = (TextView) convertView.findViewById(R.id.txtResult0);
      reintegerCtrl0.setText(quiniela.getResult(matchNumber).toString());

      // match 1
      matchNumber = 1;
      TextView numCtrl1 = (TextView) convertView.findViewById(R.id.txtLocal1);
      numCtrl1.setText(quiniela.getHomeTeam(matchNumber));

      TextView complementaryCtrl1 = (TextView) convertView.findViewById(R.id.txtVisitant1);
      complementaryCtrl1.setText(quiniela.getAwayTeam(matchNumber));

      TextView reintegerCtrl1 = (TextView) convertView.findViewById(R.id.txtResult1);
      reintegerCtrl1.setText(quiniela.getResult(matchNumber).toString());

    } 

    return convertView;
  }

  public Object getGroup(int groupPosition) {
    return listLottery.get(groupPosition);
  }

  public int getGroupCount() {
    return listLottery.size();
  }

  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

    // Reuse the inflated view if possible
    if (convertView == null)
      convertView = View.inflate(context, R.layout.details_group_row, null);

    Date date = ((Lottery) getGroup(groupPosition)).getDate();
    
    DateFormatSymbols symbols = new DateFormatSymbols();

    String[] daysES = {
          "", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
    symbols.setWeekdays(daysES);
    
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d/MM/yyyy", symbols);
    String result = formatter.format(date);
 
    
    ((TextView) convertView.findViewById(R.id.date)).setText(result);

    return convertView;
  }

  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }

  public boolean hasStableIds() {
    return true;
  }

}