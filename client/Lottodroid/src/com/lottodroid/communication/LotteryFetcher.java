package com.lottodroid.communication;

import java.util.List;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Euromillon;
import com.lottodroid.model.LoteriaNacional;
import com.lottodroid.model.Lototurf;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Primitiva;
import com.lottodroid.model.Quiniela;
import com.lottodroid.model.Quinigol;

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
  List<Primitiva> retrieveLastPrimitivas(int start, int limit) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveLastBonolotos} */
  List<Lototurf> retrieveLastLototurfs(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<LoteriaNacional> retrieveLastLoteriasNacionales(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<Quinigol> retrieveLastQuinigoles(int start, int limit) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveLastBonolotos} */
  List<Euromillon> retrieveLastEuromillones(int start, int limit) throws LotteryInfoUnavailableException;

}
