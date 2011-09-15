package com.androidsx.lottodroid.storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class CuponazoOnceDB implements LotteryDB<CuponazoOnce> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String CUPONAZO_ONCE_FILE = "CuponazoOnce";

	private static final String DATE = "date";
	private static final String NUM = "num";
	private static final String SERIE = "seri";
	private static final String SERIE_ADICIO = "seriadicio";

	private static final String ENTRY_YEAR = "entryyear";
	private static final String ENTRY_MONTH = "entrymonth";
	private static final String ENTRY_DAY = "entryday";
	private static final String ENTRY_HOUR = "entryhour";

	// Premio
	public static final String NUM_PREMIOS = "numpremios";
	public static final String CATEGORIA = "categoria";
	public static final String EUROS = "euros";

	private static final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
	private final SharedPreferences db;

	protected CuponazoOnceDB(final Context context) {
		db = context.getSharedPreferences(CUPONAZO_ONCE_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final CuponazoOnce cuponazoOnce) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(cuponazoOnce.getDate()));
		editor.putString(NUM, cuponazoOnce.getNum());
		editor.putString(SERIE, cuponazoOnce.getSerie());
		editor.putString(SERIE_ADICIO, cuponazoOnce.getSeriesAdicionales());
		
		editor.putInt(NUM_PREMIOS, cuponazoOnce.getNumPremios());

		for (int i = 0; i < cuponazoOnce.getNumPremios(); i++) {
			editor.putString(CATEGORIA + i, cuponazoOnce.getCategoria(i));
			editor.putFloat(EUROS + i, cuponazoOnce.getImporteEuros(i));
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

				CuponazoOnce cuponazoOnce = new CuponazoOnce(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))),
						db.getString(NUM, ""), db.getString(SERIE, ""), db.getString(SERIE_ADICIO, ""));

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					cuponazoOnce.addPremio(db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<CuponazoOnce> lotteryList = new LinkedList<CuponazoOnce>();
				lotteryList.add(cuponazoOnce);
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
