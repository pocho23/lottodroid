package com.androidsx.lottodroid.communication;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.androidsx.lottodroid.Lottodroid;
import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.Loteria7_39;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.model.Once;
import com.androidsx.lottodroid.model.OnceFinde;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.model.QuintuplePlus;
import com.androidsx.lottodroid.util.DateLotteries;

public class LotoluckLotteryFetcher implements LotteryFetcher {

	static final String URL_STRING = "http://www.lotoluck.com/xml_euro/resultados_loterias.cfm?username=DroidSX&password=pablOmar";
	static final String DATE_VAR = "&fecha=";
	static final String LOTTERY_VAR = "&juego=";
	static final String LIMIT_VAR = "&limit=";
	static final String START_VAR = "&start=";

	public LotoluckLotteryFetcher(Context context)
			throws LotteryInfoUnavailableException {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = manager.getActiveNetworkInfo();

		if (netInfo == null || netInfo.isAvailable() == false) {
			throw new LotteryInfoUnavailableException(
					"Unable to connect, no networks found");
		}
	}

	@Override
	public List<Lottery> retrieveLastAllLotteries()
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.SORTEOS, 0);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseAllLotteries(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last lottery results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Bonoloto> retrieveLastBonolotos(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			//StringBuilder response = new StringBuilder();
			do {
				url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.BONOLOTO,
						Long.parseLong(DateLotteries.getPreviousBonoloto(DateLotteries.getCurrentDate())));
				//response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseBonoloto(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last bonoloto results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<GordoPrimitiva> retrieveLastGordoPrimitivas(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
				url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.GORDO_PRIMITIVA,
						Long.parseLong(DateLotteries.getPreviousGordoPrimitiva(DateLotteries.getCurrentDate())));
				response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseGordoPrimitiva(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid",
					"Could not retrieve last gordoprimitiva results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Quiniela> retrieveLastQuinielas(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.QUINIELA,
					Long.parseLong(DateLotteries.getPreviousQuiniela(DateLotteries.getCurrentDate())));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseQuiniela(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Primitiva> retrieveLastPrimitivas(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.PRIMITIVA,
					Long.parseLong(DateLotteries.getPreviousPrimitiva(DateLotteries.getCurrentDate())));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parsePrimitiva(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last primitiva results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Lototurf> retrieveLastLototurfs(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTOTURF,
					Long.parseLong(DateLotteries.getPreviousLotortuf((DateLotteries.getCurrentDate()))));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseLototurf(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last lototurf results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Euromillon> retrieveLastEuromillones(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.EUROMILLONES,
					Long.parseLong(DateLotteries.getPreviousEuromillon(DateLotteries.getCurrentDate())));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseEuromillon(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last euromillon results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<LoteriaNacional> retrieveLastLoteriasNacionales(int start,
			int limit) throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTERIA_NACIONAL,
					Long.parseLong(DateLotteries.getPreviousLoteriaNacional(DateLotteries.getCurrentDate())));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseLoteriaNacional(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid",
					"Could not retrieve last loterianacional results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Quinigol> retrieveLastQuinigoles(int start, int limit)
			throws LotteryInfoUnavailableException {
		try {
			String url;
			StringBuilder response = new StringBuilder();
			do {
			url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.QUINIGOL, 
					Long.parseLong(DateLotteries.getPreviousQuinigol(DateLotteries.getCurrentDate())));
			response.append(HttpRequestPerformer.getResponse(url));
			} while (++start < limit);
			return LotteryXMLParser.parseQuinigol(response.toString());
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last quinigol results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	/**
	 * Build the web service URL to retrieve the lottery data.
	 * 
	 * @param lotteryController argument of the service that indicates the lottery type
	 * @param start Indicates the index to start retrieving results. ( start = 0 :
	 *            from last result )
	 * @param limit Number of results to retrieve
	 */
	private static String buildLotteryUrl(int lotteryController, long date) {
		StringBuilder url = new StringBuilder();

		url.append(URL_STRING);

		if (lotteryController != LotteryXMLParser.SORTEOS) {

			url.append(DATE_VAR).append(date);
			url.append(LOTTERY_VAR).append(lotteryController);

		}
		Log.i(Lottodroid.TAG, "Connecting to " + url.toString());

		return url.toString();
	}

	@Override
	public List<Bonoloto> retrieveBonolotos(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.BONOLOTO, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseBonoloto(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last bonoloto results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Quiniela> retrieveQuinielas(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.QUINIELA, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseQuiniela(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<GordoPrimitiva> retrieveGordoPrimitivas(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.GORDO_PRIMITIVA, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseGordoPrimitiva(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last gordoPrimitiva results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Primitiva> retrievePrimitivas(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.PRIMITIVA, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parsePrimitiva(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last primitiva results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Lototurf> retrieveLototurfs(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTOTURF, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseLototurf(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last lototurf results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<LoteriaNacional> retrieveLoteriasNacionales(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTERIA_NACIONAL, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseLoteriaNacional(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last loteriaNacional results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Quinigol> retrieveQuinigoles(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.QUINIGOL, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseQuinigol(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last quinigol results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Euromillon> retrieveEuromillones(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.EUROMILLONES, date);
			//String response = HttpRequestPerformer.getResponse(url);
			
			return LotteryXMLParser.parseEuromillon(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last euromillones results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<CuponazoOnce> retrieveCuponazoOnce(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.CUPONAZO_ONCE, date);
			
			return LotteryXMLParser.parseCuponazoOnce(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last CuponazoONCE results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Loteria7_39> retrieveLoteria7_39(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTERIA_7_39, date);
			
			return LotteryXMLParser.parseLoteria7_39(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last Loteria7_39 results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Lotto6_49> retrieveLotto6_49(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.LOTTO6_49, date);
			
			return LotteryXMLParser.parseLotto6_49(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last Lotto6_49 results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<Once> retrieveOnce(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.ONCE, date);
			
			return LotteryXMLParser.parseOnce(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last ONCE results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<OnceFinde> retrieveOnceFinde(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.ONCE_FINDE, date);
			
			return LotteryXMLParser.parseOnceFinde(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last OnceFinde results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}

	@Override
	public List<QuintuplePlus> retrieveQuintuplePlus(Long date)
			throws LotteryInfoUnavailableException {
		try {
			String url = LotoluckLotteryFetcher.buildLotteryUrl(LotteryXMLParser.QUINTUPLE_PLUS, date);
			
			return LotteryXMLParser.parseQuintuplePlus(url);
		} catch (Exception e) {
			Log.e("Lottodroid", "Could not retrieve last QuintuplePlus results", e);
			throw new LotteryInfoUnavailableException(e);
		}
	}
}
