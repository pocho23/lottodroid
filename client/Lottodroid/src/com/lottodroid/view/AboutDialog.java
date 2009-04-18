package com.lottodroid.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.lottodroid.R;

/**
 * A simple dialog containing the about dialog
 * 
 */
public class AboutDialog extends AlertDialog {

  public AboutDialog(final Context context) {
    super(context);

    setView(View.inflate(context, R.layout.about_dialog, null));
    setIcon(R.drawable.primitiva);
    
    // set the title with the application version
    setTitle(context.getString(R.string.about_dialog_title,
        getApplicationVersion(context)));

    setButton(context.getString(R.string.about_dialog_button_ok), (OnClickListener) null);
    setButton2(context.getString(R.string.about_dialog_button_email),
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            /* Create the Intent and fill it with our mails */
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {
                "campbell.sx@gmail.com", "pablo.sx@gmail.com" });

            /* Send it off to the Activity-Chooser */
            context.startActivity(Intent.createChooser(emailIntent, "Send mail"));
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
