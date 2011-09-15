package com.androidsx.lottodroid.storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.QuintuplePlus;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class QuintuplePlusDB implements LotteryDB<QuintuplePlus> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String QUINTUPLE_PLUS_FILE = "QuintuplePlus";

	private static final String DATE = "date";
	private static final String RACE1 = "race1";
	private static final String RACE2 = "race2";
	private static final String RACE3 = "race3";
	private static final String RACE4 = "race4";
	private static final String RACE5 = "race5";
	private static final String RACE5_2 = "race5_2";

	private static final String ENTRY_YEAR = "entryyear";
	private static final String ENTRY_MONTH = "entrymonth";
	private static final String ENTRY_DAY = "entryday";
	private static final String ENTRY_HOUR = "entryhour";

	// Premio
	public static final String NUM_PREMIOS = "numpremios";
	public static final String ACERTANTES = "acertantes";
	public static final String CATEGORIA = "categoria";
	public static final String EUROS = "euros";

	private static final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
	private final SharedPreferences db;

	protected QuintuplePlusDB(final Context context) {
		db = context.getSharedPreferences(QUINTUPLE_PLUS_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final QuintuplePlus quintuplePlus) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(quintuplePlus.getDate()));
		editor.putInt(RACE1, quintuplePlus.getRace1());
		editor.putInt(RACE2, quintuplePlus.getRace2());
		editor.putInt(RACE3, quintuplePlus.getRace3());
		editor.putInt(RACE4, quintuplePlus.getRace4());
		editor.putInt(RACE5, quintuplePlus.getRace5());
		editor.putInt(RACE5_2, quintuplePlus.getRace5_2());
		
		editor.putInt(NUM_PREMIOS, quintuplePlus.getNumPremios());

		for (int i = 0; i < quintuplePlus.getNumPremios(); i++) {
			editor.putInt(ACERTANTES + i, quintuplePlus.getAcetantes(i));
			editor.putString(CATEGORIA + i, quintuplePlus.getCategoria(i));
			editor.putFloat(EUROS + i, quintuplePlus.getImporteEuros(i));
		}
		editor.commit();
	}

	@Override
	public List<? extends Lottery> retrieveLottery() throws Exception {
		try {
			Calendar today = Calendar.getInstance();
			boolean isYear = db.getInt(ENTRY_YEAR, -1) == today.get(Calendar.YEAR);
			boolean isMonth = db.getInt(ENTRY_MONTH, -1) == today.get(Calendar.MONTH);
			boolean isDay = db.getInt(ENTRY_DAY, -1) == today.get(Calendar.DAY_OF_MONTH);
			boolean isHour = db.getInt(ENTRY_HOUR, -1) >= today.get(Calendar.HOUR_OF_DAY) - NUM_HOURS_BETWEEN_UPDATE;

			if (isYear && isMonth && isDay && isHour) {

				QuintuplePlus quintuplePlus = new QuintuplePlus(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))),
						db.getInt(RACE1, 0), db.getInt(RACE2, 0), db.getInt(RACE3,0),
						db.getInt(RACE4, 0), db.getInt(RACE5, 0), db.getInt(RACE5_2, 0));

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					quintuplePlus.addPremio(db.getInt(ACERTANTES + i, 0),
							db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<QuintuplePlus> lotteryList = new LinkedList<QuintuplePlus>();
				lotteryList.add(quintuplePlus);
				return lotteryList;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}
