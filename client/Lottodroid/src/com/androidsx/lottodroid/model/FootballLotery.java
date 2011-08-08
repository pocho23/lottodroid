package com.androidsx.lottodroid.model;

public interface FootballLotery extends Lottery {
	
	/**
	 * Provide access to the number of matches stored
	 * @return number of matches
	 */
	public int getNumMatches();
	
	/**
	 * Returns the name of the team that plays at home (<i>local</i>) for one of
	 * the matches in the day
	 * 
	 * @param matchNumber the match number
	 * @return the local team
	 */
	public String getHomeTeam(int matchNumber)
			throws ArrayIndexOutOfBoundsException, NullPointerException;

	/**
	 * Returns the name of the team that plays away (<i>visitante</i>) for one
	 * of the matches in the day
	 * 
	 * @param matchNumber the match number
	 * @return the away team
	 */
	public String getAwayTeam(int matchNumber)
			throws ArrayIndexOutOfBoundsException, NullPointerException;
	
	/**
	 * Set one of the scores of the day, with both scores: home and away
	 * 
	 * The match number could be from 0 to 14
	 * 
	 * @param matchNumber the match number
	 * @param homeScore the local team score
	 * @param awayScore the away team score
	 * @param result the result of the match
	 */
	public void setScore(int matchNumber, int homeScore, int awayScore)
			throws ArrayIndexOutOfBoundsException;
	
	/**
	 * Returns the score for the match
	 * 
	 * @param matchNumber the match number
	 * @return the score
	 */
	public int getHomeScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException;
	
	/**
	 * Returns the score for the match
	 * 
	 * @param matchNumber the match number
	 * @return the score
	 */	
	public int getAwayScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException;

}
