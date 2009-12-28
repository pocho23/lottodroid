package com.androidsx.lottodroid.communication;

/**
 * Exception that indicates that there is an error parsing the lottery information that was
 * requested (for instance, the last 5 bonoloto results)
 */
class LotteryParseException extends Exception {

  private static final long serialVersionUID = 4512631193216919873L;

  public LotteryParseException() {
    super();
  }

  public LotteryParseException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public LotteryParseException(String detailMessage) {
    super(detailMessage);
  }

  public LotteryParseException(Throwable throwable) {
    super(throwable);
  }

}
