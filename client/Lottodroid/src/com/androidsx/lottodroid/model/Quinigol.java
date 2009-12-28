package com.androidsx.lottodroid.model;

import java.util.Date;

/**
 * Results for a Quinigol draw.
 * 
 */
public class Quinigol implements Lottery {

  public static final int NUM_MATCHES = 6;
  
  /** Contains an array of matches for a Quiniela */
  Match[] listMatches = new Match[NUM_MATCHES];
  
  private final Date date;
  
  public Quinigol(Date date) {
    this.date = date;
  }
  
  /** Inner class that represents a Quinigol Match  */
  private class Match {
    private String homeTeam;
    private String awayTeam;
    private String homeResult;
    private String awayResult;

    public Match(String homeTeam, String awayTeam, String homeResult, String awayResult) {
      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
      this.homeResult = homeResult;
      this.awayResult = awayResult;
    }

    public String getHomeTeam() {
      return homeTeam;
    }

    public String getAwayTeam() {
      return awayTeam;
    }

    public String getHomeResult() {
      return homeResult;
    }

    public String getAwayResult() {
      return awayResult;
    }

  }

  /**
   * Set one of the matches of the day, with all the information required: the local and away team,
   * and the result [0-9] or M. 
   * 
   * The match number could be from 0 to 5
   * 
   * @param matchNumber the match number
   * @param homeTeam the local team
   * @param awayTeam the away team
   * @param result the result of the match
   */
  public void setMatch(int matchNumber, String homeTeam, String awayTeam, String homeResult,
      String awayResult)
      throws ArrayIndexOutOfBoundsException {
      listMatches[matchNumber] = new Match(homeTeam, awayTeam, homeResult, awayResult);
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
  public String getHomeResult(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
      return listMatches[matchNumber].homeResult;
  }
  
  /**
   * Returns the result for the match
   * 
   * @param matchNumber the match number
   * @return the result
   */
  public String getAwayResult(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
      return listMatches[matchNumber].awayResult;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.QUINIGOL;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getName() {
    return "Quinigol";
  }

}
