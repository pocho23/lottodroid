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
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class Lotto6_49DB implements LotteryDB<Lotto6_49> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String LOTTO6_49_FILE = "Lotto649";

	private static final String DATE = "date";
	private static final String NUM1 = "num1";
	private static final String NUM2 = "num2";
	private static final String NUM3 = "num3";
	private static final String NUM4 = "num4";
	private static final String NUM5 = "num5";
	private static final String NUM6 = "num6";
	private static final String REINTEGRO = "reintegro";
	private static final String COMPLEMENTARIO = "complementario";
	private static final String JOKER = "joker";

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

	protected Lotto6_49DB(final Context context) {
		db = context.getSharedPreferences(LOTTO6_49_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final Lotto6_49 lotto6_49) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(lotto6_49.getDate()));
		editor.putInt(NUM1, lotto6_49.getNum1());
		editor.putInt(NUM2, lotto6_49.getNum2());
		editor.putInt(NUM3, lotto6_49.getNum3());
		editor.putInt(NUM4, lotto6_49.getNum4());
		editor.putInt(NUM5, lotto6_49.getNum5());
		editor.putInt(NUM6, lotto6_49.getNum6());
		editor.putInt(REINTEGRO, lotto6_49.getReintegro());
		editor.putInt(COMPLEMENTARIO, lotto6_49.getComplementario());
		editor.putLong(JOKER, lotto6_49.getJoker());

		editor.putInt(NUM_PREMIOS, lotto6_49.getNumPremios());

		for (int i = 0; i < lotto6_49.getNumPremios(); i++) {
			editor.putInt(ACERTANTES + i, lotto6_49.getAcetantes(i));
			editor.putString(CATEGORIA + i, lotto6_49.getCategoria(i));
			editor.putFloat(EUROS + i, lotto6_49.getImporteEuros(i));
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

				Lotto6_49 lotto6_49 = new Lotto6_49(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))),
						db.getInt(NUM1, 0), db.getInt(NUM2, 0), db.getInt(NUM3,0),
						db.getInt(NUM4, 0), db.getInt(NUM5, 0), db.getInt(NUM6, 0), 
						db.getInt(REINTEGRO, 0), db.getInt(COMPLEMENTARIO, 0), 
						db.getLong(JOKER, 0));

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					lotto6_49.addPremio(db.getInt(ACERTANTES + i, 0),
							db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<Lotto6_49> lotteryList = new LinkedList<Lotto6_49>();
				lotteryList.add(lotto6_49);
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
