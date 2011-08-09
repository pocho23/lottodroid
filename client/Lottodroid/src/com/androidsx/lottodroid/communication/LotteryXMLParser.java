package com.androidsx.lottodroid.communication;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;

/**
 * Parser for the all the lottery draws ( bonoloto, quiniela, etc. ) retrieved
 * from the Lotoluck.
 * 
 * Convert the information into {@link Lottery} value objects
 */
class LotteryXMLParser {

	public static final int SORTEOS = 0;
	public static final int PRIMITIVA = 1;
	public static final int BONOLOTO = 2;
	public static final int GORDO_PRIMITIVA = 3;
	public static final int LOTTO6_49 = 4;
	public static final int LOTERIA_NACIONAL = 9;
	public static final int ONCE = 10;
	public static final int CUPONAZO_ONCE = 11;
	public static final int ONCE_FINDE = 12;
	public static final int QUINIELA = 13;
	public static final int EUROMILLONES = 14;
	public static final int COMBO = 15;
	public static final int LOTOTURF = 16;
	public static final int QUÍNTUPLE_PLUS = 17;
	public static final int QUINIGOL = 18;
	public static final int LOTERIA_7_19 = 19;

	private static final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");

	private LotteryXMLParser() {
		// Instantiation is not allowed for this class
	}

	/**
	 * Parse the string containing the information of one or more bonolotos and
	 * converts them into a list of {@link Bonoloto} value objects
	 * 
	 * @param response
	 *            String that represents the response from lotoluck to a lottery
	 *            request
	 * @return List<Lottery> A list of lottery objects representing the data
	 * @throws LotteryParseException
	 */
	public static List<Bonoloto> parseBonoloto(String response)
			throws LotteryParseException {

		try {
			return parseBonolotoData(parseContentTypeAndGetData(response,
					BONOLOTO));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Quiniela> parseQuiniela(String response)
			throws LotteryParseException {

		try {
			return parseQuinielaData(parseContentTypeAndGetData(response,
					QUINIELA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<GordoPrimitiva> parseGordoPrimitiva(String response)
			throws LotteryParseException {

		try {
			return parseGordoPrimitivaData(parseContentTypeAndGetData(response,
					GORDO_PRIMITIVA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Primitiva> parsePrimitiva(String response)
			throws LotteryParseException {

		try {
			return parsePrimitivaData(parseContentTypeAndGetData(response,
					PRIMITIVA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Lototurf> parseLototurf(String response)
			throws LotteryParseException {

		try {
			return parseLototurfData(parseContentTypeAndGetData(response,
					LOTOTURF));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Euromillon> parseEuromillon(String response)
			throws LotteryParseException {

		try {
			return parseEuromillonData(parseContentTypeAndGetData(response,
					EUROMILLONES));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<LoteriaNacional> parseLoteriaNacional(String response)
			throws LotteryParseException {

		try {
			return parseLoteriaNacionalData(parseContentTypeAndGetData(
					response, LOTERIA_NACIONAL));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}

	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Quinigol> parseQuinigol(String response)
			throws LotteryParseException {

		try {
			return parseQuinigolData(parseContentTypeAndGetData(response,
					QUINIGOL));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Parse the string containing the information of the last results of every
	 * lottery draw and converts them into a list of {@link Lottery} value
	 * objects
	 * 
	 * @param response
	 *            String that represents the response from lotoluck to a lottery
	 *            request
	 * @return List<Lottery> A list of lottery objects representing the data
	 * @throws LotteryParseException
	 */
	public static List<Lottery> parseAllLotteries(String response)
			throws LotteryParseException {

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(response);
			doc.getDocumentElement().normalize();
			Log.d("Debug", "punto 1 de debug");
			String rootElement = doc.getDocumentElement().getNodeName();
			if (rootElement.matches("error"))
				throw new LotteryParseException("No elements found");
			Log.d("Debug", "punto 2 de debug");
			Element game;
			int id = 0;

			List<Lottery> listLottery = new LinkedList<Lottery>();
			NodeList games = doc.getElementsByTagName("Juego");
			
			for (int i = 0; i < games.getLength(); i++) {
				Log.d("Debug", "punto 3 de debug");
				game = (Element) games.item(i);
				
				id = Integer.parseInt(game.getElementsByTagName("IdJuego")
						.item(0).getTextContent());

				
				Log.d("Debug", "punto 1 de debug");
				switch (id) {
				
				case BONOLOTO:
					listLottery.add(parseBonolotoData(game).get(0));
					break;
				case EUROMILLONES:
					listLottery.add(parseEuromillonData(game).get(0));
					break;
				case GORDO_PRIMITIVA:
					listLottery.add(parseGordoPrimitivaData(game).get(0));
					break;
				case LOTERIA_NACIONAL:
					listLottery.add(parseLoteriaNacionalData(game).get(0));
					break;
				case LOTOTURF:
					listLottery.add(parseLototurfData(game).get(0));
					break;
				case PRIMITIVA:
					listLottery.add(parsePrimitivaData(game).get(0));
					break;
				case QUINIELA:
					listLottery.add(parseQuinielaData(game).get(0));
					break;
				case QUINIGOL:
					listLottery.add(parseQuinigolData(game).get(0));
					break;

				}
			}

			return listLottery;

		} catch (ParserConfigurationException e) {
			throw new LotteryParseException("Error in configurating the parser");
		} catch (SAXException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (IOException e) {
			throw new LotteryParseException("Error connecting to the url");
		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}

	}

	/**
	 * From an unformatted date (YYYYMMDD) returns a formatted one (YYYY-MM-DD)
	 * 
	 * @param unformattedDate
	 * @return formattedDate
	 */
	private static String formatDate(String unformatedDate) {
		StringBuilder formatedDate = new StringBuilder();
		return formatedDate.append(unformatedDate.subSequence(0, 4))
				.append("-").append(unformatedDate.subSequence(4, 6))
				.append("-").append(unformatedDate.subSequence(6, 8))
				.toString();
	}

	/**
	 * Parse the string containing the information of a lottery draw checking
	 * that the data retrieved is the same type as the one requested
	 * 
	 * @param response
	 *            Represents the response from the server to a lottery request
	 * @param contentType
	 *            Lottery type requested
	 * @return Data retrieved for a lottery draw
	 * @throws LotteryParseException
	 */
	private static Element parseContentTypeAndGetData(String response,
			int contentType) throws LotteryParseException {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(response);
			doc.getDocumentElement().normalize();

			String rootElement = doc.getDocumentElement().getNodeName();
			if (rootElement.matches("error"))
				throw new LotteryParseException("No elements found");

			Element game;
			int id = 0;

			NodeList games = doc.getElementsByTagName("Juego");
			game = (Element) games.item(0);
			id = Integer.parseInt((game.getElementsByTagName("IdJuego")
					.item(0)).getTextContent());

			if (id == contentType)
				return game;

			throw new LotteryParseException("Wrong controller selected");

		} catch (Exception e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	private static List<Bonoloto> parseBonolotoData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nBonoloto:  " + date);

		// 6 results + complementario + reintegro
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}
		
		Bonoloto bonoloto = new Bonoloto(date, num[0], num[1], num[2], num[3],
				num[4], num[5], num[6], num[7]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			bonoloto.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(bonoloto.getPremio(i));
		}
		List<Bonoloto> lotteryList = new LinkedList<Bonoloto>();
		lotteryList.add(bonoloto);

		return lotteryList;
	}

	private static List<GordoPrimitiva> parseGordoPrimitivaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nGordoPrimitiva:  " + date);

		// 5 resultados + reintegro
		int num[] = new int[6];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}
		
		GordoPrimitiva gordoPrimitiva = new GordoPrimitiva(date, num[0],
				num[1], num[2], num[3], num[4], num[5]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			gordoPrimitiva.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(gordoPrimitiva.getPremio(i));
		}
		List<GordoPrimitiva> lotteryList = new LinkedList<GordoPrimitiva>();
		lotteryList.add(gordoPrimitiva);

		return lotteryList;
	}

	private static List<Quiniela> parseQuinielaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));

		Quiniela quiniela = new Quiniela(date);
		System.out.println("\n\nQuiniela:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		Element result; NamedNodeMap values;

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();

			quiniela.setMatch(i, values.getNamedItem("Equipo1").getNodeValue(),
					values.getNamedItem("Equipo2").getNodeValue(), values
							.getNamedItem("Valor").getNodeValue());
			
			quiniela.setScore(i, 
					Integer.parseInt(values.getNamedItem("GolesEquipo1").getNodeValue()),
					Integer.parseInt(values.getNamedItem("GolesEquipo2").getNodeValue()));

			System.out.println(values.getNamedItem("Equipo1").getNodeValue()
					+ " - " + values.getNamedItem("Equipo2").getNodeValue()
					+ " -- " + values.getNamedItem("Valor").getNodeValue()
					+ "(" + values.getNamedItem("GolesEquipo1").getNodeValue() + "-"
					+ values.getNamedItem("GolesEquipo2").getNodeValue() + ")");
		}
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			quiniela.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(quiniela.getPremio(i));
		}

		List<Quiniela> lotteryList = new LinkedList<Quiniela>();
		lotteryList.add(quiniela);

		return lotteryList;
	}

	private static List<Primitiva> parsePrimitivaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nPrimitiva:  " + date);

		// 8 resultados: 6 nums + reintegro + complementario
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}
		
		Primitiva primitiva = new Primitiva(date, num[0], num[1], num[2],
				num[3], num[4], num[5], num[6], num[7]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			primitiva.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(primitiva.getPremio(i));
		}

		List<Primitiva> lotteryList = new LinkedList<Primitiva>();
		lotteryList.add(primitiva);

		return lotteryList;
	}

	private static List<Lototurf> parseLototurfData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nLototurf:  " + date);

		// 8 results: 6 nums + caballoGanador + reintegro
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}
		
		Lototurf lototurf = new Lototurf(date, num[0], num[1], num[2], num[3],
				num[4], num[5], num[6], num[7]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			lototurf.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(lototurf.getPremio(i));
		}

		List<Lototurf> lotteryList = new LinkedList<Lototurf>();
		lotteryList.add(lototurf);

		return lotteryList;
	}

	private static List<Euromillon> parseEuromillonData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nEuroMillon:  " + date);

		// 5 results + estrella1 + estrella2
		int num[] = new int[7];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}
		
		Euromillon euromillon = new Euromillon(date, num[0], num[1], num[2],
				num[3], num[4], num[5], num[6]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			euromillon.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					Integer.parseInt(values.getNamedItem("AcertantesESP").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(euromillon.getPremio(i));
		}

		List<Euromillon> lotteryList = new LinkedList<Euromillon>();
		lotteryList.add(euromillon);

		return lotteryList;
	}

	private static List<LoteriaNacional> parseLoteriaNacionalData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		System.out.println("\n\nLoteriaNacional:  " + date);


		// The second award is in Premio instead of Resultado!
		NodeList results = game.getElementsByTagName("Premio");
		Element result = (Element) results.item(3);
		NamedNodeMap values = result.getAttributes();
		int premio2 = Integer.parseInt(values.getNamedItem("NumeroPremiado").getNodeValue());
		
		// 6 resultados: premio1, fraccion, serie, reintegro1,
		// reintegro2, reintegro3
		int num[] = new int[6];
		results = game.getElementsByTagName("Resultado");
	
		for (int i = 0; i < results.getLength(); i++) {

			result = (Element) results.item(i);
			values = result.getAttributes();

			// We receive Reintegros like this: R1 - R2 - R3
			if (i == 3) {
				num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(0, 1).toString());
				num[i + 1] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(4, 5).toString());
				num[i + 2] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(8, 9).toString());
				System.out.print(premio2 + "  " + num[i] + " " + num[i + 1] + " " + num[i + 2]);
				break;
			}

			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue());
			System.out.print(num[i] + " ");
		}

		LoteriaNacional loteriaNacional = new LoteriaNacional(date, num[0],
				num[1], num[2], premio2, num[3], num[4], num[5]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
			if(values.getNamedItem("Categoria").getNodeValue().matches("Terminaciones"))
				continue;
			
			loteriaNacional.addPremio(
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			if(i < 3)
				System.out.println(loteriaNacional.getPremio(i));
			else
				System.out.println(loteriaNacional.getPremio(2));
		}

		List<LoteriaNacional> lotteryList = new LinkedList<LoteriaNacional>();
		lotteryList.add(loteriaNacional);

		return lotteryList;
	}

	private static List<Quinigol> parseQuinigolData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha")
				.item(0).getTextContent()));
		Quinigol quinigol = new Quinigol(date);
		System.out.println("\n\nQuinigol:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		Element result; NamedNodeMap values;

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();

			quinigol.setMatch(i, values.getNamedItem("Equipo1").getNodeValue(),
					values.getNamedItem("Equipo2").getNodeValue(),
					values.getNamedItem("Valor").getNodeValue().substring(0, 1),
					values.getNamedItem("Valor").getNodeValue().substring(4, 5));

			quinigol.setScore(i, 
					Integer.parseInt(values.getNamedItem("GolesEquipo1").getNodeValue()),
					Integer.parseInt(values.getNamedItem("GolesEquipo2").getNodeValue()));

			System.out.println(values.getNamedItem("Equipo1").getNodeValue()
					+ " - "
					+ values.getNamedItem("Equipo2").getNodeValue()
					+ " -- "
					+ values.getNamedItem("Valor").getNodeValue()
							.substring(0, 1)
					+ "-"
					+ values.getNamedItem("Valor").getNodeValue()
							.substring(4, 5)
					+ "(" + values.getNamedItem("GolesEquipo1").getNodeValue() + "-"
					+ values.getNamedItem("GolesEquipo2").getNodeValue() + ")");
		}
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			quinigol.addPremio(
					Integer.parseInt(values.getNamedItem("Acertantes").getNodeValue()),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue()),
					Long.parseLong(values.getNamedItem("ImportePesetas").getNodeValue()));
			System.out.println(quinigol.getPremio(i));
		}

		List<Quinigol> lotteryList = new LinkedList<Quinigol>();
		lotteryList.add(quinigol);

		return lotteryList;
	}
}
