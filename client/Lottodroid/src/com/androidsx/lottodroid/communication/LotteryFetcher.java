package com.androidsx.lottodroid.communication;

import java.util.List;

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
 * Fetches data about the lottery results.
 */
public interface LotteryFetcher {

  /**
   * Gets the last result for all the lottery types.
   * 
   * @return a list of lotteries, where each element is the result of a lottery draw
   */
  List<Lottery> retrieveLastAllLotteries() throws LotteryInfoUnavailableException;

  /**
   * Gets the last bonoloto results. 
   * 
   * @param start index to start retrieving results on the server
   * @param limit number of results to retrieve
   * @return a list of {@link Bonoloto} objects
   */
  List<Bonoloto> retrieveLastBonolotos(int start, int limit) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveLastBonolotos} */
  List<Quiniela> retrieveLastQuinielas(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<GordoPrimitiva> retrieveLastGordoPrimitivas(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<Primitiva> retrieveLastPrimitivas(int start, int limit) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveLastBonolotos} */
  List<Lototurf> retrieveLastLototurfs(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<LoteriaNacional> retrieveLastLoteriasNacionales(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<Quinigol> retrieveLastQuinigoles(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<Euromillon> retrieveLastEuromillones(int start, int limit) throws LotteryInfoUnavailableException;
  
  /**
   * Gets the bonoloto results for the given date. 
   * 
   * @param date the date to make the search
   * @return a list of {@link Bonoloto} objects
   */
  List<Bonoloto> retrieveBonolotos(String date) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveBonolotos} */
  List<Quiniela> retrieveQuinielas(String date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<GordoPrimitiva> retrieveGordoPrimitivas(String date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Primitiva> retrievePrimitivas(String date) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveBonolotos} */
  List<Lototurf> retrieveLototurfs(String date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<LoteriaNacional> retrieveLoteriasNacionales(String date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Quinigol> retrieveQuinigoles(String date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Euromillon> retrieveEuromillones(String date) throws LotteryInfoUnavailableException;
  

}
