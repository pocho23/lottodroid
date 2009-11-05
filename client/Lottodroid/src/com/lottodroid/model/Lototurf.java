package com.lottodroid.model;

import java.util.Date;

/**
 * Results for a Lototurf draw
 */
public class Lototurf implements Lottery {
  private final Date date;
  private final int num1;
  private final int num2;
  private final int num3;
  private final int num4;
  private final int num5;
  private final int num6;
  private final int caballoGanador;
  private final int reintegro;

  public Lototurf(Date date, int num1, int num2, int num3, int num4, int num5, int num6,
      int caballoGanador, int reintegro) {
    this.date = date;
    this.num1 = num1;
    this.num2 = num2;
    this.num3 = num3;
    this.num4 = num4;
    this.num5 = num5;
    this.num6 = num6;
    this.caballoGanador = caballoGanador;
    this.reintegro = reintegro;
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

  public int getNum6() {
    return num6;
  }

  public int getCaballoGanador() {
    return caballoGanador;
  }
  
  public int getReintegro() {
    return reintegro;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.LOTOTURF;
  }
  
  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getName() {
    return "Lototurf";
  }

}
