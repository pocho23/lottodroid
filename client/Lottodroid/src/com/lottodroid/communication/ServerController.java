package com.lottodroid.communication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Handles the communication with the server, providing a clean interface for the client application
 * to retrieve data (bonolotos, quinielas, ...)
 * 
 * TODO: buildURI decente, error handling
 */
public class ServerController {

  static final String URL = "http://10.0.2.2/lottery/?module=data";
  static final String LOTTERY_VAR = "&controller=";
  static final String LIMIT_VAR = "&limit=";
  static final String START_VAR = "&start=";

  /**
   * Asks for the last results of all lottery draws to the server, and converts them into a list of
   * {@link Lottery} value objects
   */
  public static List<Lottery> retrieveLastAllLotteries() {
    
    // Ejemplo real
    //
    /*
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "sorteos");
      return LotteryParser.parseAllLotteries(response);
    } catch (Exception e) {
      Log.i("Lottodroid", e.getMessage() + " " + e.toString());
    }
     */
    
    // Datos de prueba
    //
    List<Lottery> listLottery = new LinkedList<Lottery>();
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listLottery.add(new Quiniela());
    listLottery.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));
    listLottery.add(new Quiniela());
    listLottery.add(new Bonoloto(new Date(), "1 2 3 4 5 6", "1", "0"));
    
    return listLottery;
  }

  /**
   * Asks for the last Bonoloto results to the server, and converts them into a list of
   * {@link Bonoloto} value objects
   * 
   * @param start Index to start retrieving results on the server ( For pagination ).
   * @param limit Number of results to retrieve
   * @return A list of {@link Bonoloto} objects containing the information
   */
  public static List<Lottery> retrieveLastBonolotos(int start, int limit) {

    // Ejemplo real
    //
    /*
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "bonoloto" + LIMIT_VAR + limit);
      return LotteryParser.parseBonoloto(response);
    } catch (Exception e) {
      Log.i("Lottodroid", e.getMessage() + " " + e.toString());
    }*/
    
    
    // Datos de prueba
    //
    List<Lottery> listBonoloto = new LinkedList<Lottery>();
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listBonoloto.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));

    return listBonoloto;

  }

  /**
   * Analogous to {@link #retrieveLastBonolotos}
   */
  public static List<Lottery> retrieveLastQuinielas(int start, int limit) {
    // Ejemplo real
    //
    /*
    try {
      String response = HttpRequestPerformer.getResponse(URL + LOTTERY_VAR + "quiniela");
      return LotteryParser.parseQuiniela(response);
    } catch (Exception e) {
      Log.i("Lottodroid", e.getMessage() + " " + e.toString());
    }*/
    
    // Datos de prueba
    //
    List<Lottery> listQuiniela = new LinkedList<Lottery>();
    listQuiniela.add(new Quiniela());
    listQuiniela.add(new Quiniela());
    listQuiniela.add(new Quiniela());

    return listQuiniela;
  }

}
