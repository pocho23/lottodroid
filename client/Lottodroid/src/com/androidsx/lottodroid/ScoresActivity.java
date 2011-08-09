package com.androidsx.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.view.LotteryViewController;
import com.androidsx.lottodroid.view.QuinielaScoresView;

public class ScoresActivity extends Activity {
	
	public static final String TAG = PrizeActivity.class.toString();
	private LinearLayout fullView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.prize_view);
		fullView = (LinearLayout) findViewById(R.id.prize);

		try {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				throw new DataFormatException();
			}

			// Get the date, set from the main activity
			Long date = (Long) extras.getSerializable("date");

			// Get the view controller, set from the main activity
			@SuppressWarnings("unchecked")
			LotteryViewController<Lottery> viewController = (LotteryViewController) extras
					.getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);

			fetchDataForFullView(date, viewController);

		} catch (DataFormatException e) {
			Log.e(TAG, "Errors passing data from main activity", e);
		} catch (IllegalStateException e) {
			Log.e(TAG, "Fatal error: " + e.getMessage());
		}

	}

	private void fetchDataForFullView(Long date,
			LotteryViewController viewController) {

		try {

			LotteryId lotteryId = viewController.getId();
			LotteryFetcher dataFetcher = LotteryFetcherFactory
					.newLotteryFetcher(ScoresActivity.this);

			List<? extends Lottery> listLottery;

			if (lotteryId == LotteryId.QUINIELA)
				listLottery = dataFetcher.retrieveQuinielas(date);
			else if (lotteryId == LotteryId.QUINIGOL)
				listLottery = dataFetcher.retrieveQuinigoles(date);
			else
				throw new DataFormatException();
			
			fullView.addView(new QuinielaScoresView().createAndFillUpScoresView(listLottery.get(0), this));


		} catch (LotteryInfoUnavailableException e) {
			Log.e(TAG, "Lottery info unavailable", e);
		} catch (DataFormatException e) {
			Log.e(TAG, "Inconsistent data fetched from the main activity", e);
		}

	}

}
