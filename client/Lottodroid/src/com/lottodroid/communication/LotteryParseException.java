package com.lottodroid.communication;

@SuppressWarnings("serial")
public class LotteryParseException extends Exception {

  LotteryParseException(String msg) 
  {
      super(msg);
  }
  
  LotteryParseException(String msg, Exception e) 
  {
      super(msg, e);
  }

}
