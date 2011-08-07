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

public class FullActivity extends Activity {

	public static final String TAG = FullActivity.class.toString();
	private LinearLayout fullView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.full_view);
		fullView = (LinearLayout) findViewById(R.id.full);

		try {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				throw new DataFormatException();
			}

			// Get the date, set from the main activity
			String date = (String) extras.getSerializable("date");

			// Get the view controller, set from the main activity
			@SuppressWarnings("unchecked")
			LotteryViewController<Lottery> viewController = (LotteryViewController) extras
					.getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);

			// set the icon and title
			// ImageView iconCtrl = (ImageView) findViewById(R.id.icon);
			// TextView titleCtrl = (TextView) findViewById(R.id.title);

			// titleCtrl.setText(viewController.getTitle());
			// iconCtrl.setImageResource(viewController.getIconResource());

			fetchDataForFullView(date, viewController);

		} catch (DataFormatException e) {
			Log.e(TAG, "Errors passing data from main activity", e);
		} catch (IllegalStateException e) {
			Log.e(TAG, "Fatal error: " + e.getMessage());
		}

	}

	private void fetchDataForFullView(String date,
			LotteryViewController viewController) {

		try {

			LotteryId lotteryId = viewController.getId();
			LotteryFetcher dataFetcher = LotteryFetcherFactory
					.newLotteryFetcher(FullActivity.this);

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
			} else {
				// TODO(pablo): check this exception handling
				throw new DataFormatException();
			}
			
			fullView.addView(viewController.createAndFillUpMainView(listLottery.get(0), this));
			//setContentView(viewController.createAndFillUpMainView(listLottery.get(0), this));

			// TODO Change ViewController or add another controller for prices

		} catch (LotteryInfoUnavailableException e) {
			Log.e(TAG, "Lottery info unavailable", e);
		} catch (DataFormatException e) {
			Log.e(TAG, "Inconsistent data fetched from the main activity", e);
		}

	}

}
