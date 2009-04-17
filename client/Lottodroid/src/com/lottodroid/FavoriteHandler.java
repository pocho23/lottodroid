package com.lottodroid;

/**
 * Handler for the set of favorites for the user, that allows to check whether a lottery type is one
 * of the user's favorites, as well as change the favorite property.
 */
public interface FavoriteHandler {

  boolean isFavorite(String lotteryId);

  void setFavorite(String lotteryId, boolean favorite);
}
