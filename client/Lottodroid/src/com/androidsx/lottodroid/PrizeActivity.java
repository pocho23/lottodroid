package com.androidsx.lottodroid;

import java.util.List;
import java.util.zip.DataFormatException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidsx.lottodroid.communication.LotteryFetcher;
import com.androidsx.lottodroid.communication.LotteryFetcherFactory;
import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.view.LotteryViewController;

public class PrizeActivity extends Activity {

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
					.newLotteryFetcher(PrizeActivity.this);

			List<? extends Lottery> listLottery;

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
			
			fullView.addView(viewController.createAndFillUpMainView(listLottery.get(0), this));
			fullView.addView(viewController.createAndFillUpPrizeView(listLottery.get(0), this));

		} catch (LotteryInfoUnavailableException e) {
			fullView.addView(View.inflate(this, R.layout.error_view, null));
			Log.e(TAG, "Lottery info unavailable", e);
		} catch (DataFormatException e) {
			Log.e(TAG, "Inconsistent data fetched from the main activity", e);
		}
	}
}
