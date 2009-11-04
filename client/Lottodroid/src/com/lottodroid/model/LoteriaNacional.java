package com.lottodroid.model;

import java.util.Date;

/**
 * Results for a Loteria nacional draw
 */
public class LoteriaNacional implements Lottery {
  private final Date date;
  private final int premio1;
  private final int fraccion;
  private final int serie;
  private final int premio2;
  private final int reintegro1;
  private final int reintegro2;
  private final int reintegro3;

  public LoteriaNacional(Date date, int premio1, int fraccion, int serie, int premio2,
      int reintegro1, int reintegro2, int reintegro3) {
    this.date = date;
    this.premio1 = premio1;
    this.fraccion = fraccion;
    this.serie = serie;
    this.premio2 = premio2;
    this.reintegro1 = reintegro1;
    this.reintegro2 = reintegro2;
    this.reintegro3 = reintegro3;
  }

  public int getPremio1() {
    return premio1;
  }

  public int getFraccion() {
    return fraccion;
  }

  public int getSerie() {
    return serie;
  }

  public int getPremio2() {
    return premio2;
  }

  public int getReintegro1() {
    return reintegro1;
  }

  public int getReintegro2() {
    return reintegro2;
  }

  public int getReintegro3() {
    return reintegro3;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTERIA_NACIONAL;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getName() {
    return "Lotería Nacional";
  }

}
