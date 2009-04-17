package com.lottodroid;

import java.util.Map;

public class MockFavoriteHandler implements FavoriteHandler {

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
