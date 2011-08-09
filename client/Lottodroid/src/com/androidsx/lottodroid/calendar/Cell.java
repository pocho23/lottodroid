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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * This class represents a day in the calendar.
 * @author Hugo
 *
 */
public class Cell {
	
	/**
	 * PREV_MONTH is 0 because the elements in the first row from a different month
	 * are the ones from the actual month
	 */
	static final int PREV_MONTH = 0;
	static final int THIS_MONTH = 1;
	static final int NEXT_MONTH = 2;
	
	protected Rect mBound = null;
	protected int mDayOfMonth = 1;	// from 1 to 31
	protected int month = 1;
	protected Paint mPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG
            |Paint.ANTI_ALIAS_FLAG);
	int dx, dy;
	public Cell(int dayOfMon, int month, Rect rect, float textSize, boolean bold) {
		mDayOfMonth = dayOfMon;
		this.month = month;
		mBound = rect;
		mPaint.setTextSize(textSize/*26f*/);
		mPaint.setColor(Color.BLACK);
		if(bold) mPaint.setFakeBoldText(true);
		
		dx = (int) mPaint.measureText(String.valueOf(mDayOfMonth)) / 2;
		dy = (int) (-mPaint.ascent() + mPaint.descent()) / 2;
	}
	
	public Cell(int dayOfMon, int month, Rect rect, float textSize) {
		this(dayOfMon, month, rect, textSize, false);
	}
	
	protected boolean isThisMonth() {
		return (month == THIS_MONTH);
	}
	
	protected boolean isNextMonth() {
		return (month == NEXT_MONTH);
	}
	
	protected boolean isPrevMonth() {
		return (month == PREV_MONTH);
	}
	
	protected void draw(Canvas canvas) {
		canvas.drawText(String.valueOf(mDayOfMonth), mBound.centerX() - dx, mBound.centerY() + dy, mPaint);
	}
	
	public int getDayOfMonth() {
		return mDayOfMonth;
	}
	
	public int getMonth() {
		return month;
	}
	
	public boolean hitTest(int x, int y) {
		return mBound.contains(x, y); 
	}
	
	public Rect getBound() {
		return mBound;
	}
	
	public String toString() {
		return String.valueOf(mDayOfMonth)+"("+mBound.toString()+")";
	}
	
}

