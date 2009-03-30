package com.lottodroid.communication;

@SuppressWarnings("serial")
class LotteryParseException extends Exception {

  LotteryParseException(String msg) 
  {
      super(msg);
  }
  
  LotteryParseException(String msg, Exception e) 
  {
      super(msg, e);
  }

}
