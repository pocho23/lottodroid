package com.androidsx.lottodroid.model;

/**
 * All the possible lottery IDs that correspond to the different lottery draws in the application.
 */
public enum LotteryId {

  BONOLOTO("Bonoloto"),
  ONCE("ONCE"),
  EUROMILLON("Euromillon"),
  PRIMITIVA("Primitiva"),
  LOTERIA_NACIONAL("Lotería Nacional"),
  QUINIELA("Quiniela"),
  GORDO_PRIMITIVA("Gordo primitiva"),
  LOTERIA7_39("7/39 de la ONCE"),
  CUPONAZO_ONCE("Cuponazo ONCE"),
  ONCE_FINDE("ONCE Fin de semana"),
  LOTTO6_49("Loto Catalunya 6/49"),
  QUINIGOL("Quinigol"),
  LOTOTURF("Lototurf"),
  QUINTUPLE_PLUS("Quíntuple plus");
  
  private final String name;
  
  LotteryId(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public static LotteryId fromName(String name) {
    for (LotteryId value : values()) {
      if (name.equals(value.getName())) {
        return value;
      }
    }
    return null;
  }
}
