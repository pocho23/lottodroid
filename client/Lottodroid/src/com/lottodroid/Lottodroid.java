package com.lottodroid;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class Lottodroid extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
       super.onCreate(icicle);
       setContentView(R.layout.main);
       
       /* Sample data */
       ArrayList<IDraw> drawList = new ArrayList<IDraw>();
       Bonoloto bonoloto = new Bonoloto( "Domingo, 01/02/2008");
       Lototurf lototurf = new Lototurf( "Domingo, 23/02/2008");
       Bonoloto bonoloto1 = new Bonoloto( "Domingo, 32/02/2008");
       
       drawList.add(bonoloto);
       drawList.add(lototurf);
       drawList.add(bonoloto1);
       
       setListAdapter(new DrawAdapter( this, drawList ));
    }
}