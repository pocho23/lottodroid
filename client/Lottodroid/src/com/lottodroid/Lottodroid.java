package com.lottodroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockLotteryFetcher;
import com.lottodroid.communication.ServerLotteryFetcher;
import com.lottodroid.model.Lottery;
import com.lottodroid.util.UserTask;
import com.lottodroid.view.ErrorDialog;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.ViewControllerFactory;

/**
 * Activity for the main screen.
 */
public class Lottodroid extends ListActivity {

  public static final String TAG = Lottodroid.class.toString();
  private static final int VIEW_DETAILS_MENU_ID = Menu.FIRST;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    registerForContextMenu(getListView());
    fetchDataForMainView();
  }

  /** 
   * Fetches and set the data that the main view will display 
   */
  private void fetchDataForMainView() {
    try {
      new FetchAllLotteryResultsTask().execute();
    } catch (IllegalStateException e) {
      Log.e(TAG, "Fatal error: " + e.getMessage());

      new ErrorDialog(Lottodroid.this,  "No se han podido encontrar los " +
                                        "resultados de los últimos sorteos").show();
    }
  }

  /**
   * This method will be called when an item in the list is selected, starting a new activity with
   * all the details of the lottery type selected.
   */
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    startDetailsActivity(position);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, VIEW_DETAILS_MENU_ID , 0, R.string.context_menu_view_details);
  }
  
  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch(item.getItemId()) {
      case VIEW_DETAILS_MENU_ID:
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        startDetailsActivity(info.position);
        return true;
    }
    return super.onContextItemSelected(item);
  }
  
  private void startDetailsActivity(int position) {
    Intent i = new Intent(this, DetailsActivity.class);

    Lottery lottery = (Lottery) getListView().getItemAtPosition(position);

    @SuppressWarnings("unchecked")
    // TODO(pablo): Can I remove this warning?
    LotteryViewController viewController = ViewControllerFactory.createViewController(lottery);
    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);

    startActivity(i);
  }
  
  /**
   * Task that fetches the data that the main view will display: the last results for every lottery
   * type.
   */
  private class FetchAllLotteryResultsTask extends UserTask<Void, Void, MainViewAdapter> {

    @Override
    public MainViewAdapter doInBackground(Void... params)  {
      LotteryFetcher dataFetcher = Configuration.OFFLINE_MODE ? 
                                              new MockLotteryFetcher()
                                            : new ServerLotteryFetcher();
      try {
        return new MainViewAdapter(Lottodroid.this, dataFetcher.retrieveLastAllLotteries());
        
      } catch (LotteryInfoUnavailableException e) {
        Log.e(TAG, "Lottery info unavailable", e);
      } 
      
      return null;
    }

    @Override
    public void end(MainViewAdapter adapter) {
      // Adapter set to null if there is an error or an exception thrown
      if (adapter == null) {
        new ErrorDialog(Lottodroid.this,  "No se han podido encontrar los " +
        		                              "resultados de los últimos sorteos").show();
      } else {
        setListAdapter(adapter);
      }
    }

  }
}
