package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Quiniela draw.
 * 
 */
public class Quiniela implements FootballLotery {

	public static final int NUM_MATCHES = 15;
	
	/** Contains an array of matches for a Quiniela */
	Match[] listMatches = new Match[NUM_MATCHES];
	
	/** Contains an array of scores for a Quiniela */
	Score[] listScores = new Score[NUM_MATCHES];

	/** Contains an array of premios for a Quiniela */
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	private final Date date;

	public Quiniela(Date date) {
		this.date = date;
	}

	/** Inner class that represents a Quiniela Match */
	private class Match {
		private final String homeTeam;
		private final String awayTeam;
		private final String result;

		public Match(String homeTeam, String awayTeam, String result) {
			this.homeTeam = homeTeam;
			this.awayTeam = awayTeam;
			this.result = result;
		}
	}

	/**
	 * Set one of the matches of the day, with all the information required: the
	 * local and away team, and the result 1, X or 2.
	 * 
	 * The match number could be from 0 to 14
	 * 
	 * @param matchNumber the match number
	 * @param homeTeam the local team
	 * @param awayTeam the away team
	 * @param result the result of the match
	 */
	public void setMatch(int matchNumber, String homeTeam, String awayTeam,
			String result) throws ArrayIndexOutOfBoundsException {
		listMatches[matchNumber] = new Match(homeTeam, awayTeam, result);
	}

	/**
	 * Returns the name of the team that plays at home (<i>local</i>) for one of
	 * the matches in the day
	 * 
	 * @param matchNumber the match number
	 * @return the local team
	 */
	public String getHomeTeam(int matchNumber)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		return listMatches[matchNumber].homeTeam;
	}

	/**
	 * Returns the name of the team that plays away (<i>visitante</i>) for one
	 * of the matches in the day
	 * 
	 * @param matchNumber
	 *            the match number
	 * @return the away team
	 */
	public String getAwayTeam(int matchNumber)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		return listMatches[matchNumber].awayTeam;
	}

	/**
	 * Returns the result for the match
	 * 
	 * @param matchNumber
	 *            the match number
	 * @return the result
	 */
	public String getResult(int matchNumber)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		return listMatches[matchNumber].result;
	}

	/** Inner class that represents a Quinigol Score */
	private class Score {
		private int homeScore;
		private int awayScore;
		
		public Score(int homeScore, int awayScore) {
			this.homeScore = homeScore;
			this.awayScore = awayScore;
		}
	}
	
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
			throws ArrayIndexOutOfBoundsException {
		listScores[matchNumber] = new Score(homeScore, awayScore);
	}
	
	/**
	 * Returns the score for the match
	 * 
	 * @param matchNumber the match number
	 * @return the score
	 */
	public int getHomeScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return listScores[machtNumber].homeScore;
	}
	
	/**
	 * Returns the score for the match
	 * 
	 * @param matchNumber the match number
	 * @return the score
	 */	
	public int getAwayScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return listScores[machtNumber].awayScore;
	}

	/** Inner class that represents a Quiniela Premio */
	public class Premio {

		private final int acertantes;
		private final String categoria;
		private final float importeEuros;
		private final long importePesetas;

		private Premio(int acertantes, String categoria, float importeEuros,
				long importePesetas) {
			this.acertantes = acertantes;
			this.categoria = categoria;
			this.importeEuros = importeEuros;
			this.importePesetas = importePesetas;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("Acertantes: ")
					.append(acertantes).append("  Categoria: ")
					.append(categoria).append("  ImporteEuros: ")
					.append(importeEuros).append("  ImportePesetas: ")
					.append(importePesetas).toString();
		}
	}
	
	public void addPremio(int acertantes, String categoria, float importeEuros,
			long importePesetas) {
		premios.add(new Premio(acertantes, categoria, importeEuros,
				importePesetas));
	}

	public Premio getPremio(int index) {
		return premios.get(index);
	}

	public int getNumPremios() {
		return premios.size();
	}

	public int getAcetantes(int index) {
		return premios.get(index).acertantes;
	}

	public String getCategoria(int index) {
		return premios.get(index).categoria;
	}

	public float getImporteEuros(int index) {
		return premios.get(index).importeEuros;
	}

	public long getImportePesetas(int index) {
		return premios.get(index).importePesetas;
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

	@Override
	public int getNumMatches() {
		return NUM_MATCHES;
	}
}
