package com.androidsx.lottodroid.storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class LoteriaNacionalDB implements LotteryDB<LoteriaNacional> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String LOTERIA_NACIONAL_FILE = "LoteriaNacionala";

	private static final String DATE = "date";
	private static final String PREMIO1 = "premio1";
	private static final String FRACCION = "fraccion";
	private static final String SERIE = "serie";
	private static final String PREMIO2 = "premio2";
	private static final String REINTEGRO1 = "reintegro1";
	private static final String REINTEGRO2 = "reintegro2";
	private static final String REINTEGRO3 = "reintegro3";
	
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

	protected LoteriaNacionalDB(final Context context) {
		db = context.getSharedPreferences(LOTERIA_NACIONAL_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final LoteriaNacional loteriaNacional) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(loteriaNacional.getDate()));
		editor.putInt(PREMIO1, loteriaNacional.getPremio1());
		editor.putInt(FRACCION, loteriaNacional.getFraccion());
		editor.putInt(SERIE, loteriaNacional.getSerie());
		editor.putInt(PREMIO2, loteriaNacional.getPremio2());
		editor.putInt(REINTEGRO1, loteriaNacional.getReintegro1());
		editor.putInt(REINTEGRO2, loteriaNacional.getReintegro2());
		editor.putInt(REINTEGRO3, loteriaNacional.getReintegro3());

		editor.putInt(NUM_PREMIOS, loteriaNacional.getNumPremios());

		for (int i = 0; i < loteriaNacional.getNumPremios(); i++) {
			editor.putString(CATEGORIA + i, loteriaNacional.getCategoria(i));
			editor.putFloat(EUROS + i, loteriaNacional.getImporteEuros(i));
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

				LoteriaNacional loteriaNacional = new LoteriaNacional(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))),
						db.getInt(PREMIO1, 0), db.getInt(FRACCION, 0), db.getInt(SERIE,0),
						db.getInt(PREMIO2, 0), db.getInt(REINTEGRO1, 0), db.getInt(REINTEGRO2, 0), 
						db.getInt(REINTEGRO3, 0));

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					loteriaNacional.addPremio(
							db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<LoteriaNacional> lotteryList = new LinkedList<LoteriaNacional>();
				lotteryList.add(loteriaNacional);
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
