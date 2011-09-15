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
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class QuinielaDB implements LotteryDB<Quiniela> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String QUNIELA_FILE = "Quiniela";
	
	public static final int NUM_MATCHES = 15;

	private static final String DATE = "date";
	private static final String HOME_TEAM = "ht";
	private static final String AWAY_TEAM = "at";
	private static final String RESULT = "result";
	private static final String HOME_SCORE = "home";
	private static final String AWAY_SCORE = "away";

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

	protected QuinielaDB(final Context context) {
		db = context.getSharedPreferences(QUNIELA_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final Quiniela quiniela) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(quiniela.getDate()));
		for(int matchNumber = 0; matchNumber < NUM_MATCHES; matchNumber++) {
			editor.putString(HOME_TEAM + matchNumber, quiniela.getHomeTeam(matchNumber));
			editor.putString(AWAY_TEAM + matchNumber, quiniela.getAwayTeam(matchNumber));
			editor.putString(RESULT + matchNumber, quiniela.getResult(matchNumber));
			editor.putInt(HOME_SCORE + matchNumber, quiniela.getHomeScore(matchNumber));
			editor.putInt(AWAY_SCORE + matchNumber, quiniela.getAwayScore(matchNumber));
		}
		
		editor.putInt(NUM_PREMIOS, quiniela.getNumPremios());

		for (int i = 0; i < quiniela.getNumPremios(); i++) {
			editor.putInt(ACERTANTES + i, quiniela.getAcetantes(i));
			editor.putString(CATEGORIA + i, quiniela.getCategoria(i));
			editor.putFloat(EUROS + i, quiniela.getImporteEuros(i));
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

				Quiniela quiniela = new Quiniela(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))));
				
				for(int matchNumber = 0; matchNumber < NUM_MATCHES; matchNumber++) {
					quiniela.setMatch(matchNumber, db.getString(HOME_TEAM + matchNumber, ""), 
							db.getString(AWAY_TEAM + matchNumber, ""), db.getString(RESULT + matchNumber, ""));
					quiniela.setScore(matchNumber, db.getInt(HOME_SCORE + matchNumber, 0), db.getInt(AWAY_SCORE + matchNumber, 0));
				}

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					quiniela.addPremio(db.getInt(ACERTANTES + i, 0),
							db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<Quiniela> lotteryList = new LinkedList<Quiniela>();
				lotteryList.add(quiniela);
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
