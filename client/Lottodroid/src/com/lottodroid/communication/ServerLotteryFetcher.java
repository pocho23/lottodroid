package com.lottodroid.communication;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lottodroid.Lottodroid;
import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Talks to the LottoDroid server to get the different lottery results.
 * 
 */
public class ServerLotteryFetcher implements LotteryFetcher {

  static final String URL_STRING = "http://www.el33.es/lottery/?module=data";
  static final String LOTTERY_VAR = "&controller=";
  static final String LIMIT_VAR = "&limit=";
  static final String START_VAR = "&start=";
  
  public ServerLotteryFetcher(Context context) throws LotteryInfoUnavailableException {
    ConnectivityManager manager = (ConnectivityManager)
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = manager.getActiveNetworkInfo();

    if (netInfo == null || netInfo.isAvailable() == false){
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
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) throws LotteryInfoUnavailableException {
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
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) throws LotteryInfoUnavailableException {
    try {
      String url = ServerLotteryFetcher.buildLotteryUrl("quiniela", start, limit);
      String response = HttpRequestPerformer.getResponse(url);
      
      return LotteryParser.parseQuiniela(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
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
}
