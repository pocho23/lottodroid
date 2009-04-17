package com.lottodroid;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that helps instantiate the appropriate handler for the favorites handling.
 */
public class FavoriteHandlerFactory {

  /**
   * Instantiates the appropriate implementation for the {@link FavoriteHandler} interface and
   * returns it.
   * 
   * @return a new favorite handler
   */
  public static FavoriteHandler newFavoriteHandler() {
    if (Configuration.IN_MEMORY_MODE) {
      Map<String, Boolean> favoritesMap = new HashMap<String, Boolean>();
      favoritesMap.put("Bonoloto", false);
      favoritesMap.put("Quiniela", true);
      return new MockFavoriteHandler(favoritesMap);
    } else {
      throw new UnsupportedOperationException("The real implementation is not yet done");
    }
  }

}
