package com.androidsx.lottodroid;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidsx.lottodroid.calendar.CalendarActivity;
import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.sorting.LotterySorter;
import com.androidsx.lottodroid.sorting.LotterySorterFactory;
import com.androidsx.lottodroid.storage.LotteryCompoundDB;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.UserTask;
import com.androidsx.lottodroid.view.AboutDialog;
import com.androidsx.lottodroid.view.CoffeeDialog;
import com.androidsx.lottodroid.view.ErrorDialog;
import com.androidsx.lottodroid.view.LotteryViewController;
import com.androidsx.lottodroid.view.ViewControllerFactory;
import com.flurry.android.FlurryAgent;

/**
 * Activity for the main screen.
 */
public class Lottodroid extends ListActivity {

  public static final String TAG = "Lottodroid";
  
  private static final int ORDER_LOTTERY_MENU_ID = Menu.FIRST;
  private static final int ABOUT_MENU_ID = Menu.FIRST + 1;
  private static final int UPDATE_MENU_ID = Menu.FIRST + 2;
  
  private final LotterySorter sorter = LotterySorterFactory.getLotterySorter(this);
  
  private MainViewAdapter adapter;
  private ListView listView;
  private boolean update = false;
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    this.listView = getListView();
    View v = View.inflate(this, R.layout.lotoluck_row, null);
    v.setOnClickListener(new OnLotoluckListener());
    
    listView.addHeaderView(v);
    fetchDataForMainView();
    new CoffeeDialog(this).show();
    Log.i(TAG, "onCreate");
  }
  
  public void onStart() {
    super.onStart();
    FlurryAgent.onStartSession(this, "9H77QFYE6N7MIWYCWAJG");
  }

  public void onStop() {
    super.onStop();
    FlurryAgent.onEndSession(this);
  }

  /** 
   * Fetches and set the data that the main view will display 
   */
  private void fetchDataForMainView() {
    try {
      new FetchAllLotteryResultsTask().execute();
    } catch (IllegalStateException e) {
      Log.e(TAG, "Fatal error: " + e.getMessage());

      new ErrorDialog(Lottodroid.this, getString(R.string.error_dialog_content)).show();
    }
  }
  
  private class OnLotoluckListener implements View.OnClickListener {

	@Override
	public void onClick(View v) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lotoluck.com"));
		startActivity(browserIntent);
	} 
  }

  /**
   * This method will be called when an item in the list is selected, creating an alert dialog with
   * the options of the lottery type selected.
   */
  @Override
  protected void onListItemClick(ListView l, View v, final int position, long id) {
    super.onListItemClick(l, v, position, id);
    
    String[] options = null;
    
    Lottery lottery = (Lottery) listView.getItemAtPosition(position);
    
    if(lottery == null) { 
    	
    	// when the error_view_row is shown
    	
    	new AlertDialog.Builder(Lottodroid.this).setTitle("Opciones")
		.setItems(new String[] { "Otra fecha" }, 
				new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			if (which == 0) 
    				startCalendarActivity(position);
    		}}).show();
    } else { 
    	
    	// when a normal view is shown - no errors in retrieving data
    
	    if(lottery.getId() == LotteryId.QUINIELA || lottery.getId() == LotteryId.QUINIGOL)
	    	options = new String[] { "Otra fecha", "Ver premios", "Ver goles" };
	    else
	    	options = new String[] { "Otra fecha", "Ver premios" };
	  
	    if(Configuration.SERVER_MODE || Configuration.IN_MEMORY_MODE) {
	    	startDetailsActivity(position);
	    } else {
		    new AlertDialog.Builder(Lottodroid.this).setTitle("Opciones")
		    		.setItems(options, 
		    				new DialogInterface.OnClickListener() {
			    		@Override
			    		public void onClick(DialogInterface dialog, int which) {
			    			// TODO: there is no way that not depend on button positions?
			    			if (which == 0) 
			    				startCalendarActivity(position);
			    			else if (which == 1) 
			    				startPrizeActivity(position);
			    			else if (which == 2)
			    				startScoresActivity(position);
			    		}}).show();
	    }
    }

  }

  /** Creates the menu items */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, ORDER_LOTTERY_MENU_ID, 0, "Ordenar sorteos")
      .setIcon(android.R.drawable.ic_menu_sort_alphabetically);
    menu.add(0, ABOUT_MENU_ID, 0, "Acerca de")
      .setIcon(android.R.drawable.ic_menu_info_details);
    menu.add(0, UPDATE_MENU_ID, 0, "Actualizar")
      .setIcon(android.R.drawable.ic_menu_rotate);
    return true;
  }

  /** Handles item selections */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case ORDER_LOTTERY_MENU_ID:
      // Launch the subactivity to let the user sort the entries
      Intent i = new Intent(this, SortingActivity.class);
      // TODO(pablo): fix the ugly casting!
      i.putExtra(IntentExtraDataNames.SORTER_IN, (Serializable) sorter.getOrder());
      startActivityForResult(i, 0); // We assume there is only one subactivity
      return true;

    case ABOUT_MENU_ID:
      new AboutDialog(this).show();
      return true;
      
    case UPDATE_MENU_ID:
      update = true;
      fetchDataForMainView();
      return true;
      
    }
    return false;
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    Log.i(TAG, "Our subactivity has finished.");

    if (resultCode == Activity.RESULT_CANCELED) { // The user pressed the "Back" button
      Log.i(TAG, "The subactivity did not produce any changes");
    } else { // resultCode is probably RESULT_OK
        Log.i(TAG, "The subactivity has changed the order");
        @SuppressWarnings("unchecked")
		final List<LotteryId> lotteryIds = (List<LotteryId>) intent.getExtras()
          .getSerializable(IntentExtraDataNames.SORTER_OUT);
        sorter.setOrder(lotteryIds);
        adapter.refresh();
        listView.invalidateViews();
        Toast.makeText(this, "Orden guardado con éxito", Toast.LENGTH_SHORT).show();
    }
  }
  
  /** Start the new activity details for the lottery type selected */
  private void startDetailsActivity(int position) {
    Intent i = new Intent(this, DetailsActivity.class);

    Lottery lottery = (Lottery) listView.getItemAtPosition(position);
    
    LotteryViewController<? extends Lottery> viewController = ViewControllerFactory.createViewController(lottery.getId());
    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);

    startActivity(i);
  }

  /** Start the new activity calendar for the lottery type selected */
  private void startCalendarActivity(int position) {
    Intent i = new Intent(this, CalendarActivity.class);

    Lottery lottery = (Lottery) listView.getItemAtPosition(position);
    LotteryId lotteryId;
    
    if(lottery == null)
    	lotteryId = sorter.getOrder().get(position-1);
    else
    	lotteryId = lottery.getId();
    
    LotteryViewController<? extends Lottery> viewController = ViewControllerFactory.createViewController(lotteryId);
    
    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);

    startActivity(i);
  }
  
  /** Start the new activity prize for the lottery type selected */
  private void startPrizeActivity(int position) {
    Intent i = new Intent(this, PrizeActivity.class);

    Lottery lottery = (Lottery) listView.getItemAtPosition(position);
    LotteryViewController<? extends Lottery> viewController = ViewControllerFactory.createViewController(lottery.getId());
    
    // Set to true because we came from Lottodroid
    i.putExtra(Lottodroid.TAG, true);
    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);
    i.putExtra("date", DateFormatter.toLotoluckString(lottery.getDate()));

    startActivity(i);
  }
  
  /** Start the new activity scores for the lottery type selected */
  private void startScoresActivity(int position) {
	Intent i = new Intent(this, ScoresActivity.class);

	Lottery lottery = (Lottery) listView.getItemAtPosition(position);
	    
	LotteryViewController<? extends Lottery> viewController = ViewControllerFactory.createViewController(lottery.getId());
	   
	i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);
	i.putExtra("date", DateFormatter.toLotoluckString(lottery.getDate()));

	startActivity(i);
  }

  /**
   * Task that fetches the data that the main view will display: the last results for every lottery
   * type.
   */
  private class FetchAllLotteryResultsTask extends UserTask<Void, Void, MainViewAdapter> {
	  
	private class Storage implements Runnable {
		
		private final List<Lottery> lotteries;
		
		public Storage(List<Lottery> lotteries) {
			this.lotteries = lotteries;
		}

		@Override
		public void run() {
			LotteryCompoundDB.storeLotteries(Lottodroid.this, lotteries);
		}		
	}

    @Override
    public MainViewAdapter doInBackground(Void... params) {
      MainViewAdapter mainViewAdapter = null;

      try {
    	List<Lottery> lotteries = null;
    	// Try to get the data from the database
    	if(!update)
    		lotteries = LotteryCompoundDB.retrieveLotteries(Lottodroid.this);
    	// If there's no data or it's out of date, fetch it from Internet
    	if(lotteries == null) {
	    	LotteryFetcher dataFetcher = LotteryFetcherFactory.newLotteryFetcher(Lottodroid.this);
	        lotteries = dataFetcher.retrieveLastAllLotteries();
	        new Storage(lotteries).run();
	        update = false;
    	}
        
        mainViewAdapter = new MainViewAdapter(Lottodroid.this, lotteries);
        mainViewAdapter.refresh();

      } catch (LotteryInfoUnavailableException e) {
        Log.e(TAG, "Lottery info unavailable", e);
      }

      return mainViewAdapter;
    }

    @Override
    public void end(MainViewAdapter mainViewAdapter) {
      // Adapter set to null if there is an error or an exception thrown
      if (mainViewAdapter == null) {
        new ErrorDialog(Lottodroid.this, getString(R.string.error_dialog_content)).show();
      } else {
        setListAdapter(mainViewAdapter);
        adapter = mainViewAdapter;
      }
    }

  }
}
