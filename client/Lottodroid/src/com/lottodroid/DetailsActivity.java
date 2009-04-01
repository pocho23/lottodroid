package com.lottodroid;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;

import com.lottodroid.communication.LotteryFetcher;

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
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    // Set up our adapter
    BaseExpandableListAdapter mAdapter = new DetailsViewAdapter(this);
    setListAdapter(mAdapter);    
  }

  
}
