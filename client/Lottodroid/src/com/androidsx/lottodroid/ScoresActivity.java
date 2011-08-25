package com.androidsx.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.UserTask;
import com.androidsx.lottodroid.view.ErrorDialog;
import com.androidsx.lottodroid.view.LotteryViewController;
import com.androidsx.lottodroid.view.QuinielaScoresView;

public class ScoresActivity extends Activity {
	
	public static final String TAG = PrizeActivity.class.toString();
	private LinearLayout fullView;
	private long date;
	private LotteryViewController<Lottery> viewController;
	private Context mContext;
	
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
				LotteryFetcher dataFetcher = LotteryFetcherFactory
					.newLotteryFetcher(ScoresActivity.this);

			if (lotteryId == LotteryId.QUINIELA)
				listLottery = dataFetcher.retrieveQuinielas(date);
			else if (lotteryId == LotteryId.QUINIGOL)
				listLottery = dataFetcher.retrieveQuinigoles(date);
			else
				throw new DataFormatException();
			
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
				new ErrorDialog(ScoresActivity.this,
						getString(R.string.error_dialog_text) + "\n\n"
						+ getString(R.string.sugerencia_error_dialog_text)).show();
			} else {
				loadingView.setVisibility(View.GONE);
				fullView.addView(new QuinielaScoresView().createAndFillUpScoresView(listLottery.get(0), mContext));
			}
		}
	}
}
