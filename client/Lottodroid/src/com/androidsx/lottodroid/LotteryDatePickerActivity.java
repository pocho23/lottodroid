package com.androidsx.lottodroid;

import java.util.Calendar;
import java.util.zip.DataFormatException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.view.LotteryViewController;

public class LotteryDatePickerActivity extends Activity {

	public static final String TAG = LotteryDatePickerActivity.class.toString();

	private LotteryViewController<Lottery> viewController;

	static final int DATE_DIALOG_ID = 999;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.date_picker_activity);

		try {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				throw new DataFormatException();
			}
			// Get the view controller, set from the main activity
			viewController = (LotteryViewController) extras
					.getSerializable(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER);
		} catch (DataFormatException e) {
			Log.e(TAG, "Errors passing data from main activity", e);
		} catch (IllegalStateException e) {
			Log.e(TAG, "Fatal error: " + e.getMessage());
		}

		setTitle("Lottodroid, " + viewController.getTitle());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		showDialog(DATE_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			// set date picker as current date
			DatePickerDialog dialog = new DatePickerDialog(this, datePickerListener, year, month, day);
			dialog.setCancelable(false);
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int which) {
			       if (which == DialogInterface.BUTTON_NEGATIVE) {
			          finish();
			       }
			    }
			});
			return dialog;
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int year,
				int month, int day) {
			startPrizeActivity(DateFormatter.toLotoluckString(year, month + 1, day));

		}
	};

	private void startPrizeActivity(long date) {
		Intent i = new Intent(this, PrizeActivity.class);

		// Set to false because we do not came from Lottodroid
		i.putExtra(Lottodroid.TAG, false);
		i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);
		i.putExtra("date", date);

		startActivity(i);
	}

}