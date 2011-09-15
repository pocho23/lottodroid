package com.androidsx.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.storage.LotteryDBFactory;
import com.androidsx.lottodroid.util.UserTask;
import com.androidsx.lottodroid.view.ErrorDialog;
import com.androidsx.lottodroid.view.LotteryViewController;

public class PrizeActivity extends Activity {

	public static final String TAG = PrizeActivity.class.toString();
	private LinearLayout fullView;
	private long date;
	private LotteryViewController<Lottery> viewController;
	private Context mContext;
	private boolean stored = false;
	private static final int UPDATE_MENU_ID = Menu.FIRST;
    private boolean update = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.prize_view);
		fullView = (LinearLayout) findViewById(R.id.prize);
		mContext = this;

		try {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				throw new DataFormatException();
			}
			
			// If we came from Lottodroid it's already stored, if not let's search!
			stored = extras.getBoolean(Lottodroid.TAG);
			
			// Get the date, set from the main activity
			date = (Long) extras.getSerializable("date");

			// Get the view controller, set from the main activity
			viewController = (LotteryViewController) extras
					.getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);

			//fetchDataForFullView(date, viewController);
			new FetchAllLotteryResultsTask().execute();

		} catch (DataFormatException e) {
			Log.e(TAG, "Errors passing data from main activity", e);
		} catch (IllegalStateException e) {
			Log.e(TAG, "Fatal error: " + e.getMessage());
		}

	}
	
	/** Creates the menu items */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, UPDATE_MENU_ID, 0, "Actualizar")
	      .setIcon(android.R.drawable.ic_menu_rotate);
	    return true;
	}
	
	/** Handles item selections */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case UPDATE_MENU_ID:
	      update = true;
	      new FetchAllLotteryResultsTask().execute();
	      return true;
	    }
	    return false;
	}

	/**
	 * Task that fetches the data that the main view will display: the last
	 * results for every lottery type.
	 */
	private class FetchAllLotteryResultsTask extends
			UserTask<Void, Void, List<? extends Lottery>> {
		
		private LinearLayout loadingView;

		@Override
		public void begin() {
			super.begin();
			loadingView = (LinearLayout) findViewById(R.id.loadingView);
			loadingView.setVisibility(View.VISIBLE);
		}
		
		@Override
		public List<? extends Lottery> doInBackground(Void... params) {

			List<? extends Lottery> listLottery = null;
			try {
				LotteryId lotteryId = viewController.getId();
				
				
				try {
					if(stored && !update) // Try to get the data from the database
						listLottery = LotteryDBFactory.newLotteryDB(PrizeActivity.this, lotteryId).retrieveLottery();
					else
						throw new Exception("We throw an exception to update results from Internet");
				}  catch (Exception e) {
					// No lottery stored or out of date, fetch it from Internet
					update = false;
					LotteryFetcher dataFetcher = LotteryFetcherFactory
							.newLotteryFetcher(PrizeActivity.this);
	
					if (lotteryId == LotteryId.BONOLOTO) {
						listLottery = dataFetcher.retrieveBonolotos(date);
					} else if (lotteryId == LotteryId.QUINIELA) {
						listLottery = dataFetcher.retrieveQuinielas(date);
					} else if (lotteryId == LotteryId.PRIMITIVA) {
						listLottery = dataFetcher.retrievePrimitivas(date);
					} else if (lotteryId == LotteryId.QUINIGOL) {
						listLottery = dataFetcher.retrieveQuinigoles(date);
					} else if (lotteryId == LotteryId.LOTOTURF) {
						listLottery = dataFetcher.retrieveLototurfs(date);
					} else if (lotteryId == LotteryId.EUROMILLON) {
						listLottery = dataFetcher.retrieveEuromillones(date);
					} else if (lotteryId == LotteryId.LOTERIA_NACIONAL) {
						listLottery = dataFetcher.retrieveLoteriasNacionales(date);
					} else if (lotteryId == LotteryId.GORDO_PRIMITIVA) {
						listLottery = dataFetcher.retrieveGordoPrimitivas(date);
					} else if (lotteryId == LotteryId.CUPONAZO_ONCE) {
						listLottery = dataFetcher.retrieveCuponazoOnce(date);
					} else if (lotteryId == LotteryId.LOTERIA7_39) {
						listLottery = dataFetcher.retrieveLoteria7_39(date);
					} else if (lotteryId == LotteryId.LOTTO6_49) {
						listLottery = dataFetcher.retrieveLotto6_49(date);
					} else if (lotteryId == LotteryId.ONCE) {
						listLottery = dataFetcher.retrieveOnce(date);
					} else if (lotteryId == LotteryId.ONCE_FINDE) {
						listLottery = dataFetcher.retrieveOnceFinde(date);
					} else if (lotteryId == LotteryId.QUINTUPLE_PLUS) {
						listLottery = dataFetcher.retrieveQuintuplePlus(date);
					} else {
						// TODO(pablo): check this exception handling
						throw new DataFormatException();
					}
				}
			} catch (LotteryInfoUnavailableException e) {
				listLottery = null;
				Log.e(TAG, "Lottery info unavailable", e);
			} catch (DataFormatException e) {
				listLottery = null;
				Log.e(TAG, "Inconsistent data fetched from the main activity", e);
			}

			return listLottery;
		}

		@Override
		public void end(List<? extends Lottery> listLottery) {
			if(listLottery == null) {
				new ErrorDialog(PrizeActivity.this,
						getString(R.string.error_dialog_text) + "\n\n"
						+ getString(R.string.sugerencia_error_dialog_text)).show();
			} else {
				loadingView.setVisibility(View.GONE);
				fullView.removeAllViews();
				fullView.addView(viewController.createAndFillUpMainView(listLottery.get(0), mContext));
				fullView.addView(viewController.createAndFillUpPrizeView(listLottery.get(0), mContext));
			}
		}

	}

}
