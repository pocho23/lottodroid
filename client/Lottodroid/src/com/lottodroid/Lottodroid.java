package com.lottodroid;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

/**
 * Activity for the main screen. 
 */
public class Lottodroid extends ListActivity {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.main);

    /* Sample data */
    ArrayList<IDraw> drawList = new ArrayList<IDraw>();
    drawList.add(new Bonoloto("Domingo, 01/02/2008"));
    drawList.add(new Lototurf("Domingo, 23/02/2008"));
    drawList.add(new Bonoloto("Domingo, 32/02/2008"));

    setListAdapter(new DrawAdapter(this, drawList));
  }
}
