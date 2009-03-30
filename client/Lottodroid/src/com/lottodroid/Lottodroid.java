package com.lottodroid;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.lottodroid.communication.DataFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockDataFetcher;
import com.lottodroid.communication.ServerController;
import com.lottodroid.model.Lottery;

/**
 * Activity for the main screen. 
 */
public class Lottodroid extends ListActivity {
  
  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link DataFetcher}.
   * TODO: Move this flag to the command-line flag list
   */
  private static boolean OFFLINE_MODE = true;
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    try {
      setListAdapter(new MainViewAdapter(this, fetchDataForMainView()));
    } catch (LotteryInfoUnavailableException e) {
      // TODO: como queremos tratar esto?
      Log.e("Lottodroid", "Ouch, no data!", e);
    }
    
  }

  /** Fetches the data that the main view will display: the last results for every lottery type */
  private List<Lottery> fetchDataForMainView() throws LotteryInfoUnavailableException {
    DataFetcher dataFetcher = OFFLINE_MODE ? new MockDataFetcher() : new ServerController();
    return dataFetcher.retrieveLastAllLotteries();
    
    // El details activity hará este tipo de llamadas
    //return ServerController.retrieveLastBonolotos(0, 5);
  }
  
}
