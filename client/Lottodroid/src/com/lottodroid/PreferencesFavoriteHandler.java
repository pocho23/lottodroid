package com.lottodroid;

import java.io.Serializable;

import android.content.SharedPreferences;

/**
 * Handler for the set of favorites for the user, this information is saved as preferences of the entire
 * application using {@link SharedPreferences} module as storage system
 */
public class PreferencesFavoriteHandler implements FavoriteHandler, Serializable {

  private static final long serialVersionUID = 526939649420407967L;
  
  public PreferencesFavoriteHandler() {

  }
  
  /**
   * Return whether a lottery type is favorite or not.
   * 
   * If preferences are not saved ( first time the application runs ), by default every lottery type
   * is favorite. Shared preferences only contains the information of a lottery type if user has
   * changed its state.
   */
  @Override
  public boolean isFavorite(String lotteryId) {
    return Configuration.getSharedPreferences().getBoolean(lotteryId, true);
  }

  @Override
  public void setFavorite(String lotteryId, boolean favorite) {
    // TODO(pablo): Create the object just once on the contructor?
    SharedPreferences.Editor editor = Configuration.getSharedPreferences().edit();
    editor.putBoolean(lotteryId, favorite);
    editor.commit();
  }

}
