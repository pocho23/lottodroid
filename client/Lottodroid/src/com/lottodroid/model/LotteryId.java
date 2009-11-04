package com.lottodroid.model;

/**
 * All the possible lottery IDs that correspond to the different lottery draws in the application.
 */
public enum LotteryId {

  BONOLOTO("Bonoloto"),
  QUINIELA("Quiniela"),
  PRIMITIVA("Primitiva"),
  LOTOTURF("Lototurf"),
  EUROMILLON("Euromillon"),
  LOTERIA_NACIONAL("Lotería Nacional"),
  QUINIGOL("Quinigol");
  
  private final String name;
  
  LotteryId(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
