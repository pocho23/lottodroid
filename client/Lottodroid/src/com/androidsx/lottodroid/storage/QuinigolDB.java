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
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.util.DateFormatter;
import com.androidsx.lottodroid.util.DateLotteries;

class QuinigolDB implements LotteryDB<Quinigol> {

	private static final int NUM_HOURS_BETWEEN_UPDATE = 2;

	private static final String QUNIGOL_FILE = "Quinigol";
	
	public static final int NUM_MATCHES = 6;

	private static final String DATE = "date";
	private static final String HOME_TEAM = "ht";
	private static final String AWAY_TEAM = "at";
	private static final String HOME_RESULT = "homeresult";
	private static final String AWAY_RESULT = "awayresult";
	
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

	protected QuinigolDB(final Context context) {
		db = context.getSharedPreferences(QUNIGOL_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public void storeLottery(final Quinigol quinigol) {
		Editor editor = db.edit();

		Calendar today = Calendar.getInstance();
		editor.putInt(ENTRY_YEAR, today.get(Calendar.YEAR));
		editor.putInt(ENTRY_MONTH, today.get(Calendar.MONTH));
		editor.putInt(ENTRY_DAY, today.get(Calendar.DAY_OF_MONTH));
		editor.putInt(ENTRY_HOUR, today.get(Calendar.HOUR_OF_DAY));

		editor.putLong(DATE,
				DateFormatter.toLotoluckString(quinigol.getDate()));
		for(int matchNumber = 0; matchNumber < NUM_MATCHES; matchNumber++) {
			editor.putString(HOME_TEAM + matchNumber, quinigol.getHomeTeam(matchNumber));
			editor.putString(AWAY_TEAM + matchNumber, quinigol.getAwayTeam(matchNumber));
			editor.putString(HOME_RESULT + matchNumber, quinigol.getHomeResult(matchNumber));
			editor.putString(AWAY_RESULT + matchNumber, quinigol.getAwayResult(matchNumber));
			editor.putInt(HOME_SCORE + matchNumber, quinigol.getHomeScore(matchNumber));
			editor.putInt(AWAY_SCORE + matchNumber, quinigol.getAwayScore(matchNumber));
		}
		
		editor.putInt(NUM_PREMIOS, quinigol.getNumPremios());

		for (int i = 0; i < quinigol.getNumPremios(); i++) {
			editor.putInt(ACERTANTES + i, quinigol.getAcetantes(i));
			editor.putString(CATEGORIA + i, quinigol.getCategoria(i));
			editor.putFloat(EUROS + i, quinigol.getImporteEuros(i));
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

				Quinigol quinigol = new Quinigol(
						dfm.parse(DateLotteries.formatDate( Long.toString(db.getLong(DATE, 0)))));
				
				for(int matchNumber = 0; matchNumber < NUM_MATCHES; matchNumber++) {
					quinigol.setMatch(matchNumber, db.getString(HOME_TEAM + matchNumber, ""), db.getString(AWAY_TEAM + matchNumber, ""),
							db.getString(HOME_RESULT + matchNumber, ""), db.getString(AWAY_RESULT + matchNumber, ""));
					quinigol.setScore(matchNumber, db.getInt(HOME_SCORE + matchNumber, 0), db.getInt(AWAY_SCORE + matchNumber, 0));
				}

				int numPremios = db.getInt(NUM_PREMIOS, 0);
				for (int i = 0; i < numPremios; i++)
					quinigol.addPremio(db.getInt(ACERTANTES + i, 0),
							db.getString(CATEGORIA + i, ""),
							db.getFloat(EUROS + i, 0), 0);
				List<Quinigol> lotteryList = new LinkedList<Quinigol>();
				lotteryList.add(quinigol);
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
