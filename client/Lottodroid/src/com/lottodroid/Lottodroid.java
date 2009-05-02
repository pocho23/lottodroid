package com.lottodroid;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryFetcherFactory;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.model.Lottery;
import com.lottodroid.util.UserTask;
import com.lottodroid.view.AboutDialog;
import com.lottodroid.view.ErrorDialog;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.TouchInterceptor;
import com.lottodroid.view.ViewControllerFactory;

/**
 * Activity for the main screen.
 */
public class Lottodroid extends ListActivity {

  public static final String TAG = "Lottodroid";
  
  private static final int ORDER_LOTTERY_MENU_ID = Menu.FIRST;
  
  private static final int ABOUT_MENU_ID = Menu.FIRST + 1;
  
  private MainViewAdapter adapter;
  
  private ListView listView;
  
  private Menu menu;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    this.listView = getListView();

    fetchDataForMainView();
    
    Log.i(TAG, "onCreate");
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

  /**
   * This method will be called when an item in the list is selected, creating an alert dialog with
   * the options of the lottery type selected.
   */
  @Override
  protected void onListItemClick(ListView l, View v, final int position, long id) {
    super.onListItemClick(l, v, position, id);
    
    // Strange bug: if we set an adapter in Light theme the text is not displayed but with
    // setItems yes, and in default theme happens the other way around
    
    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
    //    android.R.layout.simple_list_item_1, new String[] { "Ver historial" });

    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
    // android.R.layout.simple_list_item_1, new String[] { "Ver historial" });

    new AlertDialog.Builder(Lottodroid.this).setTitle("Opciones").setItems(
        new String[] { "Ver historial" }, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            // TODO: there is no way that not depend on button positions?
            if (which == 0) {
              startDetailsActivity(position);
            }
          }
        }).show();
  }

  /** Check or uncheck order mode 'checkbox' whether some intent has interrupted the activity */
  @Override
  public boolean onPrepareOptionsMenu(final Menu menu) {
    if (adapter != null) {
      if (adapter.getOrderMode()) {
        menu.findItem(ORDER_LOTTERY_MENU_ID).setIcon(android.R.drawable.button_onoff_indicator_on);
      } else {
        menu.findItem(ORDER_LOTTERY_MENU_ID).setIcon(android.R.drawable.button_onoff_indicator_off);
      }
    }
    return super.onPrepareOptionsMenu(menu);
  }

  /** Creates the menu items */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    this.menu = menu;
    menu.add(0, ORDER_LOTTERY_MENU_ID, 0, "Ordenar sorteos").setCheckable(true);

    menu.add(0, ABOUT_MENU_ID, 0, "Acerca de").setIcon(android.R.drawable.ic_menu_info_details);

    return true;
  }

  /** Handles item selections */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case ORDER_LOTTERY_MENU_ID:
      if (adapter != null) {
        if (item.isChecked()) {
          item.setChecked(false).setIcon(android.R.drawable.button_onoff_indicator_off);
          Toast.makeText(this, "Orden guardado con éxito", Toast.LENGTH_SHORT).show();

          setOrderModeAndRepaint(false);
        } else {
          item.setChecked(true).setIcon(android.R.drawable.button_onoff_indicator_on);
          Toast.makeText(this, "Desplaza cada sorteo al lugar deseado", Toast.LENGTH_LONG).show();

          setOrderModeAndRepaint(true);
        }
      }
      return true;

    case ABOUT_MENU_ID:
      new AboutDialog(this).show();
      return true;
    }
    return false;
  }
  
  // TODO(pablo): there has to be a better place where to do the refresh operation
  @Override
  public void onContentChanged() {
    super.onContentChanged();
    if (adapter != null) {
      adapter.refresh();
    }
  }

  /** Listener for the event drag of the order mode */
  private TouchInterceptor.DragListener mDragListener = new TouchInterceptor.DragListener() {
    public void drag(int from, int to) {
      int listSize = adapter.getCount();

      if (from < listSize && to < listSize) {
        adapter.moveItem(from, to);
      }

      adapter.notifyDataSetChanged();
      listView.invalidateViews();
    }
  };

  /** Listener for the event drop of the order mode */
  private TouchInterceptor.DropListener mDropListener = new TouchInterceptor.DropListener() {
    public void drop(int from, int to) {
      listView.invalidateViews();
    }
  };

  /** Changes view behavior depending of the mode (order or normal) */
  private void setOrderModeAndRepaint(boolean orderMode) {
    if (orderMode) {
      // Set the new layout
      setContentView(R.layout.main_order_mode);
      TouchInterceptor touchInterceptor = (TouchInterceptor) getListView();
      this.listView = touchInterceptor;

      // Listener for the save button
      ((Button) findViewById(R.id.button_save_order_mode))
          .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              menu.performIdentifierAction(ORDER_LOTTERY_MENU_ID, 0);
            }
          });

      // Attach the listeners to the listview of this new layout
      touchInterceptor.setDropListener(mDropListener);
      touchInterceptor.setDragListener(mDragListener);
      touchInterceptor.setCacheColorHint(0);
    } else {
      setContentView(R.layout.main);
      this.listView = getListView();
    }

    adapter.setOrderMode(orderMode);
    adapter.notifyDataSetChanged();
    this.listView.invalidateViews();
  }

  /** Start the new activity details for the lottery type selected */
  private void startDetailsActivity(int position) {
    Intent i = new Intent(this, DetailsActivity.class);

    Lottery lottery = (Lottery) listView.getItemAtPosition(position);
    
    @SuppressWarnings("unchecked")
    // TODO(pablo): Can I remove this warning?
    LotteryViewController viewController = ViewControllerFactory.createViewController(lottery.getId());
    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);

    startActivity(i);
  }

  /**
   * Task that fetches the data that the main view will display: the last results for every lottery
   * type.
   */
  private class FetchAllLotteryResultsTask extends UserTask<Void, Void, MainViewAdapter> {

    @Override
    public MainViewAdapter doInBackground(Void... params) {
      MainViewAdapter mainViewAdapter = null;

      try {
        LotteryFetcher dataFetcher = LotteryFetcherFactory.newLotteryFetcher(Lottodroid.this);
        List<Lottery> lotteries = dataFetcher.retrieveLastAllLotteries();
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
