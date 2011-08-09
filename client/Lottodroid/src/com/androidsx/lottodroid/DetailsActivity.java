package com.androidsx.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.UserTask;
import com.androidsx.lottodroid.view.ErrorDialog;
import com.androidsx.lottodroid.view.LotteryViewController;

/**
 * Activity for the details screen.
 */
public class DetailsActivity extends ExpandableListActivity {

  public static final String TAG = DetailsActivity.class.toString();
  
  /** Number of last results displayed by default */
  private static int NUM_RESULTS_SHOW = 15;
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    try {
      Bundle extras = getIntent().getExtras();
      if (extras == null) {
        throw new DataFormatException();
      }
      
      // Get the view controller, set from the main activity
      @SuppressWarnings("unchecked")
      LotteryViewController<? extends Lottery> viewController = (LotteryViewController<? extends Lottery>) extras
          .getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);
      
      // set the icon and title
      ImageView iconCtrl = (ImageView) findViewById(R.id.icon);
      TextView titleCtrl = (TextView) findViewById(R.id.title);
      
      titleCtrl.setText(viewController.getTitle());
      iconCtrl.setImageResource(viewController.getIconResource());
      
      fetchDataForDetailsView(viewController);

    } catch (DataFormatException e) {
      Log.e(TAG, "Errors passing data from main activity", e);
    } catch (IllegalStateException e) {
      Log.e(TAG, "Fatal error: " + e.getMessage());
    }
  }
  
  /** 
   * Fetches and set the data that the details view will display 
   */
  // TODO: really strange this warning...
  @SuppressWarnings("unchecked")
  private void fetchDataForDetailsView(LotteryViewController viewController) 
    throws IllegalStateException {
      new FetchSpecificLotteryResultsTask().execute(viewController);
  }
  
  /**
   * Task that fetches the data that the details view will display: the last results 
   * for the lottery type selected on the main activity.
   */
  private class FetchSpecificLotteryResultsTask extends UserTask<LotteryViewController<Lottery>, Void, DetailsViewAdapter> {

    @Override
    public DetailsViewAdapter doInBackground(LotteryViewController<Lottery>... params)  {
      LotteryViewController<Lottery> viewController =  params[0];
      LotteryId lotteryId = viewController.getId();
      DetailsViewAdapter detailsViewAdapter = null;
      
      try {
        LotteryFetcher dataFetcher = LotteryFetcherFactory.newLotteryFetcher(DetailsActivity.this);
        List<? extends Lottery> listLottery;       

        // TODO(pablo): can we get rid of this big "if" clause?
        if (lotteryId == LotteryId.BONOLOTO) {
          listLottery = dataFetcher.retrieveLastBonolotos(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.QUINIELA) {
          listLottery = dataFetcher.retrieveLastQuinielas(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.PRIMITIVA) {
          listLottery = dataFetcher.retrieveLastPrimitivas(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.QUINIGOL) {
          listLottery = dataFetcher.retrieveLastQuinigoles(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.LOTOTURF) {
          listLottery = dataFetcher.retrieveLastLototurfs(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.EUROMILLON) {
          listLottery = dataFetcher.retrieveLastEuromillones(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.LOTERIA_NACIONAL) {
          listLottery = dataFetcher.retrieveLastLoteriasNacionales(0, NUM_RESULTS_SHOW);
        } else if (lotteryId == LotteryId.GORDO_PRIMITIVA) {
          listLottery = dataFetcher.retrieveLastGordoPrimitivas(0, NUM_RESULTS_SHOW);
        } else {
          // TODO(pablo): check this exception handling
          throw new DataFormatException();
        }
        
        // The adapter is created on a non-UI thread
        detailsViewAdapter = new DetailsViewAdapter(DetailsActivity.this, listLottery, viewController);
        
      } catch (LotteryInfoUnavailableException e) {
        Log.e(TAG, "Lottery info unavailable", e);
      } catch (DataFormatException e) {
        Log.e(TAG, "Inconsistent data fetched from the main activity", e);
      } 
      
      return detailsViewAdapter;
    }

    @Override
    public void end(DetailsViewAdapter adapter) {      
       // Adapter set to null if there is an error or an exception thrown
      if (adapter == null) {     
        new ErrorDialog(DetailsActivity.this, getString(R.string.error_dialog_content)).show();
      } else {
        setListAdapter(adapter);
        
        // Expand the last lottery result
        if (getExpandableListAdapter().getGroupCount() > 0)
          getExpandableListView().expandGroup(0);
      }
    }

  }

}
