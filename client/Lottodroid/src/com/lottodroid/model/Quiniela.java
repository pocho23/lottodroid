package com.lottodroid.model;

import java.util.Date;

/**
 * Results for a Quiniela draw.
 * 
 */
public class Quiniela implements Lottery {

  /**
   * Inner class that represents a Quiniela Match
   * 
   * TODO: Pablo-> adapt result to the strange enum if required
   * TODO: Error handling with index ranges
   */
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

  public enum MatchResult {
    ONE("1"), X("X"), TWO("2");

    private final String strValue;

    MatchResult(String strValue) {
      this.strValue = strValue;
    }

    @Override
    public String toString() {
      return strValue;
    }
  }

  public static final int NUM_MATCHES = 15;
  
  /** Contains an array of matches for a Quiniela */
  //ArrayList<Match> listMathes = new ArrayList<Match>();
  Match[] listMatches = new Match[NUM_MATCHES];
  private final Date date;

  public Quiniela(Date date) {
    this.date = date;
  }

  /**
   * Set one of the mathes of the day, with all the information required: the local and away team,
   * and the result 1, X or 2
   * 
   * @param matchNumber the match number
   * @param homeTeam the local team
   * @param awayTeam the away team
   * @param result the result of the match
   */
  public void setMatch(int matchNumber, String homeTeam, String awayTeam, String result) {
    listMatches[matchNumber] = new Match(homeTeam, awayTeam, result);
  }

  /**
   * Returns the name of the team that plays at home (<i>local</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the local team
   */
  public String getHomeTeam(int matchNumber) {
    return listMatches[matchNumber].homeTeam;
  }

  /**
   * Returns the name of the team that plays away (<i>visitante</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the away team
   */
  public String getAwayTeam(int matchNumber) {
    return listMatches[matchNumber].awayTeam;
  }

  /**
   * Returns the result for the match
   * 
   * @param matchNumber the match number
   * @return the result
   */
  public String getResult(int matchNumber) {
    return listMatches[matchNumber].result;
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
