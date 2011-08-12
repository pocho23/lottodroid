package com.androidsx.lottodroid.communication;

import java.util.List;

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
  List<Bonoloto> retrieveBonolotos(Long date) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveBonolotos} */
  List<Quiniela> retrieveQuinielas(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<GordoPrimitiva> retrieveGordoPrimitivas(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Primitiva> retrievePrimitivas(Long date) throws LotteryInfoUnavailableException;

  /** @see {@link #retrieveBonolotos} */
  List<Lototurf> retrieveLototurfs(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<LoteriaNacional> retrieveLoteriasNacionales(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Quinigol> retrieveQuinigoles(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Euromillon> retrieveEuromillones(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<CuponazoOnce> retrieveCuponazoOnce(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Loteria7_39> retrieveLoteria7_39(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Lotto6_49> retrieveLotto6_49(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<Once> retrieveOnce(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<OnceFinde> retrieveOnceFinde(Long date) throws LotteryInfoUnavailableException;
  
  /** @see {@link #retrieveBonolotos} */
  List<QuintuplePlus> retrieveQuintuplePlus(Long date) throws LotteryInfoUnavailableException;

}
