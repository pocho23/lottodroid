package com.androidsx.lottodroid.communication;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.androidsx.lottodroid.Lottodroid;
import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;

/**
 * Talks to the LottoDroid server to get the different lottery results.
 * 
 */
class ServerLotteryFetcher implements LotteryFetcher {

  static final String URL_STRING = "http://www.androidsx.com/api/?module=data";
  static final String LOTTERY_VAR = "&controller=";
  static final String LIMIT_VAR = "&limit=";
  static final String START_VAR = "&start=";

  public ServerLotteryFetcher(Context context) throws LotteryInfoUnavailableException {
    ConnectivityManager manager = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = manager.getActiveNetworkInfo();

    if (netInfo == null || netInfo.isAvailable() == false) {
      throw new LotteryInfoUnavailableException("Unable to connect, no networks found");
    }
  }

  @Override
  public List<Lottery> retrieveLastAllLotteries() throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("sorteos", 0, 1);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseAllLotteries(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last lottery results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("bonoloto", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseBonoloto(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last bonoloto results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }
  
  @Override
  public List<GordoPrimitiva> retrieveLastGordoPrimitivas(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("gordoprimitiva", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseGordoPrimitiva(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last gordoprimitiva results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("quiniela", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseQuiniela(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Primitiva> retrieveLastPrimitivas(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("primitiva", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parsePrimitiva(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last primitiva results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Lototurf> retrieveLastLototurfs(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("lototurf", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseLototurf(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last lototurf results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Euromillon> retrieveLastEuromillones(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("euromillon", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseEuromillon(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last euromillon results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }
  
  @Override
  public List<LoteriaNacional> retrieveLastLoteriasNacionales(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("loterianacional", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseLoteriaNacional(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last loterianacional results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Quinigol> retrieveLastQuinigoles(int start, int limit)
      throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("quinigol", start, limit);
      String response = HttpRequestPerformer.getResponse(url);

      return LotteryParser.parseQuinigol(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last quinigol results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  /**
   * Build the web service URL to retrieve the lottery data.
   * 
   * @param lotteryController argument of the service that indicates the lottery type
   * @param start Indicates the index to start retrieving results. ( start = 0 : from last result )
   * @param limit Number of results to retrieve
   */
  private static String buildLotteryUrl(String lotteryController, int start, int limit) {
    StringBuilder url = new StringBuilder();

    url.append(URL_STRING).append(LOTTERY_VAR).append(lotteryController);
    url.append(LIMIT_VAR).append(limit).append(START_VAR).append(start);

    Log.i(Lottodroid.TAG, "Connecting to " + url.toString());

    return url.toString();
  }

@Override
public List<Bonoloto> retrieveBonolotos(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<Quiniela> retrieveQuinielas(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<GordoPrimitiva> retrieveGordoPrimitivas(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<Primitiva> retrievePrimitivas(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<Lototurf> retrieveLototurfs(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<LoteriaNacional> retrieveLoteriasNacionales(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<Quinigol> retrieveQuinigoles(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}

@Override
public List<Euromillon> retrieveEuromillones(Long date)
		throws LotteryInfoUnavailableException {
	// This method is just used when retrieve data from Lotoluck
	return null;
}
}
