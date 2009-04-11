package com.lottodroid;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockLotteryFetcher;
import com.lottodroid.communication.ServerLotteryFetcher;
import com.lottodroid.util.UserTask;

/**
 * Activity for the main screen.
 */
public class Lottodroid extends ListActivity {

  public static final String TAG = Lottodroid.class.toString();

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link LotteryFetcher}.
   * 
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
    }
  }

  /**
   * This method will be called when an item in the list is selected, starting a new activity with
   * all the details of the lottery type selected.
   */
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);

    Intent i = new Intent(this, DetailsActivity.class);
    startActivity(i);
  }

  /**
   * Task that fetches the data that the main view will display: the last results for every lottery
   * type.
   */
  private class FetchAllLotteryResultsTask extends UserTask<Void, Void, MainViewAdapter> {

    public MainViewAdapter doInBackground(Void... params) {
      LotteryFetcher dataFetcher = OFFLINE_MODE ? 
                                              new MockLotteryFetcher()
                                            : new ServerLotteryFetcher();
      try {
        // Just to see the behavior
        Thread.sleep(2000);

        return new MainViewAdapter(Lottodroid.this, dataFetcher.retrieveLastAllLotteries());
        
      } catch (LotteryInfoUnavailableException e) {
        Log.e(TAG, "Lottery info unavailable", e);
      } catch (InterruptedException e) {
        ;
      } 
      
      return null;
    }

    @Override
    public void end(MainViewAdapter adapter) {
      // Adapter set to null if there is an error or an exception thrown
      adapter = null;
      if (adapter == null) {
        OnClickListener retryListener = new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            fetchDataForMainView();
          }
        };

        OnClickListener exitListener = new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Lottodroid.this.finish();
          }
        };

        new AlertDialog.Builder(Lottodroid.this)
          .setTitle("Error")
          .setMessage("No se han podido encontrar los resultados de los últimos sorteos")
          .setCancelable(false)
          .setPositiveButton("Reintentar", retryListener)
          .setNegativeButton("Salir", exitListener)
          .show();
        
      } else {
        setListAdapter(adapter);
      }
    }

  }
}
