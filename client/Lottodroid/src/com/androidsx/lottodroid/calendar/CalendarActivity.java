package com.androidsx.lottodroid.calendar;

import java.util.zip.DataFormatException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidsx.lottodroid.IntentExtraDataNames;
import com.androidsx.lottodroid.PrizeActivity;
import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.calendar.CalendarView.OnCalendarCreated;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.view.LotteryViewController;

public class CalendarActivity extends Activity implements CalendarView.OnCellTouchListener {

	public static final String TAG = CalendarActivity.class.toString();
	
	private CalendarView calendarView = null;
	private TextView txtMonth;
	private Handler mHandler = new Handler();
	private LotteryViewController<Lottery> viewController;
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.calendar_activity);
        calendarView = (CalendarView)findViewById(R.id.calendar);
        calendarView.setOnCellTouchListener(this);
        calendarView.setOnCalendarCreated(onCalendarCreated);
        
        txtMonth = (TextView) findViewById(R.id.txt_month);
        txtMonth.setText(Months.getMonth(calendarView.getMonth()));

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
    }
    
    private OnCalendarCreated onCalendarCreated = new OnCalendarCreated() {
		
		@Override
		public void onCreate(CalendarView calendarView) {
			txtMonth.setText(Months.getMonth(calendarView.getMonth()) + " " + calendarView.getYear());
		}
	};

	public void onTouch(Cell cell) {
		
		if(cell.isPrevMonth()) {
			calendarView.previousMonth();
		} else if(cell.isNextMonth()) {
			calendarView.nextMonth();
		} else {
			startPrizeActivity(DateFormatter.
					toLotoluckString(calendarView.getYear(), calendarView.getMonth()+1, cell.getDayOfMonth()));
		}
	}
	
	private void startPrizeActivity(long date) {
	    Intent i = new Intent(this, PrizeActivity.class);

	    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);
	    i.putExtra("date", date);

	    startActivity(i);
	}
	
	/** Go forward to the day we are. Called from the XML view. */
    public void onButtonTodayClick(@SuppressWarnings("unused") View v) {
        calendarView.goToday();
    }
	
	/** Go forward the next month. Called from the XML view. */
    public void onButtonNextClick(@SuppressWarnings("unused") View v) {
        calendarView.nextMonth();
    }
    
	/** Come back to the previous month. Called from the XML view */
	public void onButtonBackClick(@SuppressWarnings("unused") View v) {
		calendarView.previousMonth();
	}
    
}