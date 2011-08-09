package com.androidsx.lottodroid;

import java.io.Serializable;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.view.TouchInterceptor;

/**
 * Activity for the screen that allows the user to sort the lottery entries.
 * <p>
 * It is actually a subactivity for the main screen (activity {@link Lottodroid}).
 */
public class SortingActivity extends ListActivity {

  public static final String TAG = SortingActivity.class.toString();

  private SortingViewAdapter adapter;
  private ListView listView;
  
  /** Listener for the event drag of the order mode */
  private TouchInterceptor.DragListener mDragListener = new TouchInterceptor.DragListener() {
    public void drag(int from, int to) {
      int listSize = adapter.getCount();

      if (from < listSize && to < listSize) {
        adapter.moveItem(from, to);
      }

      adapter.notifyDataSetChanged();
    }
  };
  
  /** Listener for the event drop of the order mode */
  private TouchInterceptor.DropListener mDropListener = new TouchInterceptor.DropListener() {
    public void drop(int from, int to) {
      listView.invalidateViews();
    }
  };

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_order_mode);
    
    @SuppressWarnings("unchecked")
	final List<LotteryId> lotteryIds = (List<LotteryId>) getIntent().getExtras().getSerializable(
        IntentExtraDataNames.SORTER_IN);
    adapter = new SortingViewAdapter(this, lotteryIds);
    setListAdapter(adapter);
    
    // Listener for the save button
    ((Button) findViewById(R.id.button_save_order_mode))
        .setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            // Return the sorter to the activity that called us
            Intent data = new Intent();
            data.putExtra(IntentExtraDataNames.SORTER_OUT, (Serializable) adapter.getLotteryIdList());
            setResult(RESULT_OK, data);
            finish();
          }
        });
    
    // Attach the listeners to the listview of this new layout
    TouchInterceptor touchInterceptor = (TouchInterceptor) getListView();
    touchInterceptor.setDropListener(mDropListener);
    touchInterceptor.setDragListener(mDragListener);
    touchInterceptor.setCacheColorHint(0);

    listView = touchInterceptor;
    
    Toast.makeText(this, "Desplaza cada sorteo al lugar deseado", Toast.LENGTH_LONG).show();
  }
}
