package com.lottodroid.communication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Performs an HTTP request using URLConnection API
 * 
 */
public class HttpRequestPerformer {

  /**
   * Forms an HTTP request
   * 
   * @param url The URL to request for.
   * @return The response as a String
   * @throws Exception
   */
  public static String getResponse(String url) throws Exception {

    HttpURLConnection http = (HttpURLConnection) new URL(url.toString()).openConnection();
    http.setDoInput(true);
    http.setDoOutput(true);

    try {
      InputStream is = http.getInputStream();

      return toString(is);

    } finally {
      http.getInputStream().close();

      if (http.getErrorStream() != null)
        http.getErrorStream().close();
    }

  }

  /**
   * Reads an InputStream and returns its contents as a String. Also effects rate control.
   * 
   * @param inputStream The InputStream to read from.
   * @return The contents of the InputStream as a String.
   * @throws Exception
   */
  private static String toString(InputStream inputStream) throws Exception {
    StringBuilder outputBuilder = new StringBuilder();
    String string;
    
    if (inputStream != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      while (null != (string = reader.readLine())) {
        outputBuilder.append(string);
      }
    }

    return outputBuilder.toString();
  }
}
