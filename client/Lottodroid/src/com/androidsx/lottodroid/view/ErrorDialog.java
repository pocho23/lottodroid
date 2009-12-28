package com.androidsx.lottodroid.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.androidsx.lottodroid.R;

public class ErrorDialog extends AlertDialog implements OnClickListener {
  private final Activity activity;

  public ErrorDialog(Activity activity, String msg) {
    super(activity);
    this.activity = activity;

    setTitle(activity.getString(R.string.error_dialog_title));
    setButton("OK", this);
    setMessage(msg);
    setCancelable(false);
  }

  public void onClick(DialogInterface dialog, int which) {
    activity.finish();
  }
}
