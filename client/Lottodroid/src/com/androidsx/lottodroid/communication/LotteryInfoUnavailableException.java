package com.androidsx.lottodroid.communication;

/**
 * Exception that indicates that the lottery information that was requested (for instance, the last
 * 5 bonoloto results) could not be retrieved.
 */
public class LotteryInfoUnavailableException extends Exception {

  private static final long serialVersionUID = 3336915283829284232L;

  public LotteryInfoUnavailableException() {
    super();
  }

  public LotteryInfoUnavailableException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public LotteryInfoUnavailableException(String detailMessage) {
    super(detailMessage);
  }

  public LotteryInfoUnavailableException(Throwable throwable) {
    super(throwable);
  }

}
