package com.lottodroid.view;

import com.lottodroid.FavoriteHandler;
import com.lottodroid.R;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Utility methods for programmatic control of render effects.
 */
class ViewUtil {

  /**
   * Applies a visual effect to a row in a list, according to whether the item (a lottery type) is
   * one of the user's favourites or not.
   * 
   * TODO: apply a decent effect
   *
   * @param favoriteHandler the handler for the favorites system
   * @param lotteryId the String identifier for the lottery type
   * @param layoutRow the view, which is expected to correspond with an item in a list
   */
  static public void applyFavoriteEffect(FavoriteHandler favoriteHandler, String lotteryId, View layoutRow) {
    TextView title = (TextView) layoutRow.findViewById(R.id.title);
    if (favoriteHandler.isFavorite(lotteryId)) {
      title.setTextColor(Color.BLUE);      
    } else {
      title.setTextColor(Color.WHITE);
    }
  }
}
