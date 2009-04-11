package com.lottodroid;

import java.util.List;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockLotteryFetcher;
import com.lottodroid.communication.ServerLotteryFetcher;
import com.lottodroid.model.Lottery;

/**
 * Activity for the details screen.
 */
public class DetailsActivity extends ExpandableListActivity {
  
  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link LotteryFetcher}.
   * TODO: Move this flag to the command-line flag list
   */
  private static boolean OFFLINE_MODE = true;
  
  /** Number of last results displayed by default */
  private static int NUM_RESULTS_SHOW = 4;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    try {
      setListAdapter(new DetailsViewAdapter(this, fetchDataForDetailsView()));
      
      // Expand the last lottery result
      if ( getExpandableListAdapter().getGroupCount() > 0 )
        getExpandableListView().expandGroup(0);
      
    } catch (LotteryInfoUnavailableException e) {
      Log.e("Lottodroid", "Ouch, no data!", e);
    }   
  }

  /** Fetches the data that the details view will display: the last results for a specify lottery type*/
  private  List<? extends Lottery> fetchDataForDetailsView() throws LotteryInfoUnavailableException {
    LotteryFetcher dataFetcher = OFFLINE_MODE ? new MockLotteryFetcher() : new ServerLotteryFetcher();
    
    return dataFetcher.retrieveLastBonolotos(0, NUM_RESULTS_SHOW);
  }
  
  
  
}
