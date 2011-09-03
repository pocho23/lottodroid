package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Quintuple Plus draw
 */
public class QuintuplePlus implements Lottery {
	private final Date date;
	private final int race1;
	private final int race2;
	private final int race3;
	private final int race4;
	private final int race5;
	private final int race5_2;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public QuintuplePlus(Date date, int race1, int race2, int race3, int race4,
			int race5, int race5_2) {
		this.date = date;
		this.race1 = race1;
		this.race2 = race2;
		this.race3 = race3;
		this.race4 = race4;
		this.race5 = race5;
		this.race5_2 = race5_2;
	}

	/** Inner class that represents a Quintuple Plus Premio */
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

	public int getRace1() {
		return race1;
	}

	public int getRace2() {
		return race2;
	}

	public int getRace3() {
		return race3;
	}

	public int getRace4() {
		return race4;
	}

	public int getRace5() {
		return race5;
	}

	public int getRace5_2() {
		return race5_2;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.QUINTUPLE_PLUS;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Qu\u00EDntuple plus";
	}

}
