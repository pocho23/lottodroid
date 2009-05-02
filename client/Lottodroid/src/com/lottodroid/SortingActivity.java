package com.lottodroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Activity for the screen that allows the user to sort the lottery entries.
 * <p>
 * It receives the list of lottery items from the main view, and returns it sorted in a different
 * way, as long as the user made some changes and click on the <i>save</i> button. Otherwise it just
 * returns the same list it was given.
 */
public class SortingActivity extends ListActivity {

  private ListView listView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    this.listView = getListView();

  }

  
  
}
