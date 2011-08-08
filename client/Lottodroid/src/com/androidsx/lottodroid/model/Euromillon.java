package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Euromillon draw
 */
public class Euromillon implements Lottery {
	private final Date date;
	private final int num1;
	private final int num2;
	private final int num3;
	private final int num4;
	private final int num5;
	private final int estrella1;
	private final int estrella2;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public Euromillon(Date date, int num1, int num2, int num3, int num4,
			int num5, int estrella1, int estrella2) {
		this.date = date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.estrella1 = estrella1;
		this.estrella2 = estrella2;
	}

	/** Inner class that represents a Euromillon Premio */
	public class Premio {

		private final int acertantes;
		private final int acertantesEsp;
		private final String categoria;
		private final float importeEuros;
		private final long importePesetas;

		private Premio(int acertantes, int acertantesEsp, String categoria, float importeEuros,
				long importePesetas) {
			this.acertantes = acertantes;
			this.acertantesEsp = acertantesEsp;
			this.categoria = categoria;
			this.importeEuros = importeEuros;
			this.importePesetas = importePesetas;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("Acertantes: ")
					.append(acertantes).append("AcertantesEsp: ")
					.append(acertantesEsp).append("  Categoria: ")
					.append(categoria).append("  ImporteEuros: ")
					.append(importeEuros).append("  ImportePesetas: ")
					.append(importePesetas).toString();
		}
	}
	
	public void addPremio(int acertantes, int acertantesEsp, String categoria, float importeEuros,
			long importePesetas) {
		premios.add(new Premio(acertantes, acertantesEsp, categoria, importeEuros,
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
	
	public int getAcetantesEsp(int index) {
		return premios.get(index).acertantesEsp;
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

	public int getNum1() {
		return num1;
	}

	public int getNum2() {
		return num2;
	}

	public int getNum3() {
		return num3;
	}

	public int getNum4() {
		return num4;
	}

	public int getNum5() {
		return num5;
	}

	public int getEstrella1() {
		return estrella1;
	}

	public int getEstrella2() {
		return estrella2;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.EUROMILLON;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Euromillon";
	}

}
