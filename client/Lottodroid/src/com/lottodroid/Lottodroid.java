package com.lottodroid;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.lottodroid.communication.ServerController;
import com.lottodroid.model.Lottery;

/**
 * Activity for the main screen. 
 */
public class Lottodroid extends ListActivity {
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    setListAdapter(new MainViewAdapter(this, fetchDataForMainView()));
  }

  /** Fetches the data that the main view will display: the last results for every lottery type */
  private List<Lottery> fetchDataForMainView() {
    return ServerController.retrieveLastAllLotteries();
    
    // El details activity hará este tipo de llamadas
    //return ServerController.retrieveLastBonolotos(0, 5);
  }
  
}
