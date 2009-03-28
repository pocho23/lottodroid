package com.lottodroid.model;

import java.util.Date;

/**
 * Results for a Quiniela draw.
 * 
 * TODO: data should come from the constructor, not invented here
 */
public class Quiniela implements Lottery {

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

  /**
   * Returns the name of the team that plays at home (<i>local</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the local team
   */
  public String getHomeTeam(int matchNumber) {
    return matchNumber == 0 ? "Barcelona" : "Villareal";
  }

  /**
   * Returns the name of the team that plays away (<i>visitante</i>) for one of the matches in the
   * day
   * 
   * @param matchNumber the match number
   * @return the away team
   */
  public String getAwayTeam(int matchNumber) {
    return matchNumber == 0 ? "Madrid" : "Numancia";
  }

  /**
   * Returns the result for the match
   * 
   * @param matchNumber the match number
   * @return the result
   */
  public MatchResult getResult(int matchNumber) {
    return matchNumber == 0 ? MatchResult.ONE : MatchResult.X;
  }

  @Override
  public Date getDate() {
    return new Date();
  }

  @Override
  public String getName() {
    return "Quiniela";
  }

}
