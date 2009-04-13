package com.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lottodroid.communication.LotteryFetcher;
import com.lottodroid.communication.LotteryInfoUnavailableException;
import com.lottodroid.communication.MockLotteryFetcher;
import com.lottodroid.communication.ServerLotteryFetcher;
import com.lottodroid.model.Lottery;
import com.lottodroid.view.LotteryViewController;
import com.lottodroid.view.LotteryViewController.LotteryId;

/**
 * Activity for the details screen.
 */
public class DetailsActivity extends ExpandableListActivity {

  /**
   * If the offline mode is true, no communication with the server will be performed: mock data will
   * be generated instead. See the implementations for {@link LotteryFetcher}. 
   * 
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
      Bundle extras = getIntent().getExtras();
      if (extras == null) {
        throw new DataFormatException();
      }
      
      // Get the view controller, set from the main activity
      // TODO(pablo): Don't use getSerializable here, there has to be something better!
      @SuppressWarnings("unchecked")
      LotteryViewController<Lottery> viewController = (LotteryViewController) extras
          .getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);
      
      // set the icon and title
      ImageView iconCtrl = (ImageView) findViewById(R.id.icon);
      TextView titleCtrl = (TextView) findViewById(R.id.title);
      
      titleCtrl.setText(viewController.getTitle());
      iconCtrl.setImageResource(viewController.getIconResource());
      
      // this line will be placed on the callback of another thread
      setListAdapter(new DetailsViewAdapter(this, fetchDataForDetailsView(viewController.getId()), viewController));

      // Expand the last lottery result
      if (getExpandableListAdapter().getGroupCount() > 0)
        getExpandableListView().expandGroup(0);

    } catch (LotteryInfoUnavailableException e) {
      Log.e("Lottodroid", "Ouch, no data!", e);
    } catch (DataFormatException e) {
      Log.e("Lottodroid", "Errors passing data from main activity", e);
    }
  }

  /**
   * Fetches the data that the details view will display: the last results for a specify lottery
   * type
   */
  private List<? extends Lottery> fetchDataForDetailsView(LotteryId lotteryId)
      throws LotteryInfoUnavailableException, DataFormatException {
    LotteryFetcher dataFetcher = OFFLINE_MODE ? 
                                  new MockLotteryFetcher()
                                : new ServerLotteryFetcher();
    List<? extends Lottery> listLottery;
    
    // TODO(pablo): can we get rid of this big "if" clause?
    if (lotteryId == LotteryViewController.LotteryId.BONOLOTO) {
      listLottery = dataFetcher.retrieveLastBonolotos(0, NUM_RESULTS_SHOW);
    } else if (lotteryId == LotteryViewController.LotteryId.QUINIELA) {
      listLottery = dataFetcher.retrieveLastQuinielas(0, NUM_RESULTS_SHOW);
    } else {
      // TODO(pablo): check this exception handling
      throw new DataFormatException();
    }

    return listLottery;
  }

}
