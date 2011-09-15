package com.androidsx.lottodroid.calendar;

import java.util.zip.DataFormatException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidsx.lottodroid.IntentExtraDataNames;
import com.androidsx.lottodroid.Lottodroid;
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
	private TextView txtInfo;
	private ImageButton btBack;
	private ImageButton btNext;
	private LotteryViewController<Lottery> viewController;
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
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
        
        setContentView(R.layout.calendar_activity);
        calendarView = (CalendarView)findViewById(R.id.calendar);
        calendarView.setOnCellTouchListener(this);
        calendarView.setOnCalendarCreated(onCalendarCreated);
        
        txtMonth = (TextView) findViewById(R.id.txt_month);
        txtMonth.setText(Months.getMonth(calendarView.getMonth()) + " " + calendarView.getYear());
        
        btBack = (ImageButton) findViewById(R.id.btn_back);
        btBack.setOnClickListener(new OnButtonBackListener());
        
        btNext = (ImageButton) findViewById(R.id.btn_next);
        btNext.setOnClickListener(new OnButtonNextListener());
        
        txtInfo = (TextView) findViewById(R.id.txt_info);
        txtInfo.setText(txtInfo.getText() + " " + viewController.getTitle());
        setTitle("Lottodroid, " + viewController.getTitle());
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

	    // Set to false because we do not came from Lottodroid
	    i.putExtra(Lottodroid.TAG, false);
	    i.putExtra(IntentExtraDataNames.LOTTERY_VIEW_CONTROLLER, viewController);
	    i.putExtra("date", date);

	    startActivity(i);
	}
	
	private class OnButtonBackListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			calendarView.previousMonth();
		}
	}
	
	private class OnButtonNextListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			calendarView.nextMonth();
		}
	}
    
}