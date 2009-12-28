package com.androidsx.lottodroid.model;

import java.util.Date;

/**
 * Represents the results for a lottery draw.
 */
public interface Lottery {
  
  public LotteryId getId();
  public String getName();
  public Date getDate();
  
}
