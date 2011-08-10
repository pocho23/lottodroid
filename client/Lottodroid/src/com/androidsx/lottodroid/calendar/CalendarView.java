/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidsx.lottodroid.calendar;

import java.util.Calendar;
import java.util.zip.DataFormatException;

import com.androidsx.lottodroid.IntentExtraDataNames;
import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.util.DateLotteries;
import com.androidsx.lottodroid.view.LotteryViewController;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * This view visualizes a calendar and provide methods to access its days
 * @author Hugo
 *
 */
public class CalendarView extends ImageView {
	
	public static final String TAG = CalendarView.class.toString();
	
    private static int WEEK_TOP_MARGIN = 74;
    private static int WEEK_LEFT_MARGIN = 40;
    private static int CELL_WIDTH = 58;
    private static int CELL_HEIGH = 53;
    private static int CELL_MARGIN_TOP = 92;
    private static int CELL_MARGIN_LEFT = 39;
    private static float CELL_TEXT_SIZE;
    
	private Calendar mRightNow = null;
    private Drawable mWeekTitle = null;
    private Cell mToday = null;
    private Cell[][] mCells = new Cell[6][7];
    private OnCellTouchListener mOnCellTouchListener = null;
    private OnCalendarCreated onCalendarCreated = null;
    private MonthDisplayHelper mHelper;
    private Drawable mDecoration = null;
    private LotteryViewController<Lottery> viewController;
    
    /**
     * It will be called when a day is touched.
     * @author Hugo
     *
     */
	public interface OnCellTouchListener {
    	public void onTouch(Cell cell);
    }
	
	/**
	 * This will be called after the whole calendar is created. Note that when you change
	 * the month and the view is updated, it will be called as well.
	 * @author Hugo
	 *
	 */
	public interface OnCalendarCreated {
    	public void onCreate(CalendarView calendarView);
    }
	
	public void setOnCalendarCreated(OnCalendarCreated onCalendarCreated) {
		this.onCalendarCreated = onCalendarCreated;
	}

	public CalendarView(Context context) {
		this(context, null);
	}
	
	public CalendarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDecoration = context.getResources().getDrawable(R.drawable.typeb_calendar_today);
		
		try {
			Bundle extras = ((Activity)context).getIntent().getExtras();

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
		initCalendarView();
	}
	
	private void initCalendarView() {
		mRightNow = Calendar.getInstance();
		// prepare static vars
		Resources res = getResources();
		WEEK_TOP_MARGIN  = (int) res.getDimension(R.dimen.week_top_margin);
		WEEK_LEFT_MARGIN = (int) res.getDimension(R.dimen.week_left_margin);
		
		CELL_WIDTH = (int) res.getDimension(R.dimen.cell_width);
		CELL_HEIGH = (int) res.getDimension(R.dimen.cell_heigh);
		CELL_MARGIN_TOP = (int) res.getDimension(R.dimen.cell_margin_top);
		CELL_MARGIN_LEFT = (int) res.getDimension(R.dimen.cell_margin_left);
		CELL_TEXT_SIZE = res.getDimension(R.dimen.cell_text_size);
		// set background
		setImageResource(R.drawable.background);
		mWeekTitle = res.getDrawable(R.drawable.calendar_week);
		mWeekTitle.setBounds(WEEK_LEFT_MARGIN, WEEK_TOP_MARGIN, WEEK_LEFT_MARGIN+mWeekTitle.getMinimumWidth(), WEEK_TOP_MARGIN+mWeekTitle.getMinimumHeight());
		
		mHelper = new MonthDisplayHelper(mRightNow.get(Calendar.YEAR), mRightNow.get(Calendar.MONTH));
		initCells();
    }
	
	private void initCells() {
	    class _calendar {
	    	public int day;
	    	public int month;
	    	public _calendar(int day, int month) {
	    		this.day = day;
	    		this.month = month;
	    	}
	    	
	    	public boolean isThisMonth() {
	    		return (month == Cell.THIS_MONTH);
	    	}
	    };
	    _calendar tmpCalendar[][] = new _calendar[6][7];
	    
	    for(int row=0; row< tmpCalendar.length; row++) {
	    	int n[] = mHelper.getDigitsForRow(row);
	    	for(int column=0; column<n.length; column++) {
	    		if(mHelper.isWithinCurrentMonth(row, column))
	    			tmpCalendar[row][column] = new _calendar(n[column], Cell.THIS_MONTH);
	    		else if(row == Cell.PREV_MONTH)
	    			tmpCalendar[row][column] = new _calendar(n[column], Cell.PREV_MONTH);
	    		else
	    			tmpCalendar[row][column] = new _calendar(n[column], Cell.NEXT_MONTH);
	    		
	    	}
	    }

	    Calendar today = Calendar.getInstance();
	    // we instantiate it to 32 to be out of the month
	    int thisDay = 32;
	    mToday = null;
	    
	    boolean correctYear = mHelper.getYear() == today.get(Calendar.YEAR);
	    boolean correctMonth = mHelper.getMonth() == today.get(Calendar.MONTH);
	    
	    boolean beforeThisYear = mHelper.getYear() <= Calendar.getInstance().get(Calendar.YEAR);
	    boolean beforeThisMonth = mHelper.getMonth() <= Calendar.getInstance().get(Calendar.MONTH);
	    
	    if(correctYear && correctMonth) {
	    	thisDay = today.get(Calendar.DAY_OF_MONTH);
	    }
	    
	    boolean beforeToday;
		// build cells
		Rect Bound = new Rect(CELL_MARGIN_LEFT, CELL_MARGIN_TOP, CELL_WIDTH+CELL_MARGIN_LEFT, CELL_HEIGH+CELL_MARGIN_TOP);
		for(int week=0; week<mCells.length; week++) {
			for(int day=0; day<mCells[week].length; day++) {
				if(tmpCalendar[week][day].isThisMonth()) {
					beforeToday = tmpCalendar[week][day].day < thisDay;
					if(DateLotteries.isALotteryDay(viewController.getId(), day) && beforeToday && beforeThisMonth && beforeThisYear)
						mCells[week][day] = new BlueCell(tmpCalendar[week][day].day, tmpCalendar[week][day].month, new Rect(Bound), CELL_TEXT_SIZE);
					else if(day==0 || day==6)
						mCells[week][day] = new RedCell(tmpCalendar[week][day].day, tmpCalendar[week][day].month, new Rect(Bound), CELL_TEXT_SIZE);
					else 
						mCells[week][day] = new Cell(tmpCalendar[week][day].day, tmpCalendar[week][day].month, new Rect(Bound), CELL_TEXT_SIZE);
				} else
					mCells[week][day] = new GrayCell(tmpCalendar[week][day].day, tmpCalendar[week][day].month, new Rect(Bound), CELL_TEXT_SIZE);
				
				Bound.offset(CELL_WIDTH, 0); // move to next column 
				
				// get today
				if(tmpCalendar[week][day].day == thisDay && tmpCalendar[week][day].isThisMonth()) {
					mToday = mCells[week][day];
					mDecoration.setBounds(mToday.getBound());
				}
			}
			Bound.offset(0, CELL_HEIGH); // move to next row and first column
			Bound.left = CELL_MARGIN_LEFT;
			Bound.right = CELL_MARGIN_LEFT+CELL_WIDTH;
		}
		if(onCalendarCreated != null)
			onCalendarCreated.onCreate(this);
	}

    public void setTimeInMillis(long milliseconds) {
    	mRightNow.setTimeInMillis(milliseconds);
    	initCells();
    	this.invalidate();
    }
        
    public int getYear() {
    	return mHelper.getYear();
    }
    
    public int getMonth() {
    	return mHelper.getMonth();
    }
    
    /**
     * Moves the calendar view to the previous month
     */
    public void nextMonth() {
    	mHelper.nextMonth();
    	initCells();
    	invalidate();
    }
    
    /**
     * Moves the calendar view to the previous month
     */
    public void previousMonth() {
    	mHelper.previousMonth();
    	initCells();
    	invalidate();
    }
    
    public boolean firstDay(int day) {
    	return day==1;
    }
    
    
    public boolean lastDay(int day) {
    	return mHelper.getNumberOfDaysInMonth()==day;
    }
    
    /**
     * Moves the calendar view until the day we are
     */
    public void goToday() {
    	Calendar cal = Calendar.getInstance();
    	mHelper = new MonthDisplayHelper(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
    	initCells();
    	invalidate();
    }
    
    /**
     * Get actual date
     * @return
     */
    public Calendar getDate() {
    	return mRightNow;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(mOnCellTouchListener!=null){
	    	for(Cell[] week : mCells) {
				for(Cell day : week) {
					if(day.hitTest((int)event.getX(), (int)event.getY())) {
						mOnCellTouchListener.onTouch(day);
					}						
				}
			}
    	}
    	return super.onTouchEvent(event);
    }
  
    /**
     * To be able to provide an action for a cell touch
     * @param p
     */
    public void setOnCellTouchListener(OnCellTouchListener p) {
		mOnCellTouchListener = p;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// draw background
		super.onDraw(canvas);
		mWeekTitle.draw(canvas);
		
		// draw cells
		for(Cell[] week : mCells) {
			for(Cell day : week) {
				day.draw(canvas);			
			}
		}
		
		// draw today
		if(mDecoration!=null && mToday!=null) {
			mDecoration.draw(canvas);
		}
	}
	
	/**
	 * Extends the class Cell in order to provide a grey color.
	 * It is used for the days of another month.
	 * @author Hugo
	 *
	 */
	private class GrayCell extends Cell {
		public GrayCell(int dayOfMon, int month, Rect rect, float s) {
			super(dayOfMon, month, rect, s);
			mPaint.setColor(Color.LTGRAY);
		}			
	}
	
	/**
	 * Extends the class Cell in order to provide a red color
	 * It is used for the weekends.
	 * @author Hugo
	 *
	 */
	private class RedCell extends Cell {
		public RedCell(int dayOfMon, int month, Rect rect, float s) {
			super(dayOfMon, month, rect, s);
			mPaint.setColor(0xdddd0000);
		}			
	}
	
	/**
	 * Extends the class Cell in order to provide a blue color
	 * It is used for the expected lottery days
	 * @author Hugo
	 *
	 */
	private class BlueCell extends Cell {
		public BlueCell(int dayOfMon, int month, Rect rect, float s) {
			super(dayOfMon, month, rect, s);
			mPaint.setColor(Color.BLUE);
		}			
	}
}
