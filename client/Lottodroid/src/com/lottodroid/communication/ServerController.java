package com.lottodroid.communication;

import java.util.Date;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Quiniela;

/**
 * Handles the communication with the server, providing a clean interface for the client application
 * to retrieve data (bonolotos, quinielas, ...)
 */
public class ServerController {

  /**
   * Asks for the last Bonoloto results to the server, and converts them into a {@link Bonoloto}
   * value object
   */
  public static Bonoloto retrieveLastBonoloto() {
    // get values from server
    Date date = new Date();
    String numbers = "5 6 7 1 0 9";
    String complementario = "3";
    String reintegro = "9";

    // build and return the bonoloto object
    return new Bonoloto(date, numbers, complementario, reintegro);
  }

  /**
   * Analogous to {@link #retrieveLastBonoloto}
   */
  public static Quiniela retrieveLastQuiniela() {
    // get values from server
    // ...

    // build and return the bonoloto object
    return new Quiniela();
  }

}
