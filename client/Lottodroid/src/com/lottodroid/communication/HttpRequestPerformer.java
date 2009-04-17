package com.lottodroid.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Performs an HTTP request using URLConnection API
 * 
 */
class HttpRequestPerformer {

  /** Time to wait to the server until the connection is canceled, it is specified in milliseconds */
  private static int CONNECT_TIMEOUT = 5000;
  private static int READ_TIMEOUT = 3000;
  
  /**
   * Forms an HTTP request
   * 
   * @param url The URL to request for.
   * @return The response as a String
   * @throws IOException 
   * @throws MalformedURLException 
   */
  public static String getResponse(String url) throws MalformedURLException, IOException {
    HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
    http.setRequestMethod("GET");
    http.setDoInput(true);
    http.setConnectTimeout(CONNECT_TIMEOUT);
    http.setReadTimeout(READ_TIMEOUT);

    try {
      return toString(http.getInputStream());
    } finally {
      http.getInputStream().close();

      if (http.getErrorStream() != null)
        http.getErrorStream().close();

      http.disconnect();
    }

  }

  /**
   * Reads an InputStream and returns its contents as a String. Also effects rate control.
   * 
   * @param inputStream The InputStream to read from.
   * @return The contents of the InputStream as a String.
   * @throws IOException 
   */
  private static String toString(InputStream inputStream) throws IOException {
    StringBuilder outputBuilder = new StringBuilder();
    String string = null;
    
    if (inputStream != null) {
      // http.getContentEncoding is null, but we must decode the data in the charset we received
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
      while (null != (string = reader.readLine())) {
        outputBuilder.append(string);
      }
    }

    return outputBuilder.toString();
  }
}
