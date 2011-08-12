package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Once draw
 */
public class Once implements Lottery {
	private final Date date;
	private final String num; 
	private final String serie;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public Once(Date date, String num, String serie) {
		this.date = date;
		this.num = num;
		this.serie = serie;
	}

	/** Inner class that represents a Cuponazo Once Premio */
	public class Premio {

		private final String categoria;
		private final float importeEuros;
		private final long importePesetas;

		private Premio(String categoria, float importeEuros, long importePesetas) {
			this.categoria = categoria;
			this.importeEuros = importeEuros;
			this.importePesetas = importePesetas;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("  Categoria: ")
					.append(categoria).append("  ImporteEuros: ")
					.append(importeEuros).append("  ImportePesetas: ")
					.append(importePesetas).toString();
		}
	}
	
	public void addPremio(String categoria, float importeEuros, long importePesetas) {
		premios.add(new Premio(categoria, importeEuros,
				importePesetas));
	}

	public Premio getPremio(int index) {
		return premios.get(index);
	}

	public int getNumPremios() {
		return premios.size();
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

	public String getNum() {
		return num;
	}

	public String getSerie() {
		return serie;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.ONCE;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "ONCE Ordinario";
	}

}
