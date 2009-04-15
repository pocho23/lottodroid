package com.lottodroid.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ErrorDialog extends AlertDialog implements OnClickListener {
  private final Activity activity;

  public ErrorDialog(Activity activity, String msg) {
    super(activity);
    this.activity = activity;

    setTitle("Error");
    setButton("OK", this);
    setMessage(msg);
    setCancelable(false);
  }

  public void onClick(DialogInterface dialog, int which) {
    activity.finish();
  }
}
