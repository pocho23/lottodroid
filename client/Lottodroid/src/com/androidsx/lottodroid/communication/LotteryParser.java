package com.androidsx.lottodroid.communication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;

/**
 * Parser for the all the lottery draws ( bonoloto, quiniela, etc. ) retrieved
 * from the server. 
 * 
 * Convert the information into {@link Lottery} value objects
 */
class LotteryParser {
  private static final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");

  private LotteryParser() {
    // Instantiation is not allowed for this class
  }

  /**
   * Parse the string containing the information of one or more bonolotos and
   * converts them into a list of {@link Bonoloto} value objects
   * 
   * @param response String that represents the response from the server to a lottery request
   * @return List<Lottery> A list of lottery objects representing the data
   * @throws LotteryParseException
   */
  public static List<Bonoloto> parseBonoloto(String response) throws LotteryParseException {
    try {
      return parseBonolotoData(parseContentTypeAndGetData(response, "bonoloto"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing bonoloto content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing bonoloto date", ex);
    }
  }

  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<Quiniela> parseQuiniela(String response) throws LotteryParseException {
    try {
      return parseQuinielaData(parseContentTypeAndGetData(response, "quiniela"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing quiniela content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing quiniela date", ex);
    } catch (Exception ex) {
      throw new LotteryParseException("Error creating quiniela object", ex);
    }
  }
  
  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<Primitiva> parsePrimitiva(String response) throws LotteryParseException {
    try {
      return parsePrimitivaData(parseContentTypeAndGetData(response, "primitiva"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing primitiva content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing primitiva date", ex);
    }
  }
  
  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<Lototurf> parseLototurf(String response) throws LotteryParseException {
    try {
      return parseLototurfData(parseContentTypeAndGetData(response, "lototurf"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing lototurf content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing lototurf date", ex);
    }
  }
  
  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<Euromillon> parseEuromillon(String response) throws LotteryParseException {
    try {
      return parseEuromillonData(parseContentTypeAndGetData(response, "euromillon"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing euromillon content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing euromillon date", ex);
    }
  }
  
  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<LoteriaNacional> parseLoteriaNacional(String response) throws LotteryParseException {
    try {
      return parseLoteriaNacionalData(parseContentTypeAndGetData(response, "loterianacional"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing Loteria Nacional content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing Loteria Nacional date", ex);
    }
  }
  
  /**
   * Analogous to {@link #parseBonoloto}
   */
  public static List<Quinigol> parseQuinigol(String response) throws LotteryParseException {
    try {
      return parseQuinigolData(parseContentTypeAndGetData(response, "quinigol"));

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing quinigol content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing quinigol date", ex);
    }
  }

  /**
   * Parse the string containing the information of the last results of
   * every lottery draw and converts them into a list of {@link Lottery} 
   * value objects
   * 
   * @param response String that represents the response from the server to a lottery request
   * @return List<Lottery> A list of lottery objects representing the data
   * @throws LotteryParseException
   */
  public static List<Lottery> parseAllLotteries(String response) throws LotteryParseException {
    try {
      String jsonData = parseContentTypeAndGetData(response, "sorteos");
      JSONObject jsonObject = new JSONObject(jsonData);

      List<Lottery> listLottery = new LinkedList<Lottery>();

      List<Bonoloto> listBonoloto = parseBonolotoData(jsonObject.getString("bonoloto"));
      List<Quiniela> listQuiniela = parseQuinielaData(jsonObject.getString("quiniela"));
      List<Primitiva> listPrimitiva = parsePrimitivaData(jsonObject.getString("primitiva"));
      List<Lototurf> listLototurf = parseLototurfData(jsonObject.getString("lototurf"));
      List<LoteriaNacional> listLoteriaNacional = parseLoteriaNacionalData(jsonObject.getString("loterianacional"));
      List<Quinigol> listQuinigol = parseQuinigolData(jsonObject.getString("quinigol"));
      List<Euromillon> listEuromillon = parseEuromillonData(jsonObject.getString("euromillon"));

      if (listBonoloto.size() > 0) {
        listLottery.add(listBonoloto.get(0));
      }
      if (listPrimitiva.size() > 0) {
        listLottery.add(listPrimitiva.get(0));
      }
      if (listQuiniela.size() > 0) {
        listLottery.add(listQuiniela.get(0));
      }
      if (listLototurf.size() > 0) {
        listLottery.add(listLototurf.get(0));
      }
      if (listQuinigol.size() > 0) {
        listLottery.add(listQuinigol.get(0));
      }
      if (listEuromillon.size() > 0) {
        listLottery.add(listEuromillon.get(0));
      }
      if (listLoteriaNacional.size() > 0) {
        listLottery.add(listLoteriaNacional.get(0));
      }

      return listLottery;

    } catch (JSONException e) {
      throw new LotteryParseException("Error parsing lottery content", e);
    } catch (ParseException ex) {
      throw new LotteryParseException("Error parsing lottery date", ex);
    }
  }

  /**
   * Parse the string containing the information of a lottery draw checking that the data retrieved
   * is the same type as the one requested
   * 
   * @param response Represents the response from the server to a lottery request
   * @param contentType Lottery type requested
   * @return Data retrieved for a lottery draw
   * @throws LotteryParseException
   * @throws JSONException
   */
  private static String parseContentTypeAndGetData(String response, String contentType)
      throws LotteryParseException, JSONException {
    JSONObject jsonObject = new JSONObject(response);

    String jsonContentType = jsonObject.getString("info");
    String strData = jsonObject.getString("data");

    if (jsonContentType.equals(contentType)) {
      return strData;
    } else {
      if (jsonContentType.equals("error"))
        throw new LotteryParseException(strData);
      else
        throw new LotteryParseException("Wrong controller selected");
    }
  }

  private static List<Bonoloto> parseBonolotoData(String strContent) throws JSONException, ParseException {
    List<Bonoloto> lotteryList = new LinkedList<Bonoloto>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);

      lotteryList.add(new Bonoloto(dfm.parse(item.getString("fecha")), 
          item.getInt("num1"), item.getInt("num2"), item.getInt("num3"), 
          item.getInt("num4"), item.getInt("num5"), item.getInt("num6"), 
          item.getInt("reintegro"), item.getInt("complementario")));  
    }

    return lotteryList;
  }

  private static List<Quiniela> parseQuinielaData(String strContent) throws JSONException, ParseException {
    List<Quiniela> lotteryList = new LinkedList<Quiniela>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);
      Quiniela quiniela = new Quiniela(dfm.parse(item.getString("fecha")));
      
      JSONArray results = item.getJSONArray("results");
      int numResults = results.length();

      for (int j = 0; j < numResults; j++) {
        JSONObject match = results.getJSONObject(j);

        quiniela.setMatch(j,  match.getString("local"), 
                              match.getString("visitante"), 
                              match.getString("resultado"));  
      }
      lotteryList.add(quiniela);
    }

    return lotteryList;
  }
  
  private static List<Primitiva> parsePrimitivaData(String strContent) throws JSONException, ParseException {
    List<Primitiva> lotteryList = new LinkedList<Primitiva>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);

      lotteryList.add(new Primitiva(dfm.parse(item.getString("fecha")), 
          item.getInt("num1"), item.getInt("num2"), item.getInt("num3"), 
          item.getInt("num4"), item.getInt("num5"), item.getInt("num6"), 
          item.getInt("reintegro"), item.getInt("complementario")));  
    }
    
    return lotteryList;
  }
  
  private static List<Lototurf> parseLototurfData(String strContent) throws JSONException, ParseException {
    List<Lototurf> lotteryList = new LinkedList<Lototurf>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);

      lotteryList.add(new Lototurf(dfm.parse(item.getString("fecha")), 
          item.getInt("num1"), item.getInt("num2"), item.getInt("num3"), 
          item.getInt("num4"), item.getInt("num5"), item.getInt("num6"), 
          item.getInt("caballoGanador"), item.getInt("reintegro")));  
    }
    
    return lotteryList;
  }
  
  private static List<Euromillon> parseEuromillonData(String strContent) throws JSONException, ParseException {
    List<Euromillon> lotteryList = new LinkedList<Euromillon>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);

      lotteryList.add(new Euromillon(dfm.parse(item.getString("fecha")), 
          item.getInt("num1"), item.getInt("num2"), item.getInt("num3"), 
          item.getInt("num4"), item.getInt("num5"), item.getInt("estrella1"), 
          item.getInt("estrella2")));  
    }
    
    return lotteryList;
  }
  
  private static List<LoteriaNacional> parseLoteriaNacionalData(String strContent) throws JSONException, ParseException {
    List<LoteriaNacional> lotteryList = new LinkedList<LoteriaNacional>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);

      lotteryList.add(new LoteriaNacional(dfm.parse(item.getString("fecha")), 
          item.getInt("premio1"), item.getInt("fraccion"), item.getInt("serie"), 
          item.getInt("premio2"), item.getInt("reintegro1"), item.getInt("reintegro2"), 
          item.getInt("reintegro3")));  
    }
    
    return lotteryList;
  }
  
  private static List<Quinigol> parseQuinigolData(String strContent) throws JSONException, ParseException {
    List<Quinigol> lotteryList = new LinkedList<Quinigol>();

    JSONArray jsonContent = new JSONArray(strContent);
    int numItems = jsonContent.length();

    for (int i = 0; i < numItems; i++) {
      JSONObject item = jsonContent.getJSONObject(i);
      Quinigol quinigol = new Quinigol(dfm.parse(item.getString("fecha")));
      
      JSONArray results = item.getJSONArray("results");
      int numResults = results.length();

      for (int j = 0; j < numResults; j++) {
        JSONObject match = results.getJSONObject(j);

        quinigol.setMatch(j,  match.getString("local"), 
                              match.getString("visitante"), 
                              match.getString("marcadorLocal"),
                              match.getString("marcadorVisitante"));  
      }
      lotteryList.add(quinigol);
    }

    return lotteryList;
  }
}
