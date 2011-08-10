package com.androidsx.lottodroid.util;

import java.util.Calendar;

import com.androidsx.lottodroid.communication.LotteryInfoUnavailableException;
import com.androidsx.lottodroid.model.LotteryId;

public class DateLotteries {

	private DateLotteries() {
		// This class is not to be instantiated
	}
	
	public static boolean isALotteryDay(LotteryId id, int day) {
		if(id == LotteryId.BONOLOTO) {
			return (equals(day, Calendar.MONDAY) || equals(day, Calendar.TUESDAY)
					|| equals(day, Calendar.WEDNESDAY) || equals(day, Calendar.FRIDAY));
		} 
		else if(id == LotteryId.EUROMILLON || id == LotteryId.PRIMITIVA) {
			return (equals(day, Calendar.TUESDAY) || equals(day, Calendar.FRIDAY));
		} 
		else if(id == LotteryId.GORDO_PRIMITIVA || id == LotteryId.LOTERIA_NACIONAL
				|| id == LotteryId.LOTERIA_NACIONAL) {
			return equals(day, Calendar.SUNDAY);
		} 
		else {
			return false;
		}
		
	}

	private static boolean equals(int day, int weekDay) {
		return day+1 == weekDay;
	}
	
	public static String getCurrentDate() {
		Calendar date = Calendar.getInstance();
		return new StringBuilder().append(date.get(Calendar.YEAR))
				.append(date.get(Calendar.MONTH)).append(date.get(Calendar.DAY_OF_MONTH))
				.toString();
	}

	private static int getDistance(int day, int prevDay) {
		if (day > prevDay)
			return day - prevDay;
		else
			return 7 - prevDay + day;
	}

	private static int getShortestDistance(int day, int[] daysWeek) {
		int num = 0;
		int min_dist = 7;
		int new_dist;
		for (int day_week : daysWeek) {
			new_dist = getDistance(day, day_week);
			if (new_dist < min_dist && new_dist != 0) {
				num = day_week;
				min_dist = new_dist;
			}
		}
		return num;
	}

	/**
	 * By giving a valid date for a lottery celebration it returns the previous
	 * result
	 * 
	 * @param date
	 *            The date when a lottery has been hold (in format yyyymmdd)
	 * @return prevDate The previous date when the lottery was hold
	 * @throws LotteryInfoUnavailableException
	 *             If the given date contains no results
	 */
	public static String getPreviousBonoloto(String date)
			throws LotteryInfoUnavailableException {

		/* Bonoloto is celebrated every Monday, Tuesday, Wednesday and Friday */

		Calendar givenDate = string2Calendar(date);

		givenDate.add(
				Calendar.DATE,
				- getShortestDistance(Calendar.DAY_OF_WEEK, new int[] {
						Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY,
						Calendar.FRIDAY }));
		/*
		int day_of_week = givenDate.get(Calendar.DAY_OF_WEEK);
		if (day_of_week == Calendar.MONDAY)
			givenDate.add(Calendar.DATE, -3); // 3 days from Monday to Friday
		else if (day_of_week == Calendar.TUESDAY)
			givenDate.add(Calendar.DATE, -1); // 1 day from Tuesday to Monday
		else if (day_of_week == Calendar.WEDNESDAY)
			givenDate.add(Calendar.DATE, -1); // 1 day from Wednesday to Tuesday
		else if (day_of_week == Calendar.FRIDAY)
			givenDate.add(Calendar.DATE, -2); // 2 days from Friday to Wednesday
		else
			throw new LotteryInfoUnavailableException(
					"There are no results for the given date");
		*/
		return new StringBuilder().append(givenDate.get(Calendar.YEAR))
				.append(givenDate.get(Calendar.MONTH)).append(givenDate.get(Calendar.DAY_OF_MONTH))
				.toString();
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousEuromillon(String date)
			throws LotteryInfoUnavailableException {

		/* Euromillon is celebrated every Tuesday and Friday */

		Calendar givenDate = string2Calendar(date);

		givenDate.add(
				Calendar.DATE,
				- getShortestDistance(Calendar.DAY_OF_WEEK, new int[] {
						Calendar.TUESDAY, Calendar.FRIDAY }));
		
		/*
		int day_of_week = givenDate.get(Calendar.DAY_OF_WEEK);
		if (day_of_week == Calendar.TUESDAY)
			givenDate.add(Calendar.DATE, -4); // 4 days from Tuesday to Friday
		else if (day_of_week == Calendar.FRIDAY)
			givenDate.add(Calendar.DATE, -3); // 3 days from Friday to Wednesday
		else
			throw new LotteryInfoUnavailableException(
					"There are no results for the given date");
		*/

		return new StringBuilder().append(givenDate.get(Calendar.YEAR))
				.append(givenDate.get(Calendar.MONTH)).append(givenDate.get(Calendar.DAY_OF_MONTH))
				.toString();
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousGordoPrimitiva(String date)
			throws LotteryInfoUnavailableException {

		/* GordoPrimitiva is celebrated every Sunday */

		return oncePerWeek(date, Calendar.SUNDAY);
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousLoteriaNacional(String date)
			throws LotteryInfoUnavailableException {

		/* LoteriaNacional is celebrated every Sunday as GordoPrimitiva */

		return oncePerWeek(date, Calendar.SUNDAY);
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousLotortuf(String date)
			throws LotteryInfoUnavailableException {

		/* Lotortuf doesn't have a clear pattern */

		throw new LotteryInfoUnavailableException("Not implemented yet");
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousPrimitiva(String date)
			throws LotteryInfoUnavailableException {

		/* Primitiva is celebrated every Thursday and Saturday */

		Calendar givenDate = string2Calendar(date);

		givenDate.add(
				Calendar.DATE,
				- getShortestDistance(Calendar.DAY_OF_WEEK, new int[] {
						Calendar.THURSDAY, Calendar.FRIDAY }));
		
		/*
		int day_of_week = givenDate.get(Calendar.DAY_OF_WEEK);
		if (day_of_week == Calendar.THURSDAY)
			givenDate.add(Calendar.DATE, -5); // 5 days from Thursday to
												// Saturday
		else if (day_of_week == Calendar.FRIDAY)
			givenDate.add(Calendar.DATE, -2); // 2 days from Saturday to
												// Thursday
		else
			throw new LotteryInfoUnavailableException(
					"There are no results for the given date");
		*/

		return new StringBuilder().append(givenDate.get(Calendar.YEAR))
				.append(givenDate.get(Calendar.MONTH)).append(givenDate.get(Calendar.DAY_OF_MONTH))
				.toString();
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousQuiniela(String date)
			throws LotteryInfoUnavailableException {

		/* Quiniela is celebrated every Sunday and some Wednesday */
		throw new LotteryInfoUnavailableException("Not supported yet");
		/*
		 * Calendar givenDate = string2Calendar(date);
		 * 
		 * int day_of_week = givenDate.get(Calendar.DAY_OF_WEEK); if
		 * (day_of_week == Calendar.WEDNESDAY) givenDate.add(Calendar.DATE, -3);
		 * // 3 days from Wednesday to Sunday else if (day_of_week ==
		 * Calendar.SUNDAY) givenDate.add(Calendar.DATE, -4); // 4 days from
		 * Sunday to Wednesday // TODO: Check whether the previous Wednesday has
		 * results or not. else throw new LotteryInfoUnavailableException(
		 * "There are no results for the given date");
		 * 
		 * return new StringBuilder().append(givenDate.get(Calendar.YEAR))
		 * .append(Calendar.MONTH).append(Calendar.DAY_OF_MONTH) .toString();
		 */
	}

	/** @see {@link #getPreviousBonoloto(String)} */
	public static String getPreviousQuinigol(String date)
			throws LotteryInfoUnavailableException {

		/* Quinigol is irregular */

		throw new LotteryInfoUnavailableException("Not supported yet");
	}

	private static String oncePerWeek(String date, int dayOfWeek)
			throws LotteryInfoUnavailableException {
		Calendar givenDate = string2Calendar(date);

		givenDate.add(
				Calendar.DATE,
				- getDistance(givenDate.DAY_OF_WEEK, dayOfWeek));
		
		/*
		if (givenDate.get(Calendar.DAY_OF_WEEK) == dayOfWeek)
			givenDate.add(Calendar.DATE, -7); // 7 days for a week
		else
			throw new LotteryInfoUnavailableException(
					"There are no results for the given date");
		*/

		return new StringBuilder().append(givenDate.get(Calendar.YEAR))
				.append(givenDate.get(Calendar.MONTH)).append(givenDate.get(Calendar.DAY_OF_MONTH))
				.toString();
	}

	private static Calendar string2Calendar(String date) {
		Calendar givenDate = Calendar.getInstance();
		givenDate.set(Integer.parseInt(date.subSequence(0, 3).toString()),
				Integer.parseInt(date.subSequence(4, 5).toString()),
				Integer.parseInt(date.subSequence(6, 7).toString()));
		return givenDate;
	}
}
