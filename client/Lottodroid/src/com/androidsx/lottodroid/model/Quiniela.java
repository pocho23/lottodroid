package com.androidsx.lottodroid.model;

import java.util.Date;

/**
 * Results for a Quiniela draw.
 * 
 */
public class Quiniela implements Lottery {

  public static final int NUM_MATCHES = 15;
  
  /** Contains an array of matches for a Quiniela */
  Match[] listMatches = new Match[NUM_MATCHES];
  
  private final Date date;
  
  public Quiniela(Date date) {
    this.date = date;
  }
  
  /** Inner class that represents a Quiniela Match  */
  private class Match {
    private String homeTeam;
    private String awayTeam;
    private String result;

    public Match(String homeTeam, String awayTeam, String result) {
      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
      this.result = result;
    }

    public String getHomeTeam() {
      return homeTeam;
    }

    public String getAwayTeam() {
      return awayTeam;
    }

    public String getResult() {
      return result;
    }
  }

  /**
   * Set one of the matches of the day, with all the information required: the local and away team,
   * and the result 1, X or 2. 
   * 
   * The match number could be from 0 to 14
   * 
   * @param matchNumber the match number
   * @param homeTeam the local team
   * @param awayTeam the away team
   * @param result the result of the match
   */
  public void setMatch(int matchNumber, String homeTeam, String awayTeam, String result)
      throws ArrayIndexOutOfBoundsException {
      listMatches[matchNumber] = new Match(homeTeam, awayTeam, result);
  }

  /**
   * Returns the name of the team that plays at home (<i>local</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the local team
   */
  public String getHomeTeam(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
      return listMatches[matchNumber].homeTeam;
  }

  /**
   * Returns the name of the team that plays away (<i>visitante</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the away team
   */
  public String getAwayTeam(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
      return listMatches[matchNumber].awayTeam;
  }

  /**
   * Returns the result for the match
   * 
   * @param matchNumber the match number
   * @return the result
   */
  public String getResult(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
      return listMatches[matchNumber].result;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.QUINIELA;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getName() {
    return "Quiniela";
  }

}
