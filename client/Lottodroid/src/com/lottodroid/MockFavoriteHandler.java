package com.lottodroid;

import java.io.Serializable;
import java.util.Map;

public class MockFavoriteHandler implements FavoriteHandler, Serializable {

  private static final long serialVersionUID = 5092293385153073742L;
  private final Map<String, Boolean> favoritesMap;

  public MockFavoriteHandler(Map<String, Boolean> favoritesMap) {
    this.favoritesMap = favoritesMap;
  }
  
  @Override
  public boolean isFavorite(String lotteryId) {
    return favoritesMap.get(lotteryId);
  }

  @Override
  public void setFavorite(String lotteryId, boolean favorite) {
    throw new UnsupportedOperationException();
  }

}
