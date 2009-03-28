package com.lottodroid.model;

import java.util.Date;

/**
 * Results for a Bonoloto draw
 */
public class Bonoloto implements Lottery {
  private final Date date;
  private final String numbers;
  private final String reintegro;
  private final String complementario;
  
  public Bonoloto(Date date, String numbers, String reintegro, String complementario) {
    this.date = date;
    this.numbers = numbers;
    this.reintegro = reintegro;
    this.complementario = complementario;
  }

  public String getNumbers() {
    return numbers;
  }
  
  public String getReintegro() {
    return reintegro;
  }
  
  public String getComplementario() {
    return complementario;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getName() {
    return "Bonoloto";
  }
}
