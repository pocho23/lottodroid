package com.lottodroid.communication;

import java.util.List;

import android.util.Log;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * TODO(pablo): javadoc
 * 
 * Handles the communication with the server, providing a clean interface for the client application
 * to retrieve data (bonolotos, quinielas, ...)
 * 
 * TODO: buildURI decente, error handling
 */
public class ServerController implements DataFetcher {

  static final String URL = "http://10.0.2.2/lottery/?module=data";
  static final String LOTTERY_VAR = "&controller=";
  static final String LIMIT_VAR = "&limit=";
  static final String START_VAR = "&start=";

  @Override
  public List<Lottery> retrieveLastAllLotteries() throws LotteryInfoUnavailableException {
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "sorteos");
      return LotteryParser.parseAllLotteries(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last lottery results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) throws LotteryInfoUnavailableException {
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "bonoloto" + LIMIT_VAR + limit);
      return LotteryParser.parseBonoloto(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last bonoloto results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) throws LotteryInfoUnavailableException {
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "quiniela");
      return LotteryParser.parseQuiniela(response);
    } catch (Exception e) {
      Log.e("Lottodroid", "Could not retrieve last quiniela results", e);
      throw new LotteryInfoUnavailableException(e);
    }
  }

}
