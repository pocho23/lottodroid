package com.lottodroid.communication;

import java.util.List;

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
 
  
  @Override
  public List<Lottery> retrieveLastAllLotteries() throws LotteryInfoUnavailableException {
    try {
      StringBuilder url = new StringBuilder();
      url.append(URL_STRING).append(LOTTERY_VAR).append("sorteos");

      Log.i(Lottodroid.TAG, "Connecting to " + url.toString());
      
      String response = HttpRequestPerformer.getResponse(url.toString());
      return LotteryParser.parseAllLotteries(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last lottery results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) throws LotteryInfoUnavailableException {
    try {
      StringBuilder url = new StringBuilder();
      url.append(URL_STRING).append(LOTTERY_VAR).append("bonoloto");
      url.append(LIMIT_VAR).append(limit).append(START_VAR).append(start);

      Log.i(Lottodroid.TAG, "Connecting to " + url.toString());
      
      String response = HttpRequestPerformer.getResponse(url.toString());
      return LotteryParser.parseBonoloto(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last bonoloto results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) throws LotteryInfoUnavailableException {
    try {
      StringBuilder url = new StringBuilder();
      url.append(URL_STRING).append(LOTTERY_VAR).append("quiniela");
      url.append(LIMIT_VAR).append(limit).append(START_VAR).append(start);

      Log.i(Lottodroid.TAG, "Connecting to " + url.toString());
      
      String response = HttpRequestPerformer.getResponse(url.toString());
      return LotteryParser.parseQuiniela(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

}
