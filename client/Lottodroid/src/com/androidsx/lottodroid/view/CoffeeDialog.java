package com.androidsx.lottodroid.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.androidsx.lottodroid.R;

/**
 * A simple dialog containing the about dialog
 * 
 */
public class CoffeeDialog extends AlertDialog {

  public CoffeeDialog(final Context context) {
    super(context);

    setView(View.inflate(context, R.layout.coffee_dialog, null));
    setIcon(R.drawable.icon);
    
    // set the title with the application version
    setTitle(context.getString(R.string.about_dialog_title,
        getApplicationVersion(context)));

    setButton(context.getString(R.string.coffee_dialog_button_next), (OnClickListener) null);
    setButton2(context.getString(R.string.coffee_dialog_button_buy),
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            /* TODO: Create the Intent to go to market place */

          }
        });
  }

  /**
   * Get the version of this application from the android manifest
   * 
   * @param context the context to use for the version retrieval.
   * @return the version of the Lottodroid application or an empty string if 
   *         the version could not be retrieved.
   */
  private Object getApplicationVersion(Context context) {
    String version = "";
    try {
      PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      version = pi.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      ;
    }
    return version;
  }

}
